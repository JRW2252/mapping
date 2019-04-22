package com.mapping.mapping.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapping.mapping.constant.Genre;
import com.mapping.mapping.util.FileUtil;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PersonTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    private ObjectMapper objectMapper;

    @Before
    public void setup() {
        objectMapper = new ObjectMapper(); // expensive
    }

    @Test
    public void testPerson_toJson_defaultMapper() throws JsonProcessingException {
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

    @Test
    public void testPerson_toPeron_UpperCase_defaultMapper() throws IOException, URISyntaxException {
        String json = FileUtil.loadResource("person_UpperCase.json");
        logger.info(json);
        Person person = objectMapper.readValue(json, Person.class);
        assertNotNull(person);
        assertEquals(Integer.valueOf(1), person.getId());
        assertEquals("Jimmy", person.getFirstName());
        assertEquals("John", person.getLastName());
        assertNotNull(person.getFavoriteMovies());
        assertEquals(1, person.getFavoriteMovies().size());
    }

    @Test
    public void testPerson_toPeron_camelCase_overrideMapper() throws IOException, URISyntaxException {
        String json = FileUtil.loadResource("person_camelCase.json");
        objectMapper.disable(MapperFeature.USE_ANNOTATIONS);
        logger.info(json);
        Person person = objectMapper.readValue(json, Person.class);
        assertNotNull(person);
        assertEquals(Integer.valueOf(1), person.getId());
        assertEquals("Jimmy", person.getFirstName());
        assertEquals("John", person.getLastName());
        assertNotNull(person.getFavoriteMovies());
        assertEquals(1, person.getFavoriteMovies().size());
    }
}