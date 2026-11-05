package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static Main app;

    @BeforeAll
    static void init()
    {
        app = new Main();
    }

    @Test
    void printCountriesTestNull()
    {
        app.printCountries(null);
    }

    @Test
    void printCountriesTestEmpty()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        app.printCountries(countries);
    }

    @Test
    void printCountriesTestContainsNull()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        countries.add(null);
        app.printCountries(countries);
    }

    @Test
    void printCountries()
    {
        ArrayList<Country> countries = new ArrayList<Country>();
        Country country = new Country();
        country.code = "GBR";
        country.name = "United Kingdom";
        country.continent = "Europe";
        country.population = 67886004;
        countries.add(country);
        app.printCountries(countries);
    }
}