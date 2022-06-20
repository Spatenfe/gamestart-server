package de.gamestart.java.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class PointOfInterest extends Data {
    public enum PoiType {
        RESTAURANT,
        NIGHTLIFE,
        LANDMARK,
        PARKING_SPACE,
        SHOP,
        ENTERTAINMENT,
        TRANSPORTATION,
        PUBLIC_BUILDING,
        OTHER
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long poiId;

    @Column(unique = true)
    public String poiName;

    public String poiContinent;
    public String poiCountry;
    @ManyToOne
    @JoinColumn(name = "city_id")
    public City poiCity;
    public double poiPosLong;
    public double poiPosLat;

    public String poiDescription;
    public String poiPictureUrl;
    public PoiType poiType;

    @OneToMany(mappedBy = "savedPoi")
    @JsonIgnore
    public Set<SavedPointOfInterest> savedBy;

    public PointOfInterest() {
    }

    public PointOfInterest(String poiName, String poiContinent, String poiCountry, City poiCity, double poiPosLong, double poiPosLat, String poiDescription, String poiPictureUrl, PoiType poiType) {
        this.poiName = poiName;
        this.poiContinent = poiContinent;
        this.poiCountry = poiCountry;
        this.poiCity = poiCity;
        this.poiPosLong = poiPosLong;
        this.poiPosLat = poiPosLat;
        this.poiDescription = poiDescription;
        this.poiPictureUrl = poiPictureUrl;
        this.poiType = poiType;
    }
}
