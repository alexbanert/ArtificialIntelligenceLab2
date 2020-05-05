package com.company.models;

import java.util.ArrayList;
import java.util.List;

public class Jolka implements Puzzle {

    private int id;
    private List<String> words;
    private ArrayList<Variable> variables;
    private int height;
    private int width;

    public Jolka(int id, ArrayList<Variable> variables, ArrayList<String> words, int height, int width){
        this.id = id;
        this.variables = variables;
        this.words = words;
        this.height = height;
        this.width = width;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public boolean isCorrect(Variable inputVariable, Character value) {
        int place = variables.indexOf(inputVariable);
        int x = place % width;
        int y = place / width;
        //find horizontal word
        StringBuilder horizontalBuilder = new StringBuilder();
        horizontalBuilder.append(value);
        int x_coord=x-1;
        while(x_coord>=0 && findInBoard(x_coord,y).getValue() != '#'){
            horizontalBuilder.insert(0, findInBoard(x_coord,y).getValue());
            x_coord--;
        }
        x_coord=x+1;
        while(x_coord<width && findInBoard(x_coord,y).getValue() != '#'){
            horizontalBuilder.append(findInBoard(x_coord,y).getValue());
            x_coord++;
        }

        //find vertical word
        StringBuilder verticalBuilder = new StringBuilder();
        verticalBuilder.append(value);
        int y_coord=y-1;
        while(y_coord>=0 && findInBoard(x,y_coord).getValue() != '#'){
            verticalBuilder.insert(0, findInBoard(x,y_coord).getValue());
            y_coord--;
        }
        y_coord=y+1;
        while(y_coord<height && findInBoard(x,y_coord).getValue() != '#'){
            verticalBuilder.append(findInBoard(x,y_coord).getValue());
            y_coord++;
        }

        String verticalWord = verticalBuilder.toString();
        String horizontalWord = horizontalBuilder.toString();
        boolean horizontalOk = false;
        boolean verticalOk = false;

        if(verticalWord.length() == 1)
            verticalOk = true;
        if(horizontalWord.length() == 1)
            horizontalOk = true;

        for(String word: words){
            if(isSubword(verticalWord, word))
                verticalOk = true;
            if(isSubword(horizontalWord, word))
                horizontalOk = true;
        }

        return horizontalOk && verticalOk;
    }

    private boolean isSubword(String sub, String original){
        if(sub.length() != original.length()){
            return false;
        }
        for(int i = 0; i < sub.length(); i++){
            Character subChar = sub.charAt(i);
            Character originalChar = original.charAt(i);
            if(!subChar.equals('_')){
                if (!subChar.equals(originalChar))
                    return false;
            }
        }

        return true;

    }

    private Variable findInBoard(int x, int y){
        return variables.get(y*width + x);
    }

    @Override
    public ArrayList<Variable> getVariables() {
        return variables;
    }

    @Override
    public ArrayList<Character> getAvailableCharacters() {
        ArrayList<Character> characters = new ArrayList<>();
        for(String word: words){
            for(int i = 0;i<word.length();i++){
                characters.add(new Character(word.charAt(i)));
            }
        }

        for(Variable variable: variables){
            characters.remove(variable.getValue());
        }

        return characters;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0 ; i < height; i++){

            for(int j = 0; j < width; j++){
                stringBuilder.append(variables.get(i*width + j));
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
