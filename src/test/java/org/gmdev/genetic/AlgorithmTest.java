package org.gmdev.genetic;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class AlgorithmTest {

    @ParameterizedTest
    @CsvSource(value = {
            "10",
            "60",
            "1000",
    }, nullValues = "null")
    void itShouldGetRandomIntegerInARangeFromZeroTo(int to) {
        // Given When Then
        for (int i = 0; i < 1000; i++) {
            int randomInt = Algorithm.getRandomIntFroZeroTo(to);
            assertThat(randomInt).isLessThanOrEqualTo(to);
        }
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1.00",
            "0.015",
            "0.05",
    }, nullValues = "null")
    void itShouldGetRandomDoubleInARangeFromZeroTo(double to) {
        // Given When Then
        for (int i = 0; i < 1000; i++) {
            double randomDouble = Algorithm.getRandomDoubleFroZeroTo(to, 4);
            assertThat(randomDouble).isLessThanOrEqualTo(to);
        }
    }

}