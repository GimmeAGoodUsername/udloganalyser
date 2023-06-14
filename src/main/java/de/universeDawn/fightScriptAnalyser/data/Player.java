package de.universeDawn.fightScriptAnalyser.data;

import java.util.HashMap;
import java.util.Map;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class Player {
  private long id;
  private String name;
  private int shipsAmount;
  private Side side;
  private Map<String, Ship> shipMap = new HashMap<>();
}
