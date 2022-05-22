package de.gamestart.java.models;

import de.gamestart.java.Database;

public class AirportModel {
    public static final String TABLE_CREATION =
            "CREATE TABLE IF NOT EXISTS airport " +
                    "(airportId INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "airportName            TEXT           NOT NULL," +
                    "airportPoslong         DOUBLE         NOT NULL," +
                    "airportPoslat         DOUBLE         NOT NULL," +
                    "cityId                 INTEGER        NOT NULL" +

                    "CONSTRAINT fk_cityId FOREIGN KEY (cityId) REFERENCES city(cityId))";

    public static boolean insert(int airportId, String cityName, double cityPoslong, double cityPoslat, int cityId) {
        return Database.executeUpdate(
                String.format("INSERT INTO airport (airportId, airportName, airportPoslong, airportPoslat, cityId) VALUES (%d, \"%s\", %f, %f, %d);",
                        airportId, cityName, cityPoslong, cityPoslat, cityId)
        );
    }
}
