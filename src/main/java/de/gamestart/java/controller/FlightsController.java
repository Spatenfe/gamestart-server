package de.gamestart.java.controller;

import de.gamestart.java.data.Account;
import de.gamestart.java.data.Airport;
import de.gamestart.java.data.Flight;
import de.gamestart.java.repository.AccountRepository;
import de.gamestart.java.repository.AirportRepository;
import de.gamestart.java.repository.FlightRepository;
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

    @GetMapping("/fetchAll")
    public ResponseEntity<List<Flight>> fetchAllFlights(@RequestParam String accessToken) {
        if(accountRepository.findByAccessToken(accessToken).size() == 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(flightRepository.findAll());
    }

    @GetMapping("/find")
    public ResponseEntity<List<Flight>> findFlight(@RequestParam String accessToken,
                                                   @RequestParam long departureAirport, @RequestParam long arrivalAirport,
                                                   @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate) {
        if(accountRepository.findByAccessToken(accessToken).size() == 0) {
            return ResponseEntity.badRequest().build();
        }

        if(airportRepository.findById(departureAirport).isEmpty() ||
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

    @GetMapping("/savedFlights")
    public ResponseEntity<List<Flight>> savedFlights(@RequestParam String accessToken) {
        if(accountRepository.findByAccessToken(accessToken).size() == 0) {
            return ResponseEntity.badRequest().build();
        }

        Account account = accountRepository.findByAccessToken(accessToken).get(0);
        return ResponseEntity.ok(account.savedFlight.stream().toList());
    }

    @DeleteMapping("/unsaveFlight")
    public ResponseEntity<Flight> unsaveFlight(@RequestParam String accessToken, @RequestParam long flightID) {
        if(accountRepository.findByAccessToken(accessToken).size() == 0) {
            return ResponseEntity.badRequest().build();
        }
        Account account = accountRepository.findByAccessToken(accessToken).get(0);

        if(flightRepository.findById(flightID).isEmpty()) {
            return ResponseEntity.ok(null);
        }
        Flight flight = flightRepository.findById(flightID).get();

        if(!account.savedFlight.remove(flight)) {
            return ResponseEntity.ok(null);
        }

        accountRepository.saveAndFlush(account);

        return ResponseEntity.ok(flight);
    }

    @PostMapping("/saveFlight")
    public ResponseEntity<Flight> saveFlight(@RequestParam String accessToken, @RequestParam long flightID) {
        if(accountRepository.findByAccessToken(accessToken).size() == 0) {
            return ResponseEntity.badRequest().build();
        }

        Account account = accountRepository.findByAccessToken(accessToken).get(0);
        if(flightRepository.findById(flightID).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Flight flight = flightRepository.findById(flightID).get();
        account.savedFlight.add(flight);
        accountRepository.saveAndFlush(account);
        return ResponseEntity.ok(flight);
    }
}
