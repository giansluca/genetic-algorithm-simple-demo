package org.gmdev;

import org.gmdev.genetic.Algorithm;
import org.gmdev.genetic.FitnessCalculator;
import org.gmdev.genetic.Population;

public class Main {

    public static void main(String[] args) {
        // Set a candidate solution
        FitnessCalculator fitnessCalculator = FitnessCalculator.getInstance();
        fitnessCalculator.setSolutionFromString("1111000000000000000000000000000000000000000000000000000000001111");

        // Create an initial population
        Population myPop = new Population(50, true);

        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
        while (myPop.getFittest().getFitness() < fitnessCalculator.getMaxFitness()) {
            generationCount++;
            System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness() + " Max Fitness: " + fitnessCalculator.getMaxFitness());
            myPop = Algorithm.evolvePopulation(myPop);
        }

        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(myPop.getFittest());
    }

}
