package de.gamestart.java.controller;

import de.gamestart.java.data.*;
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


    /**
     * get all point of interest of provided city
     *
     * @param accessToken - access key of account
     * @param cityId      - id of city
     * @return - returns all POIs of city
     */
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

    /**
     * get all saved POIs of account
     *
     * @param accessToken - access key of account
     * @return - returns all saved POIs of account
     */
    @GetMapping("/savedPoi")
    public ResponseEntity<List<SavedPointOfInterest>> savedPoi(@RequestParam String accessToken) {
        if (accountRepository.findByAccessToken(accessToken).size() == 0) {
            return ResponseEntity.badRequest().build();
        }

        Account account = accountRepository.findByAccessToken(accessToken).get(0);
        return ResponseEntity.ok(account.savedPoi.stream().toList());
    }

    /**
     * unsave POI of account with provided ID
     *
     * @param accessToken - access key of account
     * @param poiId       - ID of POI which should be removed
     * @return - returns removed POI or null if non-existent
     */
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

    /**
     * saved poi to saved POI of account
     *
     * @param accessToken - access key of account
     * @param poiId       - POI ID of POI which should be saved
     * @return - returns saved POI
     */
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

    @GetMapping("/isPoiSaved")
    public ResponseEntity<SavedPointOfInterest> isSavedPoi(@RequestParam String accessToken, @RequestParam long poiId) {
        if (accountRepository.findByAccessToken(accessToken).size() == 0) {
            return ResponseEntity.badRequest().build();
        }
        Account account = accountRepository.findByAccessToken(accessToken).get(0);

        if (pointOfInterestRepository.findById(poiId).isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        List<SavedPointOfInterest> savedPois = account.savedPoi.stream().filter(t -> t.savedPoi.poiId == poiId).toList();

        if(savedPois.size() > 0){
            return ResponseEntity.ok(savedPois.get(0));
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}


