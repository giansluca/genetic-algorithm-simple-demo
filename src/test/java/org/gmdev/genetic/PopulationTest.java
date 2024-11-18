package org.gmdev.genetic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.gmdev.Main.SOLUTION_STRING;

class PopulationTest {

    private FitnessCalculator fitnessCalculator;

    @BeforeEach
    void setUp() {
        fitnessCalculator = new FitnessCalculator(SOLUTION_STRING);
    }

    @Test
    void itShouldCreateAndInitializeAPopulationOfIndividuals() {
        // given
        int populationSize = 90;

        // when
        Population population = new Population(populationSize, true, fitnessCalculator);

        // then
        assertThat(population.getIndividuals()).hasSize(populationSize);
    }

    @Test
    void itShouldCreateAndNotInitializeAPopulationOfIndividuals() {
        // given
        int populationSize = 90;

        // when
        Population population = new Population(populationSize, false, fitnessCalculator);

        // then
        assertThat(population.getIndividuals()).hasSize(0);
    }

}