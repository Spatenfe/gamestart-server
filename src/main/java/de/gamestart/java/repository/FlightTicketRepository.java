package de.gamestart.java.repository;

import de.gamestart.java.data.Account;
import de.gamestart.java.data.Flight;
import de.gamestart.java.data.FlightTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightTicketRepository extends JpaRepository<FlightTicket, Long> {
    List<FlightTicket> findBySavedByAccountAndSaveFlight(Account account, Flight flight);
}
