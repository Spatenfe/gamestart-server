package de.gamestart.java.controller;

import com.google.gson.Gson;
import de.gamestart.java.data.City;
import de.gamestart.java.data.Flight;
import de.gamestart.java.models.FlightsModel;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@RequestMapping("/flight")
public class FlightsController {
/*
    @GetMapping("/all")
    public String getAllFlights(){
        return new Gson().toJson(FlightsModel.getAllFlights());
    }
    @GetMapping("/get")
    public String getFlightWithId(@RequestParam int id){
        return new Gson().toJson(FlightsModel.find(id));
    }
    @PostMapping(
            value = "/add",
            consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Flight addFlight(@RequestBody Flight flight){
        FlightsModel.insert(flight);
        System.out.println(flight.toString());
        return flight;
    }

 */
}
