package de.universeDawn.fightscriptanalyser.repo;

import de.universeDawn.fightscriptanalyser.user.SrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SrUserRepository extends JpaRepository<SrUser, Long> {

    @Query(value = "SELECT u FROM SrUser u WHERE u.name = ?1")
    SrUser findUserByName(String srUserName);

    @Query(value = "SELECT u FROM SrUser u WHERE u.name = ?1 AND u.password = ?2 and u.isUser = TRUE")
    SrUser findByValids(String srUserName, String srPassword);
}
