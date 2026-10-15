package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class Main
{
    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect()
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
                Thread.sleep(30000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "password");
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

    public static void main(String[] args)
    {
        Main a = new Main();
        a.connect();

        ArrayList<Country> countries = a.getAllCountriesSorted();
        if (countries != null)
        {
            for (Country cnt : countries)
            {
                System.out.println(cnt.code + " " + cnt.name + " " + cnt.continent + " " + cnt.population);
            }
        }

        a.disconnect();
    }
}
