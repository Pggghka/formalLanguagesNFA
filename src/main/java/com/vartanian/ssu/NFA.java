package com.vartanian.ssu;


import java.util.*;

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
                createTransfuncnions(text, start+1)
        );
        return nfa;
    }

    public static Map<Boolean, Integer> maxStringFunc (NFA nfa, String text, int position){
        ArrayList <String> Way = new ArrayList<>();
        Map <Boolean, Integer> token = new HashMap <Boolean, Integer>();
        Way.add(nfa.start);
        Boolean isFinal = false;
        String curSymbProc = "";
        boolean Access = true;
        for(; position < text.length(); position++) {
            curSymbProc = "";
            for (int j = 0; j < nfa.transitionFunction.size(); j++) {
                if (nfa.transitionFunction.get(j).get(1).contains(text.charAt(position)+"")
                        && Way.get(Way.size()-1).contains(nfa.transitionFunction.get(j).get(0))) {
                    curSymbProc+= nfa.transitionFunction.get(j).get(2);
                }
            }
            if (curSymbProc.length()==0) break;
            Way.add(curSymbProc);
        }
        for ( String finalState:nfa.finalStates) {
            if(Way.get(Way.size()-1).contains(finalState) && Way.size()!=1){
                isFinal = true;
                break;
            }
        }
        token.put(isFinal, Way.size()-1);
        return token;
    }

    public  void maxString(NFA nfa, int position, String text){
        while (position < text.length())
        {
            Map <Boolean,Integer> tmp =  maxStringFunc(nfa, text, position);
            if (tmp.containsKey(true))
            {
                for (Map.Entry<Boolean,Integer> entry : tmp.entrySet()) {
                    boolean key = entry.getKey();
                    int value = entry.getValue();
                    System.out.println("token: " + key + ", result: " + text.substring(position, position + value));
                    position += value;
                }
            }
            else
            {
                position++;
            }
        }
    }


}
