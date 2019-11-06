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
        file.readFromFile(textAutomats, "automat.txt");

        //Creating one automat from file
        NFA first = new NFA();
        first = first.createAutomat(textAutomats, "3");
        System.out.println(first);


        ArrayList <String> TeaxtArr = new ArrayList<>();
        File textTXT = new File();
        textTXT.readFromFile(TeaxtArr, "text.txt");
        String text = "";
        for (String s : TeaxtArr)
        {
            text += s;
        }

        first.maxString(first, 0, text);
    }
}

