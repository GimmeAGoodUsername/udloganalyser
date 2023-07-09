package de.universeDawn.fightscriptanalyser.repo;

import de.universeDawn.fightscriptanalyser.user.Planet;
import de.universeDawn.fightscriptanalyser.user.SrUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {
}
