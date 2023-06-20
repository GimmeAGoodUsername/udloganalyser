package de.universeDawn.fightscriptanalyser.data;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Player {
  private long id;
  private String name;
  private int shipsAmount;
  private Side side;
  private Map<String, Ship> shipMap = new HashMap<>();
}
