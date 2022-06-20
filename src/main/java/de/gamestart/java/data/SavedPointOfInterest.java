package de.gamestart.java.data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class SavedPointOfInterest extends Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long savedPoiId;
    @ManyToOne
    @JoinColumn(name = "poi_id")
    public PointOfInterest savedPoi;
    public LocalDate savedDate;
    @ManyToOne
    @JoinColumn(name = "account_id")
    public Account account;

    public SavedPointOfInterest() {
    }

    public SavedPointOfInterest(PointOfInterest savedPoi, LocalDate savedDate, Account account) {
        this.savedPoi = savedPoi;
        this.savedDate = savedDate;
        this.account = account;
    }
}
