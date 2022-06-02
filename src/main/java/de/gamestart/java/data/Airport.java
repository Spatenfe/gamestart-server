
package de.gamestart.java.data;

import javax.persistence.*;

@Entity
public class Airport extends Data{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long airportId;

    @Column(unique = true)
    public String airportName;
    public double airportPosLat;
    public double airportPosLong;

    @ManyToOne
    @JoinColumn(name = "city_id")
    public City locationCity;

    public Airport() {

    }

    public Airport(String airportName, double airportPosLat, double airportPosLong, City locationCity) {
        this.airportName = airportName;
        this.airportPosLat = airportPosLat;
        this.airportPosLong = airportPosLong;
        this.locationCity = locationCity;
    }
}
