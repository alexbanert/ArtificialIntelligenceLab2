package com.company;

import com.company.csp.*;
import com.company.models.Jolka;
import com.company.models.Sudoku;
import com.company.utils.JolkaParser;
import com.company.utils.Result;
import com.company.utils.ResultConsoleLogger;
import com.company.utils.SudokuParser;

import java.io.FileNotFoundException;
import java.util.ArrayList;

class Main {

    public static void main(String[] args) throws FileNotFoundException {


        //JolkaParser parser = new JolkaParser("puzzle3", "words3");
        //Jolka jolka = parser.getJolka();


        SudokuParser parser1 = new SudokuParser("Sudoku.csv");
        ArrayList<Sudoku> sudokus = (ArrayList<Sudoku>) parser1.getSudokus();

        System.out.println("-----TEST-----");
        Solver solver = new Solver(SolverType.BACKTRACK, ChooseFromDomainType.FIRST);

        //System.out.println(solver.solve(jolka));

        int n = 1;

        ArrayList<Result> results = new ArrayList<>();

        for(int i = 0; i < n; i++){
            results.add(solver.solve(sudokus.get(1)));
            sudokus = (ArrayList<Sudoku>) parser1.getSudokus();
        }
        ResultConsoleLogger.logResults(results);
        results.clear();

        for(int i = 0; i < n; i++){
            results.add(solver.solve(sudokus.get(15)));
            sudokus = (ArrayList<Sudoku>) parser1.getSudokus();
        }
        ResultConsoleLogger.logResults(results);
        results.clear();


        for(int i = 0; i < n; i++){
            results.add(solver.solve(sudokus.get(34)));
            sudokus = (ArrayList<Sudoku>) parser1.getSudokus();
        }
        ResultConsoleLogger.logResults(results);
        results.clear();


        for(int i = 0; i < n; i++){
            results.add(solver.solve(sudokus.get(42)));
            sudokus = (ArrayList<Sudoku>) parser1.getSudokus();
        }
        ResultConsoleLogger.logResults(results);
        results.clear();


        for(int i = 0; i < n; i++){
            results.add(solver.solve(sudokus.get(45)));
            sudokus = (ArrayList<Sudoku>) parser1.getSudokus();
        }

        ResultConsoleLogger.logResults(results);
        results.clear();

        //System.out.println(solver.solve(jolka));;


        //System.out.println(forwardCheckingSolver.solve(sudokus.get(42)));;

    }
}
