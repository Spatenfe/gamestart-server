package de.gamestart.java.repository;

import de.gamestart.java.data.Account;
import de.gamestart.java.data.SavedPointOfInterest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavedPointOfInterestRepository extends JpaRepository<SavedPointOfInterest, Long> {

    List<SavedPointOfInterest> findByAccount(Account account);

}
