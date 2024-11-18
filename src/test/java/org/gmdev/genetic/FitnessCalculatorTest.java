package org.gmdev.genetic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.gmdev.utils.GeneticUtils.getByteArrayFromBytesString;

public class FitnessCalculatorTest {

    @Test
    void itShouldThrowIfSolutionStringInputSizeIsNotValid() {
        // given a null solution string
        // when - then
        Throwable thrown = catchThrowable(() -> new FitnessCalculator(""));
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("solutionString cannot be empty");

        // given a not valid size solution1 string
        String solutionString1 = "11110";

        // when - then
        assertThatThrownBy(() -> new FitnessCalculator(solutionString1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("solutionString size must be equal to: 64, but it is 5");

        // given a not valid value solution1 string
        String solutionString2 = "1111000000000000000000000000000000000000000000000000000000001112";

        // when - then
        assertThatThrownBy(() -> new FitnessCalculator(solutionString2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Value 2 cannot be accepted");
    }

    @Test
    void itShouldSetCandidateSolutionFromString() {
        // given
        String solutionString = "1111000000000000000000000000000000000000000000000000000000001111";
        FitnessCalculator fitnessCalculator = new FitnessCalculator(solutionString);

        // when
        byte[] candidateSolution = fitnessCalculator.getCandidateSolution();

        // then
        assertThat(candidateSolution).isNotNull();
        assertThat(candidateSolution).hasSize(FitnessCalculator.SOLUTION_SIZE);
        for (int i = 0; i < solutionString.length(); i++) {
            char charSolutionElement = solutionString.charAt(i);
            byte byteSolutionElement = candidateSolution[i];

            assertThat(Character.toString(charSolutionElement)).isEqualTo(String.valueOf(byteSolutionElement));
        }
    }

    @Test
    void itShouldGetIndividualFitnessOf60() {
        // given
        String solutionString = "1111000000000000000000000000000000000000000000000000000000001111";
        FitnessCalculator fitnessCalculator = new FitnessCalculator(solutionString);

        // last 4 bytes are different from solution
        String genesString = "1111000000000000000000000000000000000000000000000000000000000000";
        byte[] genes = getByteArrayFromBytesString(genesString, genesString.length());
        Individual individual = new Individual(genes, fitnessCalculator);

        // when
        int fitness = fitnessCalculator.getFitness(individual);

        // then
        assertThat(fitness).isEqualTo(fitnessCalculator.getMaxFitness() -4);
        assertThat(fitness).isEqualTo(FitnessCalculator.SOLUTION_SIZE -4);
    }

    @Test
    void itShouldGetIndividualMaxFitness() {
        // given
        String solutionString = "1111000000000000000000000000000000000000000000000000000000001111";
        FitnessCalculator fitnessCalculator = new FitnessCalculator(solutionString);

        String genesString = "1111000000000000000000000000000000000000000000000000000000001111";
        byte[] genes = getByteArrayFromBytesString(genesString, genesString.length());
        Individual individual = new Individual(genes, fitnessCalculator);

        // when
        int fitness = fitnessCalculator.getFitness(individual);

        // then
        assertThat(fitness).isEqualTo(fitnessCalculator.getMaxFitness());
        assertThat(fitness).isEqualTo(FitnessCalculator.SOLUTION_SIZE);
    }

}