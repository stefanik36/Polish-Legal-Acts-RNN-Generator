package com.stefanik36.polish_legal_acts_rnn_generator.factory;

import io.vavr.collection.List;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Optional;

public class DataFactory {

    private static final String POLISH_LEGAL_ACTS_FILE_PATH_RELATIVE = "src/main/resources/polish_legal_acts/polish_legal_acts.txt";
    private static final String POLISH_LEGAL_ACTS_FILE_PATH_JAR = "polish_legal_acts/polish_legal_acts.txt";
    private static final Charset POLISH_LEGAL_ACTS_FILE_CHARSET = Charset.forName("UTF-8");


    public File extract(String filePath) {
        try {
            File f = File.createTempFile(filePath, null);
            FileOutputStream resourceOS = new FileOutputStream(f);
            byte[] byteArray = new byte[1024];
            int i;
            InputStream classIS = Optional.ofNullable(getClass().getClassLoader().getResourceAsStream(filePath)).orElseThrow(() -> new RuntimeException("cannot find file"));
            while ((i = classIS.read(byteArray)) > 0) {
                resourceOS.write(byteArray, 0, i);
            }
            classIS.close();
            resourceOS.close();
            return f;
        } catch (Exception e) {
            throw new RuntimeException("An error has occurred while extracting the database.");
        }
    }

    private File getFile() throws IOException {
        String current = null;
        current = new File(".").getCanonicalPath();
        String polishLegalActsFilePathAbsolute = current + "/" + POLISH_LEGAL_ACTS_FILE_PATH_RELATIVE;
        File file = new File(polishLegalActsFilePathAbsolute);
        if (file.exists()) {
            return file;
        }
        return extract(POLISH_LEGAL_ACTS_FILE_PATH_JAR);
    }


    public List<String> getPolishLegalActsLines() {
        try {

            File file = getFile();
            return List.ofAll(Files.readAllLines(
                    file.toPath(),
                    POLISH_LEGAL_ACTS_FILE_CHARSET
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
