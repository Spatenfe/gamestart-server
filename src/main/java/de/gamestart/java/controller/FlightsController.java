package de.gamestart.java.controller;

import de.gamestart.java.data.Account;
import de.gamestart.java.data.Airport;
import de.gamestart.java.data.Flight;
import de.gamestart.java.data.FlightTicket;
import de.gamestart.java.repository.AccountRepository;
import de.gamestart.java.repository.AirportRepository;
import de.gamestart.java.repository.FlightRepository;
import de.gamestart.java.repository.FlightTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightsController {
    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private FlightTicketRepository flightTicketRepository;

    /**
     * get all flights
     *
     * @param accessToken - access key of account
     * @return - list of all flights
     */
    @GetMapping("/fetchAll")
    public ResponseEntity<List<Flight>> fetchAllFlights(@RequestParam String accessToken) {
        if (accountRepository.findByAccessToken(accessToken).size() == 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(flightRepository.findAll());
    }

    /**
     * find fitting flight for request parameter
     *
     * @param accessToken      - accessToken of account
     * @param departureAirport - id of departure airport
     * @param arrivalAirport   - id of arrival airport
     * @param departureDate    - date of departure
     * @return - returns fitting flight for request parameter
     */
    @GetMapping("/find")
    public ResponseEntity<List<Flight>> findFlight(@RequestParam String accessToken,
                                                   @RequestParam long departureAirport, @RequestParam long arrivalAirport,
                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate) {
        if (accountRepository.findByAccessToken(accessToken).size() == 0) {
            return ResponseEntity.badRequest().build();
        }

        if (airportRepository.findById(departureAirport).isEmpty() ||
                airportRepository.findById(arrivalAirport).isEmpty()) {
            return ResponseEntity.ok(new ArrayList<>());
        }

        Airport departure = airportRepository.findById(departureAirport).get();
        Airport arrival = airportRepository.findById(arrivalAirport).get();
        return ResponseEntity.ok(flightRepository.findByDepartureAirportAndArrivalAirportAndDepartureDate(
                departure,
                arrival,
                departureDate
        ));
    }

    /**
     * get all saved flights of account
     *
     * @param accessToken - access key of account
     * @return - returns all saved flight of account
     */
    @GetMapping("/savedFlights")
    public ResponseEntity<List<FlightTicket>> savedFlights(@RequestParam String accessToken) {
        if (accountRepository.findByAccessToken(accessToken).size() == 0) {
            return ResponseEntity.badRequest().build();
        }

        Account account = accountRepository.findByAccessToken(accessToken).get(0);
        return ResponseEntity.ok(account.flightTickets.stream().toList());
    }

    /**
     * unsave flight with given id
     *
     * @param accessToken - access key of account
     * @param flightID    - id of flight which should be unsaved
     * @return - returns removed flight or null if non-existent
     */
    @DeleteMapping("/unsaveFlight")
    public ResponseEntity<Flight> unsaveFlight(@RequestParam String accessToken, @RequestParam long flightID) {
        if (accountRepository.findByAccessToken(accessToken).size() == 0) {
            return ResponseEntity.badRequest().build();
        }
        Account account = accountRepository.findByAccessToken(accessToken).get(0);

        if (flightRepository.findById(flightID).isEmpty()) {
            return ResponseEntity.ok(null);
        }
        Flight flight = flightRepository.findById(flightID).get();

        if (account.flightTickets.stream().filter(t -> t.saveFlight.flightId == flightID).findFirst().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        if (!account.flightTickets.remove(account.flightTickets.stream().filter(t -> t.saveFlight.flightId == flightID).findFirst().get())) {
            return ResponseEntity.ok(null);
        }

        accountRepository.saveAndFlush(account);

        return ResponseEntity.ok(flight);
    }

    /**
     * adds flight to saved flights of account
     *
     * @param accessToken - access key of account
     * @param flightID    - flight id of flight which should be saved
     * @param seat        - reserved seat in flight
     * @return - return flight ticket with the given flight and seat number bound to account
     */
    @PostMapping("/saveFlight")
    public ResponseEntity<FlightTicket> saveFlight(@RequestParam String accessToken,
                                                   @RequestParam long flightID,
                                                   @RequestParam String seat) {
        if (accountRepository.findByAccessToken(accessToken).size() == 0) {
            return ResponseEntity.badRequest().build();
        }

        Account account = accountRepository.findByAccessToken(accessToken).get(0);
        if (flightRepository.findById(flightID).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Flight flight = flightRepository.findById(flightID).get();
        FlightTicket flightTicket = new FlightTicket(flight, account, seat);
        flightTicketRepository.saveAndFlush(flightTicket);
        account.flightTickets.add(flightTicket);
        accountRepository.saveAndFlush(account);
        return ResponseEntity.ok(flightTicket);
    }
}
