package de.gamestart.java.models;

import de.gamestart.java.Database;
import de.gamestart.java.data.Airport;
import de.gamestart.java.data.City;
import de.gamestart.java.data.Flight;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FlightsModel {
    //TODO:: LARS ES IST UNTERSTRICHEN (departure)! DAS HEIßt ES IST FALSCH GESCHRIEBEN!!!!!!! bitte überall fixen danke <3
    //TODO:: Der schmutz hat mir bestimmt 1h errors geworfen xD
    //TODO:: Hier ist Felix 2h später - immer noch schmutz das du nicht schreiben kannst - und nein ich fixe das nicht!
    public static final String TABLE_CREATION =
            "CREATE TABLE IF NOT EXISTS flight " +
                    "(flightId                INTEGER             PRIMARY KEY AUTOINCREMENT, " +
                    "flightNumber       TEXT                NOT NULL, " +

                    "departureAirportId INTEGER            NOT NULL, " +
                    "departureTime      INTEGER            NOT NULL, " +
                    "departureDate      DATE                NOT NULL, " +

                    "arrivalAirportId   INTEGER             NOT NULL, " +
                    "arrivalTime        INTEGER             NOT NULL, " +
                    "arrivalDate        DATE                NOT NULL, " +

                    "CONSTRAINT fk_departureAirportId FOREIGN KEY (fk_departureAirportId) REFERENCES airport(airportId), " +
                    "CONSTRAINT fk_arrivalAirportId FOREIGN KEY (fk_arrivalAirportId) REFERENCES airport(airportId))";

    /*
    public static boolean insert(int id, String flightNumber, int departureCityId,
                                 int departureTime, Date departureDate, int arrivalCityId, int arrivalTime, Date arrivalDate) {
        return Database.executeUpdate(
                String.format("INSERT INTO flight (id, flightNumber, departureCity, departureTime, departureDate, arrival, arrivalTime, arrivalDate)" +
                                " VALUES (%d, \"%s\", %d, %d, %ty, %tm, %td, %d, %d, %ty, %tm, %td);",
                        id, flightNumber, departureCityId, departureTime, departureDate, arrivalCityId, arrivalTime, arrivalDate)
        );
    }
    public static boolean insert(Flight flight) {
        return Database.executeUpdate(
                String.format("INSERT INTO flight (id, flightNumber, depature, depatureTime, arrival, arrivalTime)" +
                                " VALUES (%d, \"%s\", %d, %d, %d, %d);",
                        flight.getFlightId(), flight.getFlightNumber(), flight.getdeparture().getCityID(), flight.getdepartureTime(), flight.getDepartureDate(), flight.getArrivalAirport().getCityID(), flight.getArrivalTime(), flight.getArrivalDate())
        );
    }

    public static boolean insert(int id, String flightNumber, int depatureId,
                                 int depatureTime, int arrivalId, int arrivalTime) {
        return Database.executeUpdate(
                String.format("INSERT INTO flight (id, flightNumber, depature, depatureTime, arrival, arrivalTime)" +
                                " VALUES (%d, \"%s\", %d, %d, %d, %d);",
                        id, flightNumber, depatureId, depatureTime, arrivalId, arrivalTime)
        );
    }

    public static boolean delete(String flightNumber) {
        return Database.executeUpdate(
                String.format("DELETE FROM flight WHERE flightNumber == \"%s\";",
                        flightNumber)
        );
    }

    public static boolean delete(int id) {
        return Database.executeUpdate(
                String.format("DELETE FROM flight WHERE id == \"%d\";",
                        id)
        );
    }

    public static void deleteAll() {
        Database.executeUpdate(
                String.format("DELETE FROM flight WHERE id >= 0;")
        );
    }

    public static List<Flight> getAllFlights(){
        List<Flight> flights = new ArrayList<>();
        ResultSet result = Database.executeQuery(
                "select f.*, d.id as dId, a.id as aId, d.name as dName, a.name as aName, d.long as dLong, a.long as aLong, d.lang as dLang, a.lang as aLang " +
                        "from flight f " +
                        "INNER JOIN city d ON f.depature = d.id " +
                        "INNER JOIN city a ON f.arrival = a.id;");
        try{
            if (result == null) {
                return null;
            }
            while (result.next()){
                flights.add(new Flight(
                        result.getInt("id"),
                        result.getString("flightNumber"),
                        new Airport(result.getInt("dId"), result.getString("dName"), result.getDouble("dLong"), result.getDouble("dLang")),
                        result.getInt("depatureTime"),
                        new Airport(result.getInt("aId"), result.getString("aName"), result.getDouble("aLong"), result.getDouble("aLang")),
                        result.getInt("arrivalTime")
                ));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return flights;
    }
    public static Flight find(int id){
        ResultSet result = Database.executeQuery(
                "select f.*, d.id as dId, a.id as aId, d.name as dName, a.name as aName, d.long as dLong, a.long as aLong, d.lang as dLang, a.lang as aLang " +
                        "from flight f " +
                        "INNER JOIN city d ON f.depature = d.id " +
                        "INNER JOIN city a ON f.arrival = a.id " +
                        "WHERE f.id = " + id + ";");
        try{
            if (result == null) {
                return null;
            }
            while (result.next()){
                return new Flight(
                        result.getInt("id"),
                        result.getString("flightNumber"),
                        new City(result.getInt("dId"), result.getString("dName"), result.getDouble("dLong"), result.getDouble("dLang")),
                        result.getInt("depatureTime"),
                        new City(result.getInt("aId"), result.getString("aName"), result.getDouble("aLong"), result.getDouble("aLang")),
                        result.getInt("arrivalTime")
                );


            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

     */

}
