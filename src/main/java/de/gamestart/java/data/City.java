package de.gamestart.java.data;

import java.beans.ConstructorProperties;

public class City extends Data {
    private int cityId;
    private String cityName;
    private double cityLang;
    private double cityLong;

    @ConstructorProperties({"cityId", "cityName", "cityLat", "cityLong"})
    public City(int id, String name, double lang, double log) {
        this.cityId = id;
        this.cityName = name;
        this.cityLang = lang;
        this.cityLong = log;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public double getCityLat() {
        return cityLang;
    }

    public void setCityLat(double cityLang) {
        this.cityLang = cityLang;
    }

    public double getCityLong() {
        return cityLong;
    }

    public void setCityLong(double cityLong) {
        this.cityLong = cityLong;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
