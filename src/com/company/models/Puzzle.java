package com.company.models;

import java.util.ArrayList;

public interface Puzzle {
    int getId();
    boolean isCorrect(Variable inputVariable, Character value);
    ArrayList<Variable> getVariables();
    ArrayList<Character> getAvailableCharacters();
}
