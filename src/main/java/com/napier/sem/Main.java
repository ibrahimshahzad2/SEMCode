package com.napier.sem;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect(String location, int delay)
    {
        try
        {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(delay);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location
                        + "/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "password");
                System.out.println("Successfully connected");
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect()
    {
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public Country getCountry(String Code)
    {
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country "
                            + "WHERE Code = '" + Code + "'";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return new country if valid. Check one is returned
            if (rset.next())
            {
                Country cnt = new Country();
                cnt.code = rset.getString("Code");
                cnt.name = rset.getString("Name");
                cnt.continent = rset.getString("Continent");
                cnt.region = rset.getString("Region");
                cnt.population = rset.getInt("Population");
                return cnt;
            }
            else
                return null;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get country details");
            return null;
        }
    }

    public void printCountries(ArrayList<Country> countries)
    {
        // Check countries list is not null
        if (countries == null)
        {
            System.out.println("No countries");
            return;
        }

        // Print header / rows
        for (Country cnt : countries)
        {
            if (cnt == null)
                continue;

            System.out.println(cnt.code + " " + cnt.name + " " + cnt.continent + " " + cnt.population);
        }
    }

    public void outputCountries(ArrayList<Country> countries, String filename)
    {
        if (countries == null)
        {
            System.out.println("No countries");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("| Code | Name | Continent | Region | Population |\r\n");
        sb.append("| --- | --- | --- | --- | --- |\r\n");

        for (Country cnt : countries)
        {
            if (cnt == null)
                continue;

            sb.append("| ")
                    .append(cnt.code).append(" | ")
                    .append(cnt.name).append(" | ")
                    .append(cnt.continent).append(" | ")
                    .append(cnt.region).append(" | ")
                    .append(cnt.population).append(" |\r\n");
        }

        try
        {
            File dir = new File("./reports/");
            if (!dir.exists())
            {
                dir.mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(new File(dir, filename)));
            writer.write(sb.toString());
            writer.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<Country> getAllCountriesSorted()
    {
        try
        {
            Statement stmt = con.createStatement();
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country "
                            + "ORDER BY Population DESC";
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country cnt = new Country();
                cnt.code = rset.getString("Code");
                cnt.name = rset.getString("Name");
                cnt.continent = rset.getString("Continent");
                cnt.region = rset.getString("Region");
                cnt.population = rset.getInt("Population");
                countries.add(cnt);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries sorted by population");
            return null;
        }
    }

    public ArrayList<Country> getTopCountries(int n)
    {
        try
        {
            Statement stmt = con.createStatement();
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country "
                            + "ORDER BY Population DESC "
                            + "LIMIT " + n;
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country cnt = new Country();
                cnt.code = rset.getString("Code");
                cnt.name = rset.getString("Name");
                cnt.continent = rset.getString("Continent");
                cnt.region = rset.getString("Region");
                cnt.population = rset.getInt("Population");
                countries.add(cnt);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get top countries");
            return null;
        }
    }

    public List<Country> getCountriesByContinentAndPopulation(String continent)
    {
        try
        {
            Statement stmt = con.createStatement();
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country "
                            + "WHERE Continent = '" + continent + "' "
                            + "ORDER BY Population DESC";
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country cnt = new Country();
                cnt.code = rset.getString("Code");
                cnt.name = rset.getString("Name");
                cnt.continent = rset.getString("Continent");
                cnt.region = rset.getString("Region");
                cnt.population = rset.getInt("Population");
                countries.add(cnt);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries by continent");
            return null;
        }
    }

    public List<Country> getCountriesByPopulationLtS()
    {
        try
        {
            Statement stmt = con.createStatement();
            String strSelect =
                    "SELECT Code, Name, Continent, Region, Population, Capital "
                            + "FROM country "
                            + "ORDER BY Population DESC";
            ResultSet rset = stmt.executeQuery(strSelect);

            ArrayList<Country> countries = new ArrayList<Country>();
            while (rset.next())
            {
                Country cnt = new Country();
                cnt.code = rset.getString("Code");
                cnt.name = rset.getString("Name");
                cnt.continent = rset.getString("Continent");
                cnt.region = rset.getString("Region");
                cnt.population = rset.getInt("Population");
                countries.add(cnt);
            }
            return countries;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countries");
            return null;
        }
    }

    public static void main(String[] args)
    {
        Main a = new Main();

        if (args.length < 1)
        {
            // Defaults to port 33060 for local IntelliJ execution against Docker
            a.connect("localhost:33060", 5000);
        }
        else
        {
            a.connect(args[0], Integer.parseInt(args[1]));
        }

        ArrayList<Country> countries = a.getAllCountriesSorted();
        a.printCountries(countries);
        a.outputCountries(countries, "CountriesByPopulation.md");

        a.disconnect();
    }
}