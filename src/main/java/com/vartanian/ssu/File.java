package com.vartanian.ssu;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class File {
    public static ArrayList<String> readFromFile(List<String> text, String fileName, boolean automat) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(
                    "src/main/resources/"+fileName));
            String line =  reader.readLine();
            String Text = "";
            while (line != null) {
                if (automat){
                    Text += line;
                }
                else text.add(line);
                line = reader.readLine();
            }
            reader.close();
            if(automat)return new ArrayList<String>(Arrays.asList(Text.split(" ")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
