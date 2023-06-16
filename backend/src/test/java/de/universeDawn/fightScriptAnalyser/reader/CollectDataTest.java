package de.universeDawn.fightScriptAnalyser.reader;

import de.universeDawn.fightScriptAnalyser.data.Player;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

class CollectDataTest {
    CollectDataService sut = new CollectDataService();


    @Test
    void readDataTest() throws IOException {
        Map<String, Player> playerMap = sut.collectDataFromScript();
        System.out.println(playerMap);
    }
}