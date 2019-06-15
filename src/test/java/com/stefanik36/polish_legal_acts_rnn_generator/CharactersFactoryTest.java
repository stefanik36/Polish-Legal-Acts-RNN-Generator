package com.stefanik36.polish_legal_acts_rnn_generator;

import com.stefanik36.polish_legal_acts_rnn_generator.factory.CharactersFactory;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class CharactersFactoryTest {

    @Test
    public void builder() {
        CharactersFactory result = new CharactersFactory().builder()
                .lowercaseLetters()
                .uppercaseLetters()
                .digits()
                .lowercasePolishLetters()
                .uppercasePolishLetters()
                .actsCharacters()
                .other('%')
                .build();


        assertEquals(result.getCharacters().size(), 95);
    }
}