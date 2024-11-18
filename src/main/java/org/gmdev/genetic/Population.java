package org.gmdev.genetic;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Population {

    private final List<Individual> individuals;
    private final int populationSize;

    public Population(int populationSize, boolean initialize, FitnessCalculator fitnessCalculator) {
        this.individuals = new ArrayList<>(populationSize);
        this.populationSize = populationSize;

        if (initialize) {
            for (int i = 0; i < populationSize; i++)
                individuals.add(new Individual(fitnessCalculator));
        }
    }

    public Individual getIndividual(int index) {
        return individuals.get(index);
    }

    public void saveIndividual(int index, Individual individual) {
        individuals.add(index, individual);
    }

    public Individual getFittest() {
        Individual fittest = individuals.getFirst();

        for (var individual : individuals) {
            if (fittest.getFitness() <= individual.getFitness())
                fittest = individual;
        }

        return fittest;
    }

    public int size() {
        return this.populationSize;
    }

    
}
