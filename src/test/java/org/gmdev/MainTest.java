package org.gmdev;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class MainTest {

    @Test
    public void shouldAnswerWithTrue() {
        int number = 5;
        assertThat(number).isEqualTo(5);
    }

}
