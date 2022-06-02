package de.gamestart.java.data;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
public class Flight extends Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long flightId;

    public String flightNumber;

    @ManyToOne
    @JoinColumn(name = "depature_airport_id")
    public Airport departureAirport;

    public Date departureDate;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    public Airport arrivalAirport;

    public Date arrivalDate;

    public String airline;

    @JsonIgnore
    @ManyToMany(mappedBy = "savedFlight")
    public Set<Account> savedBy;

    public Flight() {

    }

    public Flight(String flightNumber, Airport departureAirport, Date departureDate, Airport arrivalAirport, Date arrivalDate, String airline) {
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.airline = airline;
    }
}
