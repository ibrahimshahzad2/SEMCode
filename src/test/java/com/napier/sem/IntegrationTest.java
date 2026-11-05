package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntegrationTest {

    static Main app;

    @BeforeAll
    static void init() {
        app = new Main();
        // Connect to your running Docker MySQL instance via port 33060
        app.connect("localhost:33060", 5000);
    }

    @Test
    void testGetCountry() {
        Country country = app.getCountry("USA");
        assertNotNull(country);
        assertEquals("United States", country.name);
        assertEquals("North America", country.continent);
    }

    @Test
    void testGetAllCountriesSorted() {
        ArrayList<Country> countries = app.getAllCountriesSorted();
        assertNotNull(countries);
        assertTrue(countries.size() > 0);
        // Verify it is sorted by population descending
        if (countries.size() > 1) {
            assertTrue(countries.get(0).population >= countries.get(1).population);
        }
    }

    @Test
    void testGetCountriesByContinentAndPopulation() {
        List<Country> countries = app.getCountriesByContinentAndPopulation("Asia");
        assertNotNull(countries);
        assertTrue(countries.size() > 0);
        assertEquals("Asia", countries.get(0).continent);
    }


    @Test
    void testTop5Countries()
    {
        ArrayList<Country> countries = app.getTopCountries(5);
        assertNotNull(countries);
        assertEquals(5, countries.size());
        assertEquals("China", countries.get(0).name);

        // This output will appear in your GitHub Actions workflow logs
        System.out.println("--- TOP 5 COUNTRIES ---");
        app.printCountries(countries); }
}