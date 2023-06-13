package de.universeDawn.fightScriptAnalyser.reader;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class CollectDataTest {
    CollectData sut = new CollectData();


    @Test
    void readDataTest() throws IOException {
        sut.collectDataFromScript();
    }
}