package org.gmdev.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.gmdev.utils.MapperFactory.getMapper;

public class MapperFactoryTest {

    @Test
    void itShouldGetMapperAndSerializeALocalDateTime() throws JsonProcessingException {
        // Given
        var mapper = getMapper();

        LocalDateTime date = LocalDateTime.of(2024, 5, 20, 2, 30, 45);

        // When
        String dateString = mapper.writeValueAsString(date);

        // Then
        assertThat(dateString).containsSequence("2024-05-20T02:30:45");
        assertThat(dateString).isEqualTo("\"2024-05-20T02:30:45\"");
    }
}