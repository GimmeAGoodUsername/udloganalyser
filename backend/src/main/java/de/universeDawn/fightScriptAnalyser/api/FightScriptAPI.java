package de.universeDawn.fightscriptanalyser.api;

import de.universeDawn.fightscriptanalyser.data.Player;
import de.universeDawn.fightscriptanalyser.reader.CollectDataService;
import de.universeDawn.fightscriptanalyser.writer.CsvWriter;
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

  @Autowired
  private CsvWriter csvWriter;

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
  @PostMapping(value = "/mapFightResultCsv",produces = "application/json")
  ResponseEntity<String> mapFightResultCsv(@RequestBody String userInput) {
    Map<String, Player> playerMap = new HashMap<>();
    try {
      playerMap = collectDataService.collectDataFromScript(userInput);
      return new ResponseEntity<String>(csvWriter.writeAsCsv(playerMap), HttpStatus.OK);
    } catch (IOException e) {
      return new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
