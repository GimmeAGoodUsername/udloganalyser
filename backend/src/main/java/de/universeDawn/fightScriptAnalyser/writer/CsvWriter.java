package de.universeDawn.fightscriptanalyser.writer;

import de.universeDawn.fightscriptanalyser.data.FightScriptPlayer;
import de.universeDawn.fightscriptanalyser.data.Ship;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CsvWriter {
    private static final String SEP = ";";

    public String writeAsCsv(Map<String, FightScriptPlayer> playerMap) {
        StringBuilder sb = new StringBuilder();
        sb.append("Player;").append("Shipname" + SEP).append("Side" + SEP).append("dmg total" + SEP).append("hits" + SEP).append("misses" + SEP).append("failedEscapes" + SEP).append("crits" + SEP).append("heavy" + SEP).append("hits;\n");
        for (Map.Entry<String, FightScriptPlayer> player : playerMap.entrySet()) {
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

        return sb.toString();
    }
}
