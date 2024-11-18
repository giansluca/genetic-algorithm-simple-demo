package org.gmdev.genetic;

public class Algorithm {

    private static final double UNIFORM_RATE = 0.5;
    private static final double MUTATION_RATE = 0.015;
    private static final int TOURNAMENT_SIZE = 5;
    private static final boolean ELITISM = true;
    
    /**
     * Evolve a population
     */
    public static Population evolvePopulation(Population pop, FitnessCalculator fitnessCalculator) {
        Population newPopulation = new Population(pop.size(), false, fitnessCalculator);

        // keep our best individual
        if (ELITISM)
            newPopulation.saveIndividual(0, pop.getFittest());

        int elitismOffset = ELITISM ? 1 : 0;

        // crossover population
        // loop over the population size and create new individuals with crossover
        for (int i = elitismOffset; i < pop.size(); i++) {
            Individual individual1 = tournamentSelection(pop, fitnessCalculator);
            Individual individual2 = tournamentSelection(pop, fitnessCalculator);
            Individual newIndividual = crossover(individual1, individual2, fitnessCalculator);
            newPopulation.saveIndividual(i, newIndividual);
        }

        // mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++)
            mutate(newPopulation.getIndividual(i));

        return newPopulation;
    }
    
    /**
     * Crossover individuals
     */
    private static Individual crossover(Individual individual1,
                                        Individual individual2,
                                        FitnessCalculator fitnessCalculator) {

        Individual newIndividual = new Individual(fitnessCalculator);
        
        // loop through genes
        for (int i = 0; i < individual1.getGenes().length; i++) {
            // crossover
            if (Math.random() <= UNIFORM_RATE) {
                newIndividual.setGene(i, individual1.getGene(i));
            } else {
                newIndividual.setGene(i, individual2.getGene(i));
            }
        }
        
        return newIndividual;
    }
    
    /**
     * Select individuals for crossover
     */
    private static Individual tournamentSelection(Population pop, FitnessCalculator fitnessCalculator) {
        // create a tournament population
        Population tournament = new Population(TOURNAMENT_SIZE, false, fitnessCalculator);
        
        // for each place in the tournament get a random individual
        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        
        // get the fittest
        return tournament.getFittest();
    }
    
    /**
     * Mutate an individual
     */
    private static void mutate(Individual indiv) {
        // loop through genes
        for (int i = 0; i < indiv.getGenes().length; i++) {
            if (Math.random() <= MUTATION_RATE) {
                // create random gene
                byte gene = (byte) Math.round(Math.random());
                indiv.setGene(i, gene);
            }
        }
    }

}
