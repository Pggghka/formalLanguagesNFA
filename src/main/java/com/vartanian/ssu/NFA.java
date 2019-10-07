package com.vartanian.ssu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NFA {
    ArrayList<String> entryAlphabet = new ArrayList<>();
    ArrayList <String> statements = new ArrayList<>();
    List<List<String>> transitionFunction = new ArrayList<>();
    ArrayList <String> finalStates = new ArrayList<>();
    String start;

    public NFA(ArrayList<String> entryAlphabet, ArrayList<String> statements, ArrayList<String> finalStates, String start, List<List<String>> transitionFunction) {
        this.entryAlphabet = entryAlphabet;
        this.statements = statements;
        this.transitionFunction = transitionFunction;
        this.finalStates = finalStates;
        this.start = start;
    }

    public NFA() {}

    @Override
    public String toString() {
        return "NFA{" +
                "\n\tentryAlphabet=" + entryAlphabet +
                "\n\tstatements=" + statements +
                "\n\ttransitionFunction=" + transitionFunction +
                "\n\tfinalStates=" + finalStates +
                "\n\tstart='" + start + '\'' +
                "\n}";
    }

    public ArrayList<String> getEntryAlphabet() {
        return entryAlphabet;
    }

    public void setEntryAlphabet(ArrayList<String> entryAlphabet) {
        this.entryAlphabet = entryAlphabet;
    }

    public ArrayList<String> getStatements() {
        return statements;
    }

    public void setStatements(ArrayList<String> statements) {
        this.statements = statements;
    }

    public List<List<String>> getTransitionFunction() {
        return transitionFunction;
    }

    public void setTransitionFunction(List<List<String>> transitionFunction) {
        this.transitionFunction = transitionFunction;
    }

    public ArrayList<String> getFinalStates() {
        return finalStates;
    }

    public void setFinalStates(ArrayList<String> finalStates) {
        this.finalStates = finalStates;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public static List<List<String>> createTransfuncnions(List<String> text, int index) {
        List<List<String>> transitionFunction = new ArrayList<List<String>>();
        for (; index < text.size(); index++) {
            if (!text.get(index).contains("|")) break;
            transitionFunction.add(createArr(text.get(index), "| ", ""));
        }
        return transitionFunction;
    }

    public static ArrayList<String> createArr(String text, String firstSym, String secondSym){
        return new ArrayList<String>(Arrays.asList(text.replace(firstSym,"").replace(secondSym, "").split(" ")));
    }

    public static NFA createAutomat(List <String> text, String number){
        int start = 1;
        for (int i = 0; i < text.size(); i++) {
            String line = text.get(i);
            if(line.contains("Automat "+ number)) start += i;
        }
        if(start==1){
            System.out.println("Wrong automat number");
            return null;
        }

        createTransfuncnions(text, 7);
        NFA nfa = new NFA(
                createArr(text.get(start++), "alphabet - ", ","),
                createArr(text.get(start++), "statements - ", ","),
                createArr(text.get(start++), "final - ", ","),
                text.get(start++).replace("start - ", ""),
                createTransfuncnions(text, start+2)
        );
        return nfa;
    }

    public static String automatContains (NFA nfa, String word){
        ArrayList <String> Way = new ArrayList<>();
        Way.add(nfa.start);
        String curSymbProc = "";

        for(int i = 0; i< word.length(); i++) {
            curSymbProc = "";
            for (int j = 0; j < nfa.transitionFunction.size(); j++) {
                if (nfa.transitionFunction.get(j).get(1).contains(word.charAt(i)+"")
                        && Way.get(Way.size()-1).contains(nfa.transitionFunction.get(j).get(0))) {
                    curSymbProc+= nfa.transitionFunction.get(j).get(2);
                }
            }
            Way.add(curSymbProc);
        }

        System.out.print("Word " + word + " : " + Way);
        for ( String finalstate:nfa.finalStates) {
            if(Way.get(Way.size()-1).contains(finalstate)){
                System.out.println(" !!!Word accessed!!!");
                return word + " ";
            }
        }
        System.out.println();
        return "";
    }

    public static void maxString (String acceptedWords){
        String[] words = acceptedWords.split(" ");
        int max = -1; String maxLengthWord = "";
        for(String word : words){
            if(word.length()>max){
                max = word.length();
                maxLengthWord = word;
            }
        }
        System.out.println("maxString is - " + maxLengthWord + "(length -" + max + ")");
    }
}
