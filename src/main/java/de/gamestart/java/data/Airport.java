package de.gamestart.java.data;

import javax.persistence.*;

@Entity
public class Airport extends Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long airportId;

    @Column(unique = true)
    public String airportName;

    @Column(unique = true)
    public String airportSymbol;
    public double airportPosLat;
    public double airportPosLong;

    @ManyToOne
    @JoinColumn(name = "city_id")
    public City locationCity;

    public Airport() {

    }

    public Airport(String airportName, String airportSymbol, double airportPosLat, double airportPosLong, City locationCity) {
        this.airportName = airportName;
        this.airportSymbol = airportSymbol;
        this.airportPosLat = airportPosLat;
        this.airportPosLong = airportPosLong;
        this.locationCity = locationCity;
    }
}
