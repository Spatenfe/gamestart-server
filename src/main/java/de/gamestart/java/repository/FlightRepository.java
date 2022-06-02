package de.gamestart.java.repository;

import de.gamestart.java.data.Airport;
import de.gamestart.java.data.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByDepartureAirportAndArrivalAirportAndDepartureDate(Airport departure, Airport arrival, Date departureDate);
}
