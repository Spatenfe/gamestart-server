package de.gamestart.java.repository;

import de.gamestart.java.data.FlightTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightTicketRepository extends JpaRepository<FlightTicket, Long> {
}
