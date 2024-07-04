package org.gmdev.genetic;

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

    @Test
    void itShouldSetSolutionAsString() {
        // Given
        String solution = "1111000000000000000000000000000000000000000000000000000000001111";
        assertThat(fitnessCalculator.getSolution()).isNull();

        // When
        fitnessCalculator.setSolutionFromString(solution);
        byte[] candidateSolution = fitnessCalculator.getSolution();

        // Then
        assertThat(candidateSolution).hasSize(FitnessCalculator.SOLUTION_SIZE);
        for (int i = 0; i < solution.length(); i++) {
            char charSolutionElement = solution.charAt(i);
            byte byteSolutionElement = candidateSolution[i];
            assertThat(Character.toString(charSolutionElement)).isEqualTo(String.valueOf(byteSolutionElement));
        }
    }

    @Test
    void itShouldThrowIfSolutionInputSizeIsNotValid() {
        // Given a not valid size solution1 string
        String solution1 = "11110";

        // When - Then
        assertThatThrownBy(() -> fitnessCalculator.setSolutionFromString(solution1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("String solution size must be equal to: 64, but it is 5");

        // Given a not valid value solution1 string
        String solution2 = "1111000000000000000000000000000000000000000000000000000000001112";

        // When - Then
        assertThatThrownBy(() -> fitnessCalculator.setSolutionFromString(solution2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Value 2 cannot be accepted");
    }


}