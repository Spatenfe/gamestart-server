
package de.gamestart.java.data;
public class Airport extends Data{
    private int airportId;
    private String airportName;
    private double airportPosLat;
    private double airportPosLong;
    private City locationCity;

    public Airport(int airportId, String airportName, double airportPosLat, double airportPosLong, City locationCity) {
        this.airportId = airportId;
        this.airportName = airportName;
        this.airportPosLat = airportPosLat;
        this.airportPosLong = airportPosLong;
        this.locationCity = locationCity;
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

    public City getLocationCity() {
        return locationCity;
    }

    public void setLocationCity(City locationCity) {
        this.locationCity = locationCity;
    }


}
