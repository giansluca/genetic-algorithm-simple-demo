package org.gmdev;

import org.gmdev.genetic.Algorithm;
import org.gmdev.genetic.FitnessCalculator;
import org.gmdev.genetic.Population;

public class Main {

    public static final String SOLUTION_STRING = "1111000000000000000000000000000000000000000000000000000000001111";
    public static final int POPULATION_SIZE = 50;

    public static void main(String[] args) {
        // Set a candidate solution
        FitnessCalculator fitnessCalculator = new FitnessCalculator(SOLUTION_STRING);

        // Create an initial population
        Population pop = new Population(POPULATION_SIZE, true, fitnessCalculator);

        // Evolve our population until we reach an optimum solution
        int generationCount = 0;
        while (pop.getFittest().getFitness() < fitnessCalculator.getMaxFitness()) {
            generationCount++;
            System.out.println("Generation: " + generationCount + " Fittest: " + pop.getFittest().getFitness() + " Max Fitness: " + fitnessCalculator.getMaxFitness());
            pop = Algorithm.evolvePopulation(pop, fitnessCalculator);
        }

        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(pop.getFittest());
    }

}
