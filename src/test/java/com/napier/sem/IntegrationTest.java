package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest
{
    static Main app;

    @BeforeAll
    static void init()
    {
        app = new Main();
        // Connect to the database container for integration tests
        app.connect();
    }

    @Test
    void testGetCountriesByContinentAndPopulation()
    {
        List<Country> countries = app.getCountriesByContinentAndPopulation("Europe");

        assertNotNull(countries);
        assertFalse(countries.isEmpty());
        assertEquals("Russian Federation", countries.get(0).name);
    }

    @Test
    void testgetCountriesByPopulationLtS()
    {
        List<Country> countries = app.getCountriesByPopulationLtS();
        assertNotNull(countries);
        assertFalse(countries.isEmpty());
        Assertions.assertEquals("China", countries.get(0).name);
    }
}