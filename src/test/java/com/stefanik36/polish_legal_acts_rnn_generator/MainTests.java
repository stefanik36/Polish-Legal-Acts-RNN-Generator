package com.stefanik36.polish_legal_acts_rnn_generator;

import com.stefanik36.polish_legal_acts_rnn_generator.factory.CharactersFactory;
import com.stefanik36.polish_legal_acts_rnn_generator.factory.DataFactory;
import com.stefanik36.polish_legal_acts_rnn_generator.factory.RnnFactory;
import org.junit.Test;

public class MainTests {
    public static void main(String[] args) {
        new MainTests().testLtsml02ls200();
    }

    @Test
    public void testLtsml01ls400() {
        int seed = 666;
        int miniBatchSize = 32;
        int length = 10000;
        int epochs = 1;
        int layerSize = 400;
        int tBpttLength = 50;

        CharactersFactory charactersFactory = new CharactersFactory().builder()
                .lowercaseLetters()
                .uppercaseLetters()
                .digits()
                .lowercasePolishLetters()
                .uppercasePolishLetters()
                .actsCharacters()
                .build();
        DataFactory dataFactory = new DataFactory();

        DataIterator dataIterator = new DataIterator(
                charactersFactory.getCharacters(),
                dataFactory.getPolishLegalActsLines(),
                miniBatchSize,
                length,
                seed
        );

        Rnn network = RnnFactory.getPolishLegalActsLtsml01(seed, dataIterator.inputColumns(), dataIterator.totalOutcomes(), layerSize, tBpttLength);
        network.start(dataIterator, epochs);
    }

    @Test
    public void testLtsml02ls400() {
        int seed = 666;
        int miniBatchSize = 32;
        int length = 10000;
        int epochs = 1;
        int layerSize = 400;
        int tBpttLength = 50;

        CharactersFactory charactersFactory = new CharactersFactory().builder()
                .lowercaseLetters()
                .uppercaseLetters()
                .digits()
                .lowercasePolishLetters()
                .uppercasePolishLetters()
                .actsCharacters()
                .build();
        DataFactory dataFactory = new DataFactory();

        DataIterator dataIterator = new DataIterator(
                charactersFactory.getCharacters(),
                dataFactory.getPolishLegalActsLines(),
                miniBatchSize,
                length,
                seed
        );

        Rnn network = RnnFactory.getPolishLegalActsLtsml02(seed, dataIterator.inputColumns(), dataIterator.totalOutcomes(), layerSize, tBpttLength);
        network.start(dataIterator, epochs);
    }

    @Test
    public void testLtsml03ls400() {
        int seed = 666;
        int miniBatchSize = 32;
        int length = 10000;
        int epochs = 1;
        int layerSize = 400;
        int tBpttLength = 50;

        CharactersFactory charactersFactory = new CharactersFactory().builder()
                .lowercaseLetters()
                .uppercaseLetters()
                .digits()
                .lowercasePolishLetters()
                .uppercasePolishLetters()
                .actsCharacters()
                .build();
        DataFactory dataFactory = new DataFactory();

        DataIterator dataIterator = new DataIterator(
                charactersFactory.getCharacters(),
                dataFactory.getPolishLegalActsLines(),
                miniBatchSize,
                length,
                seed
        );

        Rnn network = RnnFactory.getPolishLegalActsLtsml03(seed, dataIterator.inputColumns(), dataIterator.totalOutcomes(), layerSize, tBpttLength);
        network.start(dataIterator, epochs);
    }


    @Test
    public void testLtsml01ls100() {
        int seed = 666;
        int miniBatchSize = 32;
        int length = 10000;
        int epochs = 1;
        int layerSize = 100;
        int tBpttLength = 50;

        CharactersFactory charactersFactory = new CharactersFactory().builder()
                .lowercaseLetters()
                .uppercaseLetters()
                .digits()
                .lowercasePolishLetters()
                .uppercasePolishLetters()
                .actsCharacters()
                .build();
        DataFactory dataFactory = new DataFactory();

        DataIterator dataIterator = new DataIterator(
                charactersFactory.getCharacters(),
                dataFactory.getPolishLegalActsLines(),
                miniBatchSize,
                length,
                seed
        );

        Rnn network = RnnFactory.getPolishLegalActsLtsml01(seed, dataIterator.inputColumns(), dataIterator.totalOutcomes(), layerSize, tBpttLength);
        network.start(dataIterator, epochs);
    }

    @Test
    public void testLtsml02ls100() {
        int seed = 666;
        int miniBatchSize = 32;
        int length = 10000;
        int epochs = 1;
        int layerSize = 100;
        int tBpttLength = 50;

        CharactersFactory charactersFactory = new CharactersFactory().builder()
                .lowercaseLetters()
                .uppercaseLetters()
                .digits()
                .lowercasePolishLetters()
                .uppercasePolishLetters()
                .actsCharacters()
                .build();
        DataFactory dataFactory = new DataFactory();

        DataIterator dataIterator = new DataIterator(
                charactersFactory.getCharacters(),
                dataFactory.getPolishLegalActsLines(),
                miniBatchSize,
                length,
                seed
        );

        Rnn network = RnnFactory.getPolishLegalActsLtsml02(seed, dataIterator.inputColumns(), dataIterator.totalOutcomes(), layerSize, tBpttLength);
        network.start(dataIterator, epochs);
    }


    @Test
    public void testLtsml01ls200() {
        int seed = 666;
        int miniBatchSize = 32;
        int length = 10000;
        int epochs = 1;
        int layerSize = 200;
        int tBpttLength = 50;

        CharactersFactory charactersFactory = new CharactersFactory().builder()
                .lowercaseLetters()
                .uppercaseLetters()
                .digits()
                .lowercasePolishLetters()
                .uppercasePolishLetters()
                .actsCharacters()
                .build();
        DataFactory dataFactory = new DataFactory();

        DataIterator dataIterator = new DataIterator(
                charactersFactory.getCharacters(),
                dataFactory.getPolishLegalActsLines(),
                miniBatchSize,
                length,
                seed
        );

        Rnn network = RnnFactory.getPolishLegalActsLtsml01(seed, dataIterator.inputColumns(), dataIterator.totalOutcomes(), layerSize, tBpttLength);
        network.start(dataIterator, epochs);
    }


    @Test
    public void testLtsml02ls200() {
        int seed = 666;
        int miniBatchSize = 32;
        int length = 10000;
        int epochs = 1;
        int layerSize = 200;
        int tBpttLength = 50;

        CharactersFactory charactersFactory = new CharactersFactory().builder()
                .lowercaseLetters()
                .uppercaseLetters()
                .digits()
                .lowercasePolishLetters()
                .uppercasePolishLetters()
                .actsCharacters()
                .build();
        DataFactory dataFactory = new DataFactory();

        DataIterator dataIterator = new DataIterator(
                charactersFactory.getCharacters(),
                dataFactory.getPolishLegalActsLines(),
                miniBatchSize,
                length,
                seed
        );

        Rnn network = RnnFactory.getPolishLegalActsLtsml02(seed, dataIterator.inputColumns(), dataIterator.totalOutcomes(), layerSize, tBpttLength);
        network.start(dataIterator, epochs);
    }

}
