package com.stefanik36.polish_legal_acts_rnn_generator;

import com.stefanik36.polish_legal_acts_rnn_generator.factory.RnnFactory;
import org.junit.Test;

public class RnnFactoryTest {

    @Test
    public void getPolishLegalActsRnn01() {
        Rnn rnn = RnnFactory.getPolishLegalActsLtsml01(1, 5, 20, 200, 50);
        System.out.println(rnn.getNetwork().summary());
    }
}