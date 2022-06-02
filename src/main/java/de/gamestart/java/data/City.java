package de.gamestart.java.data;

import javax.persistence.*;

@Entity
public class City extends Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long cityId;

    @Column(unique = true)
    public String cityName;
    public double cityLang;
    public double cityLong;

    public City() {

    }

    public City(String name, double lang, double log) {
        this.cityName = name;
        this.cityLang = lang;
        this.cityLong = log;
    }
}
