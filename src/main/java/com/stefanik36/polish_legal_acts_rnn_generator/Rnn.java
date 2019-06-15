package com.stefanik36.polish_legal_acts_rnn_generator;

import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.factory.Nd4j;

import java.util.Random;

public class Rnn {
    private MultiLayerNetwork network;

    public Rnn(MultiLayerNetwork network) {
        this.network = network;

    }

    public void start(DataIterator dataIterator, int epochs) {

        int generateSamplesEvery = 2;
        int nCharactersToSample = 300;
        int nSamplesToGenerate = 5;
        int batchNumber = 0;
        String startCharacterSamples = "Ar";

        for (int i = 0; i < epochs; i++) {
            while (dataIterator.hasNext()) {
                DataSet ds = dataIterator.next();
                network.fit(ds);
                if (++batchNumber % generateSamplesEvery == 0) {
                    System.out.println("--------------------");
                    System.out.println("Completed " + batchNumber + " minibatches of size " + batchNumber + "x" + dataIterator.getLength() + " characters");
                    System.out.println("Sampling characters from network given initialization \"" + (startCharacterSamples == null ? "" : startCharacterSamples) + "\"");
                    String[] samples = sampleCharactersFromNetwork(
                            startCharacterSamples,
                            network,
                            dataIterator,
                            new Random(dataIterator.getSeed()),
                            nCharactersToSample,
                            nSamplesToGenerate
                    );
                    for (int j = 0; j < samples.length; j++) {
                        System.out.println("----- Sample " + j + " -----");
                        System.out.println(samples[j]);
                        System.out.println();
                    }
                }
            }

            dataIterator.reset();
        }

        System.out.println("\n\nExample complete");
    }


    private static String[] sampleCharactersFromNetwork(
            String initialization,
            MultiLayerNetwork net,
            DataIterator iter,
            Random rng,
            int charactersToSample,
            int numSamples
    ) {
        if (initialization == null) {
            initialization = String.valueOf(iter.getRandomCharacter());
        }

        INDArray initializationInput = Nd4j.zeros(numSamples, iter.inputColumns(), initialization.length());
        char[] init = initialization.toCharArray();
        for (int i = 0; i < init.length; i++) {
            int idx = iter.characterToIndex(init[i]);
            for (int j = 0; j < numSamples; j++) {
                initializationInput.putScalar(new int[]{j, idx, i}, 1.0f);
            }
        }

        StringBuilder[] sb = new StringBuilder[numSamples];
        for (int i = 0; i < numSamples; i++) sb[i] = new StringBuilder(initialization);

        net.rnnClearPreviousState();
        INDArray output = net.rnnTimeStep(initializationInput);
        output = output.tensorAlongDimension((int) output.size(2) - 1, 1, 0);

        for (int i = 0; i < charactersToSample; i++) {
            INDArray nextInput = Nd4j.zeros(numSamples, iter.inputColumns());
            for (int s = 0; s < numSamples; s++) {
                double[] outputProbDistribution = new double[iter.totalOutcomes()];
                for (int j = 0; j < outputProbDistribution.length; j++)
                    outputProbDistribution[j] = output.getDouble(s, j);
                int sampledCharacterIdx = sampleFromDistribution(outputProbDistribution, rng);
                nextInput.putScalar(new int[]{s, sampledCharacterIdx}, 1.0f);
                sb[s].append(iter.indexToCharacter(sampledCharacterIdx));
            }
            output = net.rnnTimeStep(nextInput);
        }
        String[] out = new String[numSamples];
        for (int i = 0; i < numSamples; i++) out[i] = sb[i].toString();
        return out;
    }

    private static int sampleFromDistribution(double[] distribution, Random rng) {
        double d = 0.0;
        double sum = 0.0;
        for (int t = 0; t < 10; t++) {
            d = rng.nextDouble();
            sum = 0.0;
            for (int i = 0; i < distribution.length; i++) {
                sum += distribution[i];
                if (d <= sum) return i;
            }
        }
        throw new IllegalArgumentException("Distribution is invalid? d=" + d + ", sum=" + sum);
    }

    public MultiLayerNetwork getNetwork() {
        return network;
    }
}
