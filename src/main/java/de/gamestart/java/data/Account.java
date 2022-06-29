package de.gamestart.java.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.utility.RandomString;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long accountID;
    @Column(unique = true)
    public String userName;
    public String firstName;
    public String lastName;
    @OneToMany(mappedBy = "savedByAccount")
    @JsonIgnore
    public Set<FlightTicket> flightTickets;
    @OneToMany
    @JsonIgnore
    public Set<SavedPointOfInterest> savedPoi;
    //Password will be saved as SHA-256
    public String passwordHash;
    @Column(unique = true)
    public String accessToken;
    public UserPrivilidges privilidges;

    public Account() {

    }

    public Account(String userName, String firstName, String lastName, String passwordHash) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passwordHash = passwordHash;
        this.accessToken = generateRandomAccessToken();
        this.privilidges = UserPrivilidges.USER_PRIVILIDGES;
    }

    public static String generateRandomAccessToken() {
        return RandomString.make(32);
    }

    public enum UserPrivilidges {
        USER_PRIVILIDGES,
        ADMIN_PRIVILIDGES
    }
}
