package com.company.models;

import java.util.ArrayList;

public class Sudoku implements Puzzle {
    private int id;
    private double difficulty;
    private ArrayList<Variable> variables;

    public Sudoku(int id, double difficulty, ArrayList<Variable> variables){
        this.id = id;
        this.difficulty = difficulty;
        this.variables = variables;
    }


    public boolean isCorrect(Variable inputVariable, Character value){
        int place = variables.indexOf(inputVariable);
        //System.out.println(place);
        //System.out.println(value);
        int x = place % 9;
        int y = place / 9;

        for(int i = 0 ; i < 9; i++){
            Variable variable = variables.get(y*9 + i);
            if(variable.getValue().equals(value) && i != x){
                return false;
            }
            variable = variables.get(i*9 + x);
            if(variable.getValue().equals(value) && i != y){
                return false;
            }
        }

        int sqX = x % 3;
        int sqY = y % 3;
        int edgeX = x / 3;
        int edgeY = y / 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Variable variable = variables.get((edgeY * 3 + i) * 9 + (edgeX * 3 + j));
                if (variable.getValue() == value && i != sqY && j != sqX) {
                    return false;
                }

            }
        }
        return true;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(double difficulty) {
        this.difficulty = difficulty;
    }

    public ArrayList<Variable> getVariables() {
        return variables;
    }

    @Override
    public ArrayList<Character> getAvailableCharacters() {
        return Variable.getDefaultDomain();
    }

    public void setVariables(ArrayList<Variable> variables) {
        this.variables = variables;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0 ; i < variables.size(); i++){
            stringBuilder.append(variables.get(i)+ ", ");
            if((i+1)%9 == 0 && i > 0)
                stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
