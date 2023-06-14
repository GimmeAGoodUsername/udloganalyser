package de.universeDawn.fightScriptAnalyser.writer;

import de.universeDawn.fightScriptAnalyser.data.Player;
import de.universeDawn.fightScriptAnalyser.data.Ship;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

@Service
public class CsvWriter {
    private static final String SEP = ";";

    public void writeAsCsv(Map<String, Player> playerMap) {
        StringBuilder sb = new StringBuilder();
        sb.append("Player;").append("Shipname" + SEP).append("Side" + SEP).append("dmg total" + SEP).append("hits" + SEP).append("misses" + SEP).append("failedEscapes" + SEP).append("crits" + SEP).append("heavy" + SEP).append("hits;\n");
        for (Map.Entry<String, Player> player : playerMap.entrySet()) {
            for (Map.Entry<String, Ship> ship : player.getValue().getShipMap().entrySet()) {
                sb.append(player.getValue().getName() + SEP).append(ship.getValue().getShipName() + SEP).append(player.getValue().getSide()+ SEP).append(ship.getValue().getDamageDealt()+ SEP).
                        append(ship.getValue().
                        getHits()+ SEP).append(ship.getValue().getMisses()+ SEP).append(ship.getValue().getFailedEscapes()+ SEP).append(ship.getValue().getCrits()+ SEP).
                        append(ship.getValue().getHeavy()+ SEP);

                for (Object i:ship.getValue().getHitDamage()){
                    sb.append(i+ SEP);
                }
                sb.append("\n");
            }
        }

        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("fight.csv");
            fileWriter.write(sb.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
