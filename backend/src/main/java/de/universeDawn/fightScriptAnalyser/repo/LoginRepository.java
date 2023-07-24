package de.universeDawn.fightscriptanalyser.repo;

import de.universeDawn.fightscriptanalyser.user.LoginInformation;
import de.universeDawn.fightscriptanalyser.user.SrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<LoginInformation, Long> {
    @Query(value = "SELECT u FROM LoginInformation u WHERE u.name = ?1 AND u.password = ?2 AND u.isUser = TRUE")
    LoginInformation findByValids(String srUserName, String srPassword);
}
