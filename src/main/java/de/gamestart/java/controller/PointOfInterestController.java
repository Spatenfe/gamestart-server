package de.gamestart.java.controller;

import de.gamestart.java.data.Account;
import de.gamestart.java.data.City;
import de.gamestart.java.data.PointOfInterest;
import de.gamestart.java.data.SavedPointOfInterest;
import de.gamestart.java.repository.AccountRepository;
import de.gamestart.java.repository.CityRepository;
import de.gamestart.java.repository.PointOfInterestRepository;
import de.gamestart.java.repository.SavedPointOfInterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/poi")
public class PointOfInterestController {
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private SavedPointOfInterestRepository savedPointOfInterestRepository;
    @Autowired
    private PointOfInterestRepository pointOfInterestRepository;
    @Autowired
    private AccountRepository accountRepository;


    @GetMapping("/getAllFromCity")
    public ResponseEntity<List<PointOfInterest>> findAllPoi(@RequestParam String accessToken,
                                                            @RequestParam long cityId) {
        if (accountRepository.findByAccessToken(accessToken).size() == 0) {
            return ResponseEntity.badRequest().build();
        }

        if (cityRepository.findById(cityId).isEmpty()) {
            return ResponseEntity.ok(new ArrayList<>());
        }
        City city = cityRepository.findById(cityId).get();
        return ResponseEntity.ok(pointOfInterestRepository.findByPoiCity(city));
    }

    @GetMapping("/savedPoi")
    public ResponseEntity<List<SavedPointOfInterest>> savedPoi(@RequestParam String accessToken) {
        if (accountRepository.findByAccessToken(accessToken).size() == 0) {
            return ResponseEntity.badRequest().build();
        }

        Account account = accountRepository.findByAccessToken(accessToken).get(0);
        return ResponseEntity.ok(savedPointOfInterestRepository.findByAccount(account));
    }

    @DeleteMapping("/unsavePoi")
    public ResponseEntity<SavedPointOfInterest> unsavePoi(@RequestParam String accessToken, @RequestParam long poiId) {
        if (accountRepository.findByAccessToken(accessToken).size() == 0) {
            return ResponseEntity.badRequest().build();
        }
        Account account = accountRepository.findByAccessToken(accessToken).get(0);

        if (savedPointOfInterestRepository.findById(poiId).isEmpty()) {
            return ResponseEntity.ok(null);
        }
        SavedPointOfInterest poi = savedPointOfInterestRepository.findById(poiId).get();

        if (!account.savedPoi.remove(poi)) {
            return ResponseEntity.ok(null);
        }

        accountRepository.saveAndFlush(account);

        return ResponseEntity.ok(poi);
    }

    @PostMapping("/savePoi")
    public ResponseEntity<SavedPointOfInterest> savePoi(@RequestParam String accessToken, @RequestParam long poiId) {
        if (accountRepository.findByAccessToken(accessToken).size() == 0) {
            return ResponseEntity.badRequest().build();
        }

        Account account = accountRepository.findByAccessToken(accessToken).get(0);
        if (pointOfInterestRepository.findById(poiId).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        PointOfInterest poi = pointOfInterestRepository.findById(poiId).get();
        SavedPointOfInterest savedPoi = new SavedPointOfInterest(poi, LocalDate.now(), account);
        savedPointOfInterestRepository.saveAndFlush(savedPoi);

        account.savedPoi.add(savedPoi);
        accountRepository.saveAndFlush(account);
        return ResponseEntity.ok(savedPoi);
    }

}


