package com.company.utils;

import com.company.models.Sudoku;
import com.company.models.Variable;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class SudokuParser {
    private File dataFile;
    public SudokuParser(String fileName) throws FileNotFoundException {
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path filePath = Paths.get(currentPath.toString(), "data", fileName);
        if (filePath.toFile().exists()) {
            dataFile = filePath.toFile();
        } else {
            throw new FileNotFoundException();
        }

    }

    public List<Sudoku> getSudokus(){
        List<Sudoku> sudokus = new ArrayList<>();
        Variable.setDefaultDomain(new ArrayList<>(Arrays.asList('1', '2', '3', '4', '5', '6', '7', '8', '9')));
        Variable.setDefaultChar('.');
        List<String> lines = readLinesFromFile(dataFile);

        // Start at 1 to skip first line
        for(int i = 1; i < lines.size(); i++){
            String currentLine = lines.get(i);
            String[] splitLine = currentLine.split(";");
            int id = Integer.parseInt(splitLine[0]);
            double difficulty = Double.parseDouble(splitLine[1]);
            String boardLine = splitLine[2];
            ArrayList<Variable> variables = new ArrayList<>();
            for(int j = 0; j<boardLine.length(); j++){

                Character current = boardLine.charAt(j);
                if(current == '.'){
                    variables.add(new Variable(current, null));
                }
                else{
                    variables.add(new Variable(current, new ArrayList<>(Arrays.asList(current)))); //Arrays.asList(current)
                }

            }
            sudokus.add(new Sudoku(id, difficulty, variables));

        }

        return sudokus;

    }

    private List<String> readLinesFromFile(File file){
        List<String> lines = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                lines.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return lines;
    }
}
