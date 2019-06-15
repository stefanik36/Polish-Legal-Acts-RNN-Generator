package com.stefanik36.polish_legal_acts_rnn_generator;

import io.vavr.collection.List;
import org.junit.Test;


import static org.junit.Assert.*;

public class DataIteratorTest {

    @Test
    public void construct() {
        DataIterator dataIterator = new DataIterator(
                List.of('q', 'w', 'e', 'r', 't', '\n'),
                List.of("qwe", "erty", "3qwe"),
                1,
                1,
                1
        );
        assertArrayEquals(dataIterator.getDataAsCharacters(), new char[]{'q', 'w', 'e', '\n', 'e', 'r', 't', '\n', 'q', 'w', 'e', '\n'});
    }
}