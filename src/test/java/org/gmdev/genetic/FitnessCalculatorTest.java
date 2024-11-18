package org.gmdev.genetic;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.gmdev.utils.GeneticUtils.getByteArrayFromBytesString;

public class FitnessCalculatorTest {

    @Test
    void itShouldThrowIfSolutionStringInputSizeIsNotValid() {
        // Given a null solution string
        // When - Then
        Throwable thrown = catchThrowable(() -> new FitnessCalculator(""));
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("solutionString cannot be empty");

        // Given a not valid size solution1 string
        String solutionString1 = "11110";

        // When - Then
        assertThatThrownBy(() -> new FitnessCalculator(solutionString1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("solutionString size must be equal to: 64, but it is 5");

        // Given a not valid value solution1 string
        String solutionString2 = "1111000000000000000000000000000000000000000000000000000000001112";

        // When - Then
        assertThatThrownBy(() -> new FitnessCalculator(solutionString2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Value 2 cannot be accepted");
    }

    @Test
    void itShouldSetCandidateSolutionFromString() {
        // Given
        String solutionString = "1111000000000000000000000000000000000000000000000000000000001111";
        FitnessCalculator fitnessCalculator = new FitnessCalculator(solutionString);

        // When
        byte[] candidateSolution = fitnessCalculator.getCandidateSolution();

        // Then
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
        // Given
        String solutionString = "1111000000000000000000000000000000000000000000000000000000001111";
        FitnessCalculator fitnessCalculator = new FitnessCalculator(solutionString);

        // last 4 bytes are different from solution
        String genesString = "1111000000000000000000000000000000000000000000000000000000000000";
        byte[] genes = getByteArrayFromBytesString(genesString, genesString.length());
        Individual individual = new Individual(genes, fitnessCalculator);

        // When
        int fitness = fitnessCalculator.getFitness(individual);

        // Then
        assertThat(fitness).isEqualTo(fitnessCalculator.getMaxFitness() -4);
        assertThat(fitness).isEqualTo(FitnessCalculator.SOLUTION_SIZE -4);
    }

    @Test
    void itShouldGetIndividualMaxFitness() {
        // Given
        String solutionString = "1111000000000000000000000000000000000000000000000000000000001111";
        FitnessCalculator fitnessCalculator = new FitnessCalculator(solutionString);

        String genesString = "1111000000000000000000000000000000000000000000000000000000001111";
        byte[] genes = getByteArrayFromBytesString(genesString, genesString.length());
        Individual individual = new Individual(genes, fitnessCalculator);

        // When
        int fitness = fitnessCalculator.getFitness(individual);

        // Then
        assertThat(fitness).isEqualTo(fitnessCalculator.getMaxFitness());
        assertThat(fitness).isEqualTo(FitnessCalculator.SOLUTION_SIZE);
    }

}