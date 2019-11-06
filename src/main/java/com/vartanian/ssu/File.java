package com.vartanian.ssu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class File {
    public static void readFromFile(List<String> text, String fileName) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "src/main/resources/"+fileName));
            String line =  reader.readLine();
            String Text = "";
            while (line != null) {
                text.add(line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
