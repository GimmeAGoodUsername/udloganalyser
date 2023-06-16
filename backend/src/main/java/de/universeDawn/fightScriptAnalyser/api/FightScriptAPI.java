package de.universeDawn.fightScriptAnalyser.api;

import de.universeDawn.fightScriptAnalyser.data.Player;
import de.universeDawn.fightScriptAnalyser.reader.CollectDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/fightApi")
public class FightScriptAPI {

  @Autowired
  private CollectDataService collectDataService;

  @PostMapping(value = "/mapFightResult",produces = "application/json")
  ResponseEntity<Map<String, Player>> mapFightResult(@RequestBody String userInput) {
    Map<String, Player> playerMap = new HashMap<>();
    try {
      playerMap = collectDataService.collectDataFromScript(userInput);
      return new ResponseEntity<Map<String, Player>>(playerMap, HttpStatus.OK);
    } catch (IOException e) {
      return new ResponseEntity<Map<String, Player>>(playerMap, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
