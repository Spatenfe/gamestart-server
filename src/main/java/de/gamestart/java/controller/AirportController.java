package de.gamestart.java.controller;

import de.gamestart.java.data.Airport;
import de.gamestart.java.data.City;
import de.gamestart.java.repository.AccountRepository;
import de.gamestart.java.repository.AirportRepository;
import de.gamestart.java.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/airport")
public class AirportController {
    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private AccountRepository accountRepository;

    /**
     * get all Airports
     *
     * @param accessToken - Account access key
     * @return - List of all Airports
     */
    @GetMapping("/fetchAll")
    public ResponseEntity<List<Airport>> fetchAllAirpots(@RequestParam String accessToken) {
        if (accountRepository.findByAccessToken(accessToken).size() == 0) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(airportRepository.findAll());
    }

    /**
     * returns all Airports of one City
     *
     * @param accessToken - Account access key
     * @param cityId      - Id of city
     * @return - returns list af all airports of this city
     */
    @GetMapping("/getAllFromCity")
    public ResponseEntity<List<Airport>> findAllAirportsFromCity(@RequestParam String accessToken,
                                                                 @RequestParam long cityId) {
        if (accountRepository.findByAccessToken(accessToken).size() == 0) {
            return ResponseEntity.badRequest().build();
        }

        if (cityRepository.findById(cityId).isEmpty()) {
            return ResponseEntity.ok(new ArrayList<>());
        }
        City city = cityRepository.findById(cityId).get();
        return ResponseEntity.ok(airportRepository.findByLocationCity(city));
    }
}
