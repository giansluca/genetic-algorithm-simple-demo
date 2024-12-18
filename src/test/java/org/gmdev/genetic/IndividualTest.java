package org.gmdev.genetic;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.gmdev.utils.GeneticUtils.getByteArrayFromBytesString;

class IndividualTest {

    @Test
    void itShouldCreateAnIndividualWithRandomGenes() {
        // given
        String solutionString = "1111000000000000000000000000000000000000000000000000000000001111";
        FitnessCalculator fitnessCalculator = new FitnessCalculator(solutionString);

        // when
        Individual individual = new Individual(fitnessCalculator);

        // then
        assertThat(individual).isNotNull();
        assertThat(individual.getFitnessCalculator()).isNotNull();
        assertThat(individual.getGenes()).isNotNull();
        assertThat(individual.getGenes().length).isEqualTo(FitnessCalculator.SOLUTION_SIZE);
    }

    @Test
    void itShouldCreateAnIndividualWithGivenGenes() {
        // given
        String solutionString = "1111000000000000000000000000000000000000000000000000000000001111";
        FitnessCalculator fitnessCalculator = new FitnessCalculator(solutionString);

        String genesString = "1111000000000000000000000000000000000000000000000000000000000000";
        byte[] genes = getByteArrayFromBytesString(genesString, genesString.length());

        // when
        Individual individual = new Individual(genes, fitnessCalculator);

        // then
        assertThat(individual).isNotNull();
        assertThat(individual.getFitnessCalculator()).isNotNull();
        assertThat(individual.getGenes()).isNotNull();

        IntStream.range(0, genes.length).forEach(i ->
                assertThat(genes[i]).isEqualTo(individual.getGenes()[i])
        );
    }

}