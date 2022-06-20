package de.gamestart.java.repository;

import de.gamestart.java.data.City;
import de.gamestart.java.data.PointOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PointOfInterestRepository extends JpaRepository<PointOfInterest, Long> {

    List<PointOfInterest> findByPoiCity(City city);

}
