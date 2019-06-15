package com.stefanik36.polish_legal_acts_rnn_generator.factory;

import io.vavr.collection.List;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class DataFactory {
    private final String POLISH_LEGAL_ACTS_FILE_PATH_RELATIVE = "src/main/resources/polish_legal_acts/polish_legal_acts.txt";
    private final Charset POLISH_LEGAL_ACTS_FILE_CHARSET = Charset.forName("UTF-8");


    public List<String> getPolishLegalActsLines() {
        try {
            String current = new java.io.File(".").getCanonicalPath();
            String polishLegalActsFilePathAbsolute = current + "/" + POLISH_LEGAL_ACTS_FILE_PATH_RELATIVE;
            File file = new File(polishLegalActsFilePathAbsolute);
            if (!file.exists()) {
                throw new RuntimeException("Cannot find polish legal acts file in " + polishLegalActsFilePathAbsolute + ".");
            }
            return List.ofAll(Files.readAllLines(
                    new File(polishLegalActsFilePathAbsolute).toPath(),
                    POLISH_LEGAL_ACTS_FILE_CHARSET
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
