package com.company.utils;

import java.util.ArrayList;

public class ResultConsoleLogger {
    public static void logResults(ArrayList<Result> results){
        int n = results.size();
        double firstTimeAvg=0;
        double firstSolutionNodesAvg=0;
        double firstSolutionReturnsAvg=0;
        double totalTimeAvg=0;
        double totalNodesAvg=0;
        double totalReturnsAvg=0;
        for(int i = 0 ; i < n; i++){
            Result result = results.get(i);
            firstTimeAvg+=result.getFirstSolutionTime();
            firstSolutionNodesAvg+=result.getFirstSolutionNodes();
            firstSolutionReturnsAvg+=result.getFirstSolutionReturns();
            totalTimeAvg+=result.getTotalTime();
            totalNodesAvg+=result.getTotalNodes();
            totalReturnsAvg+=result.getTotalReturns();
        }
        firstTimeAvg = firstTimeAvg/n;
        firstSolutionNodesAvg=firstSolutionNodesAvg/n;
        firstSolutionReturnsAvg=firstSolutionReturnsAvg/n;
        totalTimeAvg=totalTimeAvg/n;
        totalNodesAvg=totalNodesAvg/n;
        totalReturnsAvg=totalReturnsAvg/n;

        System.out.println(
                "Srednia z " + n + " prób" +
                "\nId:" + results.get(0).getPuzzleId() +
                "\nCzas do pierwszego rozwiązania: " + firstTimeAvg + " ms" +
                "\nLiczba węzlow do pierwszego rozwiązania: " + firstSolutionNodesAvg +
                "\nLiczba nawrotów do pierwszego rozwiązania: " + firstSolutionReturnsAvg +
                "\nŁączny czas: " + totalTimeAvg + " ms" +
                "\nŁącznie węzłów: " + totalNodesAvg +
                "\nŁącznie nawrotów: " + totalReturnsAvg +
                "\nZnalezionych rozwiązań: " + results.get(0).getSolutionsFound());

    }
}
