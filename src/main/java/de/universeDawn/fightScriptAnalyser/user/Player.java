package de.universeDawn.fightScriptAnalyser.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
public class Player {
    @Id
    private long id;
    private String name;
    private Race race;
    private Role role;
    @OneToMany(mappedBy = "player")
    private List<Planet> planets;
}
