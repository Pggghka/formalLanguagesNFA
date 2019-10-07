package com.vartanian.ssu;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Program {

    public static void main(String[] args) {
        //Reading the whole
        List <String> textAutomats = new ArrayList<>();
        File file = new File();
        file.readFromFile(textAutomats, "automat.txt", false);

        //Creating one autoomat from file
        NFA first = new NFA();
        first = first.createAutomat(textAutomats, "3");
        System.out.println(first);

        //Reading all automats form file
        ArrayList <NFA> automats = new ArrayList<>();
        int index = 0;
        for (int i=0;i < textAutomats.size(); i++){
            if(textAutomats.get(i).contains("Automat")) {
                automats.add(first.createAutomat(textAutomats,textAutomats.get(i).split(" ")[1]));
            }
        }

        String acceptedWords = "";

        //Checking the word
        String word = "10";
        first.automatContains(first, word);

        //Checking words
        ArrayList <String> words = new ArrayList<>();
        File textTXT = new File();
        words = textTXT.readFromFile(words, "text.txt",true);
        for(String Word: words){
            acceptedWords += first.automatContains(first, Word);
        }

        first.maxString(acceptedWords);

    }
}

