
package de.gamestart.java.data;

import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Airport extends Data{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airport_id")
    private int airportId;

    @JsonIgnore
    @OneToMany(targetEntity = Flight.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "airportId")
    private Set<Flight> flights = new HashSet<>();

    @Column(name = "airport_name")
    private String airportName;
    @Column(name = "airport_pos_lat")
    private double airportPosLat;
    @Column(name = "airport_pos_long")
    private double airportPosLong;
    //private City locationCity;


    public Airport() {

    }

    public Airport(int airportId, String airportName, double airportPosLat, double airportPosLong, City locationCity) {
        this.airportId = airportId;
        this.airportName = airportName;
        this.airportPosLat = airportPosLat;
        this.airportPosLong = airportPosLong;
        //this.locationCity = locationCity;
    }

    public Set<Flight> getFlights() {
        return flights;
    }

    public int getAirportId() {
        return airportId;
    }

    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public double getAirportPosLat() {
        return airportPosLat;
    }

    public void setAirportPosLat(double airportPosLat) {
        this.airportPosLat = airportPosLat;
    }

    public double getAirportPosLong() {
        return airportPosLong;
    }

    public void setAirportPosLong(double airportPosLong) {
        this.airportPosLong = airportPosLong;
    }

    /**public City getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(City locationCity) {
        this.locationCity = locationCity;
    }**/


}
