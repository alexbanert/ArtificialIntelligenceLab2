package com.company.utils;

import com.company.models.Jolka;
import com.company.models.Variable;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class JolkaParser {
    File puzzleFile;
    File wordsFile;
    public JolkaParser(String puzzleFileName, String wordsFileName) throws FileNotFoundException{
        Path currentPath = Paths.get(System.getProperty("user.dir"));
        Path puzzleFilePath = Paths.get(currentPath.toString(), "data", "Jolka", puzzleFileName);
        Path wordsFilePath = Paths.get(currentPath.toString(), "data", "Jolka", wordsFileName);
        //dataFile = new File(filePath);
        if (puzzleFilePath.toFile().exists() && wordsFilePath.toFile().exists()) {
            puzzleFile = puzzleFilePath.toFile();
            wordsFile = wordsFilePath.toFile();
        } else {
            throw new FileNotFoundException();
        }
    }

    public Jolka getJolka(){

        List<String> puzzleContent = readLinesFromFile(puzzleFile);
        List<String> wordsContent = readLinesFromFile(wordsFile);

        int height = puzzleContent.size();
        int width = puzzleContent.get(0).length();

        List<String> words = new ArrayList<>();
        for (int i = 0; i < wordsContent.size(); i++) {
            String line = wordsContent.get(i);
            words.add(line);
        }
        ArrayList<Variable> variables = new ArrayList<>();

        // Initialize domain as all unique letters
        Variable.setDefaultDomain(createDomain((ArrayList<String>) words));
        Variable.setDefaultChar('_');

        for (int i = 0; i < puzzleContent.size(); i++) {
            String line = puzzleContent.get(i);
            for(int j = 0 ; j < line.length(); j++){
                Character currentChar = line.charAt(j);
                Variable variable;
                if(currentChar.equals('#')){
                    variable = new Variable(currentChar, new ArrayList<>(Arrays.asList(currentChar)));
                }
                else {
                    variable = new Variable(currentChar, null);
                }
                variables.add(variable);
            }
        }
        return new Jolka(1, variables, (ArrayList<String>) words, height, width);

    }

    private ArrayList<Character> createDomain(ArrayList<String> words){
        Set<Character> availableCharacters = new HashSet<>();
        for(int i = 0; i < words.size(); i++){
            String line = words.get(i);
            for(int j=0; j<line.length(); j++){
                availableCharacters.add(line.charAt(j));
            }
        }
        return new ArrayList<>(availableCharacters);
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

