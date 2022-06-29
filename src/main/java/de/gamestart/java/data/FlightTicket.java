package de.gamestart.java.data;

import javax.persistence.*;

@Entity
public class FlightTicket extends Data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @ManyToOne
    @JoinColumn(name = "saved_flight_id")
    public Flight saveFlight;
    @ManyToOne
    @JoinColumn(name = "saved_by_account_id")
    public Account savedByAccount;
    public String seat;
    public TicketClass ticketClass;

    public FlightTicket(Flight saveFlight, Account savedByAccount, String seat) {
        this.saveFlight = saveFlight;
        this.savedByAccount = savedByAccount;
        this.seat = seat;
        this.ticketClass = TicketClass.ECONOMY_CLASS;
    }

    public FlightTicket() {

    }

    public enum TicketClass {
        FIRST_CLASS,
        BUISINESS_CLASS,
        ECONOMY_CLASS
    }
}
