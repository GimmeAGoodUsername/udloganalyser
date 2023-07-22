package de.universeDawn.fightscriptanalyser.data;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FightScriptPlayer {
  private long id;
  private String name;
  private int shipsAmount;
  private Side side;
  private Map<String, Ship> shipMap = new HashMap<>();
}
