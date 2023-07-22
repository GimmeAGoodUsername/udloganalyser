package de.universeDawn.fightscriptanalyser.reader.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Ship {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long shipID;
    private String name;
    private int damageDealt;
    private int hits;
    private int misses;
    private int failedEscapes;
    private int crits;
    private int heavy;
}
