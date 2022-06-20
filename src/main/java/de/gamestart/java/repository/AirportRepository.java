package de.gamestart.java.repository;

import de.gamestart.java.data.Airport;
import de.gamestart.java.data.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AirportRepository extends JpaRepository<Airport, Long> {

    List<Airport> findByLocationCity(City city);

}
