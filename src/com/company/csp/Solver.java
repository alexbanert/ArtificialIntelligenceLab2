package com.company.csp;

import com.company.models.Puzzle;
import com.company.models.Variable;
import com.company.utils.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Solver {

    private boolean skipForwardChecking = true;
    private ChooseFromDomainType chooseType;

    public Solver(SolverType solverType,ChooseFromDomainType chooseType){
        if(solverType.equals(SolverType.FORWARDCHECK)){
            skipForwardChecking = false;
        }
        this.chooseType = chooseType;
    }

    public Result solve(Puzzle puzzle){
        List<Puzzle> solved = new ArrayList<>();
        ListIterator<Variable> variablesIterator = puzzle.getVariables().listIterator();
        Variable currentVariable = variablesIterator.next();


        // Results
        long startTime = System.currentTimeMillis();
        long firstSolutionTime = -1;
        int firstSolutionNodes =-1;
        int firstSolutionReturns = -1;
        int totalNodes = 0;
        int totalReturns = 0;
        int solutionsFound = 0;

        do{
            // Skip variables which are constant
            while(currentVariable.isConstant() && variablesIterator.hasNext()){
                currentVariable = variablesIterator.next();
            }

            // Non-constant variable
            while(!currentVariable.getDomain().isEmpty() && !currentVariable.isConstant()){

                // Choose next value from domain
                Character consideration = currentVariable.chooseFromDomain(chooseType);
                totalNodes++;


                if(puzzle.isCorrect(currentVariable, consideration)){
                    currentVariable.setValue(consideration);
                    if(skipForwardChecking || forwardCheck(puzzle, currentVariable)) {
                        if (variablesIterator.hasNext()) {
                            currentVariable = variablesIterator.next();
                            while (currentVariable.isConstant()) {
                                if (variablesIterator.hasNext()) {
                                    currentVariable = variablesIterator.next();
                                } else {
                                    if (solutionsFound == 0) {
                                        firstSolutionTime = System.currentTimeMillis() - startTime;
                                        firstSolutionNodes = totalNodes;
                                        firstSolutionReturns = totalReturns;
                                    }
                                    solutionsFound++;
                                    variablesIterator.previous();
                                    while (currentVariable.isConstant())
                                        currentVariable = variablesIterator.previous();
                                    variablesIterator.next();
                                    break;
                                }
                            }
                            break;
                        } else {
                            if (solutionsFound == 0) {
                                firstSolutionTime = System.currentTimeMillis() - startTime;
                                firstSolutionNodes = totalNodes;
                                firstSolutionReturns = totalReturns;
                            }
                            solutionsFound++;
                            solved.add(puzzle);
                        }
                    }

                }
            }


            if((currentVariable.getDomain().size() == 0 && !currentVariable.isConstant() && variablesIterator.hasPrevious())){
                variablesIterator.previous();
                while ((currentVariable.getDomain().isEmpty() && variablesIterator.hasPrevious()) || (currentVariable.isConstant() && variablesIterator.hasPrevious())){
                    currentVariable.resetVariable();
                    currentVariable = variablesIterator.previous();
                    totalReturns++;
                    if(!variablesIterator.hasPrevious() && currentVariable.isConstant()){
                        long totalTime = System.currentTimeMillis() - startTime;
                        return new Result(puzzle.getId(), firstSolutionTime, firstSolutionNodes, firstSolutionReturns, totalTime, totalNodes, totalReturns, solutionsFound);
                    }
                }
                variablesIterator.next();
            }
        } while(variablesIterator.hasPrevious() && !currentVariable.getDomain().isEmpty());


        long totalTime = System.currentTimeMillis() - startTime;
        return new Result(puzzle.getId(), firstSolutionTime, firstSolutionNodes, firstSolutionReturns, totalTime, totalNodes, totalReturns, solutionsFound);
    }


    private boolean forwardCheck(Puzzle puzzle, Variable variable){
        ArrayList<Variable> variables = puzzle.getVariables();
        Character[] domain = puzzle.getAvailableCharacters().toArray(new Character[0]);
        for(int i = variables.indexOf(variable) + 1; i < variables.size(); i++){
            Variable compared = variables.get(i);
            if(!compared.isConstant()) {
                boolean domainEmpty = true;
                for (int j = 0; j < domain.length; j++) {
                    if (puzzle.isCorrect(compared, domain[j])) {
                        domainEmpty = false;
                        break;
                    }
                }
                if(domainEmpty){
                    return false;
                }
            }
        }
        return true;
    }
}
