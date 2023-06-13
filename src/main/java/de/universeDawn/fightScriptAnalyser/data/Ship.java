package de.universeDawn.fightScriptAnalyser.data;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Ship {
    private String shipName;
    private int damageDealt = 0;
    private int hits = 0;
    private int misses = 0;
    private int failedEscapes = 0;
    private List hitDamage = new ArrayList<Integer>();
    private int crits = 0;
    private int heavy = 0;
}
