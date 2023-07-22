package de.universeDawn.fightscriptanalyser.data;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Ship {
  private long id;
  private String shipName;
  private int damageDealt = 0;
  private int hits = 0;
  private int misses = 0;
  private int failedEscapes = 0;
  private List hitDamage = new ArrayList<Integer>();
  private int crits = 0;
  private int heavy = 0;
}
