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
    public int depatureDelayedMinutes;

    public char departureGate;
    public int departureTerminal;

    public boolean isCanceled;

    @ManyToOne
    @JoinColumn(name = "arrival_airport_id")
    public Airport arrivalAirport;

    public LocalDate arrivalDate;
    public LocalTime arrivalTime;
    public int arrivalDelayedMinutes;

    public char arrivalGate;
    public int arrivalTerminal;

    public String airline;

    @OneToMany(mappedBy = "saveFlight")
    @JsonIgnore
    private Set<FlightTicket> flightTickets;

    public Flight() {

    }

    public Flight(String flightNumber,
                  Airport departureAirport,
                  LocalDate departureDate,
                  LocalTime departureTime,
                  char departureGate,
                  int departureTerminal,
                  Airport arrivalAirport,
                  LocalDate arrivalDate,
                  LocalTime arrivalTime,
                  char arrivalGate,
                  int arrivalTerminal,
                  String airline) {
        this.flightNumber = flightNumber;
        this.departureAirport = departureAirport;
        this.departureGate = departureGate;
        this.departureTerminal = departureTerminal;
        this.arrivalAirport = arrivalAirport;
        this.arrivalGate = arrivalGate;
        this.arrivalTerminal = arrivalTerminal;
        this.departureDate = departureDate;
        this.departureTime = departureTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.airline = airline;
        this.arrivalDelayedMinutes = 0;
        this.depatureDelayedMinutes = 0;
        this.isCanceled = false;
    }
}
