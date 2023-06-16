package de.universeDawn.fightScriptAnalyser.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Planet {
    @Id
    private long id;
    private String name;
    private int x;
    private int y;
    private int z;
    @ManyToOne
    @JoinColumn(name="srUser_id",nullable = true)
    private SrUser srUser;
}
