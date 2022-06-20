package de.gamestart.java.data;

import net.bytebuddy.utility.RandomString;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Account {
    public enum UserPrivilidges {
        USER_PRIVILIDGES,
        ADMIN_PRIVILIDGES
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long accountID;

    @Column(unique=true)
    public String userName;

    public String firstName;
    public String lastName;

    @ManyToMany
    @JsonIgnore
    @JoinTable(
            name = "saved_flight",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "flight_id")
    )
    public Set<Flight> savedFlight;

    //Password will be saved as SHA-256
    public String passwordHash;

    @Column(unique=true)
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
}
