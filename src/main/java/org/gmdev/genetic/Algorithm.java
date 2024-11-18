package org.gmdev.genetic;

public class Algorithm {

    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;
    
    /**
     * Evolve a population
     */
    public static Population evolvePopulation(Population pop, FitnessCalculator fitnessCalculator) {
        Population newPopulation = new Population(pop.size(), false, fitnessCalculator);

        // Keep our best individual
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
        }

        // Crossover population
        int elitismOffset;
        
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        
        // Loop over the population size and create new individuals with crossover
        for (int i = elitismOffset; i < pop.size(); i++) {
            Individual indiv1 = tournamentSelection(pop, fitnessCalculator);
            Individual indiv2 = tournamentSelection(pop, fitnessCalculator);
            Individual newIndiv = crossover(indiv1, indiv2, fitnessCalculator);
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }
    
    /**
     * Crossover individuals
     */
    private static Individual crossover(Individual indiv1, Individual indiv2, FitnessCalculator fitnessCalculator) {
        Individual newSol = new Individual(fitnessCalculator);
        
        // Loop through genes
        for (int i = 0; i < indiv1.getGenes().length; i++) {
            // Crossover
            if (Math.random() <= uniformRate) {
                newSol.setGene(i, indiv1.getGene(i));
            } else {
                newSol.setGene(i, indiv2.getGene(i));
            }
        }
        
        return newSol;
    }
    
    /**
     * Select individuals for crossover
     */
    private static Individual tournamentSelection(Population pop, FitnessCalculator fitnessCalculator) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false, fitnessCalculator);
        
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        
        // Get the fittest
        Individual fittest = tournament.getFittest();

        return fittest;
    }
    
    /**
     * Mutate an individual
     */
    private static void mutate(Individual indiv) {
        // Loop through genes
        for (int i = 0; i < indiv.getGenes().length; i++) {
            if (Math.random() <= mutationRate) {
                // Create random gene
                byte gene = (byte) Math.round(Math.random());
                indiv.setGene(i, gene);
            }
        }
    }


}
