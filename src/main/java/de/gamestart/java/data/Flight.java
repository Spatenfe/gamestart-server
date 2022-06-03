package de.gamestart.java.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Entity
public class Flight extends Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long flightId;

    public String flightNumber;

    @ManyToOne
    @JoinColumn(name = "departure_airport_id")
    public Airport departureAirport;

    public LocalDate departureDate;
    public LocalTime departureTime;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    public Airport arrivalAirport;

    public LocalDate arrivalDate;
    public LocalTime arrivalTime;

    public String airline;

    @ManyToMany(mappedBy = "savedFlight")
    @JsonIgnore
    public Set<Account> savedBy;

    public Flight() {

    }

    public Flight(String flightNumber,
                  Airport departureAirport,
                  LocalDate departureDate,
                  LocalTime departureTime,
                  Airport arrivalAirport,
                  LocalDate arrivalDate,
                  LocalTime arrivalTime,
                  String airline) {
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.airline = airline;
    }
}
