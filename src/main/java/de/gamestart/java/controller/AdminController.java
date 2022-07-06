package de.gamestart.java.controller;

import de.gamestart.java.data.*;
import de.gamestart.java.repository.AccountRepository;
import de.gamestart.java.repository.AirportRepository;
import de.gamestart.java.repository.CityRepository;
import de.gamestart.java.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
        @Autowired
        private FlightRepository flightRepository;

        @PostMapping("/setCanceled")
        public ResponseEntity<Flight> setCanceledFlight(@RequestParam long flightId) {

            if (flightRepository.findById(flightId).isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Flight flight = flightRepository.findById(flightId).get();
            flight.isCanceled = true;
            flightRepository.saveAndFlush(flight);
            return ResponseEntity.ok(flight);
        }
        @PostMapping("/removeCancel")
        public ResponseEntity<Flight> removeCanceledFlight(@RequestParam long flightId) {

            if (flightRepository.findById(flightId).isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Flight flight = flightRepository.findById(flightId).get();
            flight.isCanceled = false;
            flightRepository.saveAndFlush(flight);
            return ResponseEntity.ok(flight);
        }
        @PostMapping("/setArrDelay")
        public ResponseEntity<Flight> setArDelayFlight(@RequestParam long flightId, @RequestParam int delayedBy) {

            if (flightRepository.findById(flightId).isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Flight flight = flightRepository.findById(flightId).get();
            flight.arrivalDelayedMinutes = delayedBy;
            flightRepository.saveAndFlush(flight);
            return ResponseEntity.ok(flight);
        }
        @PostMapping("/setDepDelay")
        public ResponseEntity<Flight> setDepDelayFlight(@RequestParam long flightId, @RequestParam int delayedBy) {

            if (flightRepository.findById(flightId).isEmpty()) {
                return ResponseEntity.badRequest().build();
            }

            Flight flight = flightRepository.findById(flightId).get();
            flight.depatureDelayedMinutes = delayedBy;
            flightRepository.saveAndFlush(flight);
            return ResponseEntity.ok(flight);
        }
}
