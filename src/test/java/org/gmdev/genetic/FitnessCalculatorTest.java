package org.gmdev.genetic;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class FitnessCalculatorTest {

    private FitnessCalculator fitnessCalculator;

    @BeforeEach
    void setUp() {
        fitnessCalculator = FitnessCalculator.getInstance();
    }

    @AfterEach
    void tearDown() {
        fitnessCalculator.resetCandidateSolution();
    }

    @Test
    void itShouldThrowIfSolutionStringInputSizeIsNotValid() {
        // Given a null solution string

        // When - Then
        assertThatThrownBy(() -> fitnessCalculator.setCandidateSolution(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("solutionString cannot be null");

        // Given a not valid size solution1 string
        String solutionString1 = "11110";

        // When - Then
        assertThatThrownBy(() -> fitnessCalculator.setCandidateSolution(solutionString1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("solutionString size must be equal to: 64, but it is 5");

        // Given a not valid value solution1 string
        String solutionString2 = "1111000000000000000000000000000000000000000000000000000000001112";

        // When - Then
        assertThatThrownBy(() -> fitnessCalculator.setCandidateSolution(solutionString2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Value 2 cannot be accepted");
    }

    @Test
    void itShouldSetCandidateSolutionFromString() {
        // Given
        String solutionString = "1111000000000000000000000000000000000000000000000000000000001111";
        assertThat(fitnessCalculator.getCandidateSolution()).isNull();

        // When
        fitnessCalculator.setCandidateSolution(solutionString);
        byte[] candidateSolution = fitnessCalculator.getCandidateSolution();

        // Then
        assertThat(candidateSolution).hasSize(FitnessCalculator.SOLUTION_SIZE);
        for (int i = 0; i < solutionString.length(); i++) {
            char charSolutionElement = solutionString.charAt(i);
            byte byteSolutionElement = candidateSolution[i];

            assertThat(Character.toString(charSolutionElement)).isEqualTo(String.valueOf(byteSolutionElement));
        }
    }

    @Test
    void itShouldThrowGettingIndividualFitnessIfCandidateSolutionIsNotSet() {
        // Given
        String genesString = "1111000000000000000000000000000000000000000000000000000000001110";
        byte[] genes = fitnessCalculator.fromBytesString(genesString, genesString.length());
        Individual individual = new Individual(genes);

        // When - Then
        assertThatThrownBy(() -> fitnessCalculator.getFitness(individual))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("candidateSolution cannot be null");

    }

    @Test
    void itShouldGetIndividualFitness() {
        // Given
        String solutionString = "1111000000000000000000000000000000000000000000000000000000001111";
        fitnessCalculator.setCandidateSolution(solutionString);

        String genesString = "1111000000000000000000000000000000000000000000000000000000001110";
        byte[] genes = fitnessCalculator.fromBytesString(genesString, genesString.length());
        Individual individual = new Individual(genes);

        // When
        int fitness = fitnessCalculator.getFitness(individual);

        // Then
        assertThat(fitness).isEqualTo(fitnessCalculator.getMaxFitness() -1);
        assertThat(fitness).isEqualTo(63);
    }

}