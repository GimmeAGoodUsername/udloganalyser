package de.universeDawn.fightScriptAnalyser.reader;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class CollectDataTest {
    CollectDataService sut = new CollectDataService();


    @Test
    void readDataTest() throws IOException {
        sut.collectDataFromScript();
    }
}