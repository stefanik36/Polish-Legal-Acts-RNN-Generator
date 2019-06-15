package com.stefanik36.polish_legal_acts_rnn_generator;

import com.stefanik36.polish_legal_acts_rnn_generator.factory.DataFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataFactoryTest {

    @Test
    public void getPolishLegalActsFilePath() {
        DataFactory dataFactory = new DataFactory();
        assertEquals(dataFactory.getPolishLegalActsLines().size(),95357);
    }
}