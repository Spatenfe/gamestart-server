package de.gamestart.java.repository;

import de.gamestart.java.data.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserName(String userName);

    List<Account> findByAccessToken(String accessToken);
}
