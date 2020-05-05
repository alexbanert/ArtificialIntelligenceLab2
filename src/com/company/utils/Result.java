package com.company.utils;

public class Result {
    private int puzzleId;
    private long firstSolutionTime;
    private int firstSolutionNodes;
    private int firstSolutionReturns;
    private long totalTime;
    private int totalNodes;
    private int totalReturns;
    private int solutionsFound;

    public Result(int puzzleId, long firstSolutionTime, int firstSolutionNodes, int firstSolutionReturns, long totalTime, int totalNodes, int totalReturns, int solutionsFound) {
        this.puzzleId = puzzleId;
        this.firstSolutionTime = firstSolutionTime;
        this.firstSolutionNodes = firstSolutionNodes;
        this.firstSolutionReturns = firstSolutionReturns;
        this.totalTime = totalTime;
        this.totalNodes = totalNodes;
        this.totalReturns = totalReturns;
        this.solutionsFound = solutionsFound;
    }

    public int getPuzzleId() {
        return puzzleId;
    }

    public long getFirstSolutionTime() {
        return firstSolutionTime;
    }

    public int getFirstSolutionNodes() {
        return firstSolutionNodes;
    }

    public int getFirstSolutionReturns() {
        return firstSolutionReturns;
    }

    public long getTotalTime() {
        return totalTime;
    }

    public int getTotalNodes() {
        return totalNodes;
    }

    public int getTotalReturns() {
        return totalReturns;
    }

    public int getSolutionsFound() {
        return solutionsFound;
    }

    @Override
    public String toString() {
        return "\nId:" + puzzleId +
                "\nCzas do pierwszego rozwiązania: " + firstSolutionTime + " ms" +
                "\nLiczba węzlow do pierwszego rozwiązania: " + firstSolutionNodes +
                "\nLiczba nawrotów do pierwszego rozwiązania: " + firstSolutionReturns +
                "\nŁączny czas: " + totalTime + " ms" +
                "\nŁącznie węzłów: " + totalNodes +
                "\nŁącznie nawrotów: " + totalReturns +
                "\nZnalezionych rozwiązań: " + solutionsFound;
    }
}
