package de.universeDawn.fightScriptAnalyser.reader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import de.universeDawn.fightScriptAnalyser.data.Player;
import de.universeDawn.fightScriptAnalyser.data.Ship;
import de.universeDawn.fightScriptAnalyser.data.Side;
import de.universeDawn.fightScriptAnalyser.writer.CsvWriter;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Service
public class CollectData {
    private static final String HERRSCHER = "Herrscher";
    private static final String ANGREIFER = "Angreifer";
    private static final String VERTEIDIGER = "Verteidiger";
    private static final String ANZAHL = "Anzahl";
    private int destroyedShips = 0;
    Map<String, Player> playerMap = new HashMap<>();

    public void collectDataFromScript() throws IOException {
        URL resource = this.getClass().getClassLoader().getResource("static/testscript.txt");
        BufferedReader br = new BufferedReader(new FileReader(resource.getFile()));

        try {
            String line = br.readLine();
            while (line != null) {
                detectLineType(line, br);

                line = br.readLine();
            }
        } finally {
            br.close();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(new Gson().toJson(playerMap));
        FileWriter fileWriter = new FileWriter("fight.txt");
        fileWriter.write(gson.toJson(jsonElement)+"\n"+"Total Destroyed Ships: " + destroyedShips);
        fileWriter.flush();
        fileWriter.close();

        CsvWriter csvWriter = new CsvWriter();
        csvWriter.writeAsCsv(playerMap);
    }

    private void detectLineType(String line, BufferedReader br) throws IOException {
        if (line.isEmpty()) {
            return;
        } else if (line.startsWith(HERRSCHER)) {
            readPlayerMetaData(line, br);
        } else {
            readBattleData(line, br);
        }
    }

    private void readBattleData(String line, BufferedReader br) throws IOException {
        while (true) {
            if (line == null) {
                return;
            }
            if (!line.startsWith("Das")) {
                if (line.startsWith("Versuchte")) {
                    line = br.readLine();
                    continue;
                } else if (line.contains("Die Angreifer haben gewonnen")) {
                    return;
                } else if (line.contains("wurde ")) {
                    destroyedShips++;
                    line = br.readLine();

                }
                if (line.contains("feuert")) {
                    String[] battleData = line.split(" ");
                    int playerLoc = getPlayerLoc(battleData);
                    String playerName = battleData[playerLoc].replace(")", "").replace("(", "");
                    Player player = playerMap.get(playerName);
                    if (player == null) {
                        int t = playerLoc + 1;
                        System.out.println(line);
                        String te = battleData[playerLoc + 1].replace(")", "").replace("(", "");

                    }
                    String shipname ="";
                    for(int i = 0; i < playerLoc; i++){
                        shipname+= battleData[i]+" ";
                    }
                    shipname = shipname.trim();
                    if (!player.getShipMap().containsKey(shipname)) {
                        Ship ship = new Ship();
                        ship.setShipName(shipname);
                        player.getShipMap().put(ship.getShipName(), ship);
                    }
                    Ship ship = player.getShipMap().get(shipname);
                    if (line.contains("verfehlt")) {
                        ship.setMisses(ship.getMisses() + 1);
                    } else if (line.contains("fehlgeschlagen")) {
                        ship.setFailedEscapes(ship.getFailedEscapes() + 1);
                    } else {
                        if (line.contains("kritischen")) {
                            ship.setCrits(ship.getCrits() + 1);
                        }
                        if (line.contains("schweren")) {
                            ship.setHeavy(ship.getHeavy() + 1);
                        }
                        String damage = battleData[getDamagePos(battleData) + 1];
                        String[] split = damage.split("\\(");
                        ship.getHitDamage().add(Integer.parseInt(split[0]));
                        ship.setHits(ship.getHits() + 1);
                        ship.setDamageDealt(ship.getDamageDealt() + Integer.parseInt(split[0]));
                    }
                }


            }
            line = br.readLine();


        }
    }

    private int getPlayerLoc(String[] block) {
        int i = 0;
        for (String s : block) {
            if (s.startsWith("(")) {
                return i;
            }
            i++;
        }
        return 0;
    }

    private int getDamagePos(String[] block) {
        int i = 0;
        for (String s : block) {
            if (s.equals("Schaden:")) {
                return i;
            }
            i++;
        }
        return 0;
    }

    private void readPlayerMetaData(String line, BufferedReader br) throws IOException {
        Side side = Side.Verteidiger;
        if (line.contains(ANGREIFER)) {
            side = Side.Angreifer;
        }
        while (true) {
            line = br.readLine();
            if (line.startsWith(ANZAHL)) {
                return;
            }
            String[] values = line.split(" ");
            Player player = new Player();
            player.setName(values[0]);
            player.setSide(side);
            player.setShipsAmount(Integer.parseInt(values[2].replace(")", "")));
            playerMap.putIfAbsent(values[0], player);
        }
    }
}
