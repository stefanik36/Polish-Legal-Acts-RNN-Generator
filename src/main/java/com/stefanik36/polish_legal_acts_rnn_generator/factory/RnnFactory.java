package com.stefanik36.polish_legal_acts_rnn_generator.factory;

import com.stefanik36.polish_legal_acts_rnn_generator.Rnn;
import org.deeplearning4j.nn.conf.BackpropType;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.layers.LSTM;
import org.deeplearning4j.nn.conf.layers.RnnOutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.deeplearning4j.optimize.listeners.ScoreIterationListener;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;

public class RnnFactory {

    public static Rnn getPolishLegalActsLtsml01(int seed, int inSize, int outSize, int layerSize, int tBpttLength) {
        LSTM l1 = new LSTM.Builder().nIn(inSize).nOut(layerSize).activation(Activation.TANH).build();
        RnnOutputLayer l3 = new RnnOutputLayer.Builder(LossFunctions.LossFunction.MCXENT)
                .activation(Activation.SOFTMAX)
                .nIn(layerSize)
                .nOut(outSize).build();

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(seed)
                .l2(0.0001)
                .weightInit(WeightInit.XAVIER)
                .updater(new Adam(0.005))
                .list()
                .layer(l1)
                .layer(l3)
                .backpropType(BackpropType.TruncatedBPTT)
                .tBPTTForwardLength(tBpttLength)
                .tBPTTBackwardLength(tBpttLength)
                .build();

        MultiLayerNetwork net = new MultiLayerNetwork(conf);
        net.init();
        net.setListeners(new ScoreIterationListener(1));

        System.out.println(net.summary());

        return new Rnn(net);
    }

    public static Rnn getPolishLegalActsLtsml02(int seed, int inSize, int outSize, int layerSize, int tBpttLength) {
        LSTM l1 = new LSTM.Builder().nIn(inSize).nOut(layerSize).activation(Activation.TANH).build();
        LSTM l2 = new LSTM.Builder().nIn(layerSize).nOut(layerSize).activation(Activation.TANH).build();
        RnnOutputLayer l3 = new RnnOutputLayer.Builder(LossFunctions.LossFunction.MCXENT)
                .activation(Activation.SOFTMAX)
                .nIn(layerSize)
                .nOut(outSize).build();

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(seed)
                .l2(0.0001)
                .weightInit(WeightInit.XAVIER)
                .updater(new Adam(0.005))
                .list()
                .layer(l1)
                .layer(l2)
                .layer(l3)
                .backpropType(BackpropType.TruncatedBPTT)
                .tBPTTForwardLength(tBpttLength)
                .tBPTTBackwardLength(tBpttLength)
                .build();

        MultiLayerNetwork net = new MultiLayerNetwork(conf);
        net.init();
        net.setListeners(new ScoreIterationListener(1));

        System.out.println(net.summary());

        return new Rnn(net);
    }
    public static Rnn getPolishLegalActsLtsml03(int seed, int inSize, int outSize, int layerSize, int tBpttLength) {
        LSTM l1 = new LSTM.Builder().nIn(inSize).nOut(layerSize).activation(Activation.TANH).build();
        LSTM l2 = new LSTM.Builder().nIn(layerSize).nOut(layerSize).activation(Activation.TANH).build();
        LSTM l3 = new LSTM.Builder().nIn(layerSize).nOut(layerSize).activation(Activation.TANH).build();
        RnnOutputLayer ol = new RnnOutputLayer.Builder(LossFunctions.LossFunction.MCXENT)
                .activation(Activation.SOFTMAX)
                .nIn(layerSize)
                .nOut(outSize).build();

        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(seed)
                .l2(0.0001)
                .weightInit(WeightInit.XAVIER)
                .updater(new Adam(0.005))
                .list()
                .layer(l1)
                .layer(l2)
                .layer(l3)
                .layer(ol)
                .backpropType(BackpropType.TruncatedBPTT)
                .tBPTTForwardLength(tBpttLength)
                .tBPTTBackwardLength(tBpttLength)
                .build();

        MultiLayerNetwork net = new MultiLayerNetwork(conf);
        net.init();
        net.setListeners(new ScoreIterationListener(1));

        System.out.println(net.summary());

        return new Rnn(net);
    }


}
