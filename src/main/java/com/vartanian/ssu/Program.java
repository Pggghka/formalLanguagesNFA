package com.vartanian.ssu;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Program {

    public static void main(String[] args) {
        // Reading the whole
        List <String> textAutomats = new ArrayList<>();
        File file = new File();
        file.readFromFile(textAutomats, "automat.txt");

        // Creating one automat from file
        NFA first = new NFA();
        first = first.createAutomat(textAutomats, "1");
        System.out.println(first);
        ArrayList <String> TeaxtArr = new ArrayList<>();
        File textTXT = new File();
        textTXT.readFromFile(TeaxtArr, "text.txt");
        String text = "";
        for (String s : TeaxtArr)
        {
            text += s + "\\" + "n";
        }
        // MaxString function
        first.maxString(first, 0, text);

        // Creating two automats for lexemes
        NFA keyWordAutomat = new NFA();
        keyWordAutomat = keyWordAutomat.createAutomat(textAutomats, "2");
        NFA idAutomat = new NFA();
        idAutomat = idAutomat.createAutomat(textAutomats, "3");

        first.maxString(keyWordAutomat, 0, text);
        // Creating two lexemes
        Lexeme keyWords = new Lexeme(100, "keyWords", keyWordAutomat);
        Lexeme id = new Lexeme(10, "id", idAutomat);
        List <Lexeme> Lexemes = new ArrayList<>();
        Lexemes.add(id);
        Lexemes.add(keyWords);

        System.out.println(Lexemes.toString());
        List<Map<String, String>> result = Lexeme.Tokenizing(Lexemes, "while");
        for(Map<String, String> res : result)
        {
            System.out.println(res);
        }
    }
}

