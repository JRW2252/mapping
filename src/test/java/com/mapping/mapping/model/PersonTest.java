package com.mapping.mapping.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapping.mapping.constant.Genre;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

import static org.junit.Assert.assertTrue;

public class PersonTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void testPerson_toJson_defaultMapper() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Person person = new Person(1, "Jimmy", "John", Collections.singletonList(
            new Movie(1, "Sponge Bob Square Pants", Genre.FAMILY)
        ));
        String result = objectMapper.writeValueAsString(person);

        logger.info(result);

        assertTrue(result.contains("Id"));
        assertTrue(result.contains("FirstName"));
        assertTrue(result.contains("LastName"));
        assertTrue(result.contains("FavoriteMovies"));
    }

    @Test
    public void testPerson_toJson_overrideMapper() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(MapperFeature.USE_ANNOTATIONS);
        Person person = new Person(1, "Jimmy", "John", Collections.singletonList(
            new Movie(1, "Sponge Bob Square Pants", Genre.FAMILY)
        ));
        String result = objectMapper.writeValueAsString(person);

        logger.info(result);

        assertTrue(result.contains("id"));
        assertTrue(result.contains("firstName"));
        assertTrue(result.contains("lastName"));
        assertTrue(result.contains("favoriteMovies"));
    }
}