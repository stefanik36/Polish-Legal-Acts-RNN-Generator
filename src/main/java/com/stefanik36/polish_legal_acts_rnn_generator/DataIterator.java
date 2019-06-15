package com.stefanik36.polish_legal_acts_rnn_generator;

import io.vavr.collection.List;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.DataSetPreProcessor;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;

import java.util.*;

public class DataIterator implements DataSetIterator {


    private char[] dataAsCharacters;
    private Map<Character, Integer> characterIntegerMap;
    private List<Character> usedCharacters;
    private int miniBatchSize;
    private LinkedList<Integer> offSets;
    private int length;
    private int seed;

    public DataIterator(
            List<Character> usedCharacters,
            List<String> data,
            int miniBatchSize,
            int length,
            int seed
    ) {
        this.length = length;
        this.seed = seed;
        this.miniBatchSize = miniBatchSize;
        this.characterIntegerMap = new HashMap<>();
        this.usedCharacters = usedCharacters;
        usedCharacters.zipWith(List.range(0, usedCharacters.size()), (c, i) -> this.characterIntegerMap.put(c, i));
        this.dataAsCharacters = getDataAsCharacters(data);
        this.offSets = getOffSet();
    }

    private LinkedList<Integer> getOffSet() {
        LinkedList<Integer> ll = new LinkedList<>();
        int miniBatchesPerEpoch = (dataAsCharacters.length - 1) / length - 2;
        for (int i = 0; i < miniBatchesPerEpoch; i++) {
            ll.add(i * length);
        }
        Collections.shuffle(ll, new Random(seed));
        return ll;
    }

    private char[] getDataAsCharacters(List<String> data) {
        int allDataCharsSize = data.size() + data.map(String::length).sum().intValue();
        boolean lineInUsedCharacters = characterIntegerMap.containsKey('\n');
        char[] tmpCharacters = new char[allDataCharsSize];
        int iterator = 0;
        for (String s : data) {
            char[] thisLine = s.toCharArray();
            for (char aThisLine : thisLine) {
                if (characterIntegerMap.containsKey(aThisLine)) {
                    tmpCharacters[iterator++] = aThisLine;
                }
            }
            if (lineInUsedCharacters) tmpCharacters[iterator++] = '\n';
        }
        if (iterator == tmpCharacters.length) {
            return tmpCharacters;
        }
        return Arrays.copyOfRange(tmpCharacters, 0, iterator);
    }

    public char[] getDataAsCharacters() {
        return dataAsCharacters;
    }

    public char indexToCharacter(int idx) {
        return usedCharacters.get(idx);
    }

    public int characterToIndex(char c) {
        return characterIntegerMap.get(c);
    }

    public char getRandomCharacter() {
        return usedCharacters.get((int) (new Random(seed).nextDouble() * usedCharacters.size()));
    }

    @Override
    public boolean hasNext() {
        return offSets.size() > 0;
    }

    @Override
    public DataSet next() {
        return next(miniBatchSize);
    }

    @Override
    public DataSet next(int num) {
        int miniBatchSize = Math.min(num, offSets.size());
        INDArray input = Nd4j.create(new int[]{miniBatchSize, this.characterIntegerMap.size(), length}, 'f');
        INDArray labels = Nd4j.create(new int[]{miniBatchSize, this.characterIntegerMap.size(), length}, 'f');

        for (int i = 0; i < miniBatchSize; i++) {
            int startIdx = offSets.removeFirst();
            int endIdx = startIdx + length;
            int currCharIdx = characterIntegerMap.get(dataAsCharacters[startIdx]);
            int c = 0;
            for (int j = startIdx + 1; j < endIdx; j++, c++) {
                int nextCharIdx = characterIntegerMap.get(dataAsCharacters[j]);
                input.putScalar(new int[]{i, currCharIdx, c}, 1.0);
                labels.putScalar(new int[]{i, nextCharIdx, c}, 1.0);
                currCharIdx = nextCharIdx;
            }
        }
        return new DataSet(input, labels);
    }

    @Override
    public int inputColumns() {
        return this.characterIntegerMap.size();
    }

    @Override
    public int totalOutcomes() {
        return this.characterIntegerMap.size();
    }

    @Override
    public boolean resetSupported() {
        return true;
    }

    @Override
    public boolean asyncSupported() {
        return true;
    }

    @Override
    public void reset() {
        offSets.clear();
        offSets = getOffSet();
    }

    @Override
    public int batch() {
        return miniBatchSize;
    }

    public int getMiniBatchSize() {
        return miniBatchSize;
    }

    public int getLength() {
        return length;
    }

    public int getSeed() {
        return seed;
    }

    @Override
    public void setPreProcessor(DataSetPreProcessor preProcessor) {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public DataSetPreProcessor getPreProcessor() {
        throw new UnsupportedOperationException("Unsupported");
    }

    @Override
    public java.util.List<String> getLabels() {
        throw new UnsupportedOperationException("Unsupported");
    }

}
