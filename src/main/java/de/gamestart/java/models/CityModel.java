package de.gamestart.java.models;

import de.gamestart.java.Database;
import de.gamestart.java.data.City;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityModel{
    public static final String TABLE_CREATION =
            "CREATE TABLE IF NOT EXISTS city " +
            "(cityId INTEGER PRIMARY KEY AUTOINCREMENT," +
            "cityName            TEXT   NOT NULL," +
            "cityPosLong            DOUBLE NOT NULL," +
            "cityPoslat            DOUBLE NOT NULL)";


    public static boolean insert(int cityId, String cityName, double cityPoslong, double cityPoslat) {
        return Database.executeUpdate(
                    String.format("INSERT INTO city (cityId, cityName, cityPoslong, cityPoslat) VALUES (%d, \"%s\", %f, %f);",
                            cityId, cityName, cityPoslong, cityPoslat)
        );
    }

    public static boolean delete(String name) {
        return Database.executeUpdate(
                    String.format("DELETE FROM city WHERE cityName == \"%s\";",
                            name)
        );
    }

    public static void deleteAll() {
        Database.executeUpdate(
                String.format("DELETE FROM city WHERE cityId >= 0;")
        );
    }

    public static City find(String name) {
        ResultSet result = Database.executeQuery(
                String.format("SELECT * FROM city WHERE cityName == \"%s\";",
                        name)
        );

        try {
            return new City(
                    result.getInt("cityId"),
                    result.getString("cityName"),
                    result.getDouble("cityPoslat"),
                    result.getDouble("cityPoslong")
            );
        } catch (SQLException exception) {
            System.out.println("Failed to create City Object");
        }
        return null;
    }
}
