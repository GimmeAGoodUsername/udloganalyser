package de.universeDawn.fightScriptAnalyser.data;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class Player {
    private String name;
    private int shipsAmount;
    private Side side;
    private Map<String, Ship> shipMap = new HashMap<>();

}
