package com.vartanian.ssu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lexeme {
    public int priority;
    public String name;
    public NFA automat;

    public Lexeme (int priority, String name, NFA automat) {
        this.priority = priority;
        this.name = name;
        this.automat = automat;
    }

    @Override
    public String toString() {
        return "Lexeme{" +
                "priority=" + priority +
                ", name='" + name + '\'' +
                ", automat=" + automat +
                '}';
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NFA getAutomat() {
        return automat;
    }

    public void setAutomat(NFA automat) {
        this.automat = automat;
    }

    public static List<Map<String, String>> Tokenizing(List<Lexeme> lexemes, String text)
    {
        List<Map<String, String>> result = new ArrayList<Map<String, String>>();
        int index = 0;
        while(index < text.length())
        {
            String currentLexemeClass = "";
            int currentPriority = 0;
            int maxLength = 0;

            for(Lexeme lexeme : lexemes)
            {
                Map <Boolean, Integer> token = lexeme.automat.maxStringFunc(lexeme.automat, text, index);
                if (token.containsKey(true))
                {
                    for (Map.Entry<Boolean,Integer> entry : token.entrySet()) {
                        boolean key = entry.getKey();
                        int value = entry.getValue();
                        if (maxLength < value) {
                            currentLexemeClass = lexeme.name;
                            currentPriority = lexeme.priority;
                            maxLength = value;
                        } else if (maxLength == value && currentPriority < lexeme.priority) {
                            currentLexemeClass = lexeme.name;
                            currentPriority = lexeme.priority;
                            maxLength = value;
                        }
                    }
                }
            }

            if(maxLength > 0)
            {
                Map<String, String>  res = new HashMap<String, String>();
                res.put(currentLexemeClass, text.substring(index, maxLength));
                result.add(res);
                index += maxLength;
            }
            else
            {
                Map<String, String>  res = new HashMap<String, String>();
                res.put("Error!!!", index + "");
                result.add(res);
                index++;
            }
        }

        return result;
    }
}
