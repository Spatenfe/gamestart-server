package de.gamestart.java.data;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.sql.Date;

@Entity
public class Flight extends Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "flight_id")
    private int flightId;

    @Column(name = "flight_number")
    private String flightNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "departure_airport_id", referencedColumnName = "airport_id")
    private Airport departureAirport;

    @Column(name = "departure_date")
    private Date departureDate;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id", referencedColumnName = "airport_id")
    private Airport arrivalAirport;

    @Column(name = "arrival_date")
    private Date arrivalDate;

    public Flight() {

    }

    //@ConstructorProperties({"flightNumber", "departureAirport", "departureDate", "arrivalAirport", "arrivalDate"})
    public Flight(String flightNumber, Airport departureAirport, Date departureDate, Airport arrivalAirport, Date arrivalDate) {
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
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

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(Airport arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "Id=" + flightId +
                ", flightNumber='" + flightNumber + '\'' +
                ", departure=" + departureAirport +
                ", arrival=" + arrivalAirport +
                '}';
    }
}
