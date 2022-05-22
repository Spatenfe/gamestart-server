package de.gamestart.java.data;

import java.beans.ConstructorProperties;
import java.sql.Date;

public class Flight extends Data {
    private int flightId;
    private String flightNumber;
    private Airport departureAirport;
    private Date departureDate;
    private int departureTime;
    private Airport arrivalAirport;
    private int arrivalTime;

    private Date arrivalDate;

    @ConstructorProperties({"Id", "flightNumber", "departureAirport", "departureTime", "departureDate", "arrivalAirport", "arrivalTime", "arrivalDate"})
    public Flight(int flightId, String flightNumber, Airport departureAirport, int departureTime, Date departureDate, Airport arrivalAirport, int arrivalTime, Date arrivalDate) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.departureTime = departureTime;
        this.arrivalAirport = arrivalAirport;
        this.arrivalTime = arrivalTime;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }
    public Flight(int flightId, String flightNumber, int departureTime, int arrivalTime) {
        this.flightId = flightId;
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
    }
    public int getFlightId() {
        return flightId;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Airport getdeparture() {
        return departureAirport;
    }

    public void setdeparture(Airport departure) {
        this.departureAirport = departure;
    }

    public int getdepartureTime() {
        return departureTime;
    }

    public void setdepartureTime(int departureTime) {
        this.departureTime = departureTime;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "Id=" + flightId +
                ", flightNumber='" + flightNumber + '\'' +
                ", departure=" + departureAirport +
                ", departureTime=" + departureTime +
                ", arrival=" + arrivalAirport +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
