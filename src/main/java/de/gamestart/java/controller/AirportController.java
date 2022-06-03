package de.gamestart.java.controller;

import de.gamestart.java.data.Airport;
import de.gamestart.java.repository.AccountRepository;
import de.gamestart.java.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/airport")
public class AirportController {
    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/fetchAll")
    public ResponseEntity<List<Airport>> fetchAllAirpots(@RequestParam String accessToken) {
        if(accountRepository.findByAccessToken(accessToken).size() == 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(airportRepository.findAll());
    }
}
