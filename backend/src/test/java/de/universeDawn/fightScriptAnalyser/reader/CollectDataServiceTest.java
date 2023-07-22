package de.universeDawn.fightscriptanalyser.reader;

import de.universeDawn.fightscriptanalyser.data.FightScriptPlayer;
import de.universeDawn.fightscriptanalyser.writer.CsvWriter;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Map;

class CollectDataServiceTest {
    CollectDataService collectDataService = new CollectDataService();
    CsvWriter csvWriter = new CsvWriter();


    @Test
    public void test() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("fight.txt"));
        StringBuilder builder = new StringBuilder();
        String line = br.readLine();
        while (line!=null){
            builder.append(line);
            builder.append(System.getProperty("line.separator"));
            line = br.readLine();
        }
        System.out.println(builder.toString());
        Map<String, FightScriptPlayer> stringPlayerMap = collectDataService.collectDataFromScript(builder.toString());
        BufferedWriter bw = new BufferedWriter(new FileWriter("fight.csv"));
        bw.write(csvWriter.writeAsCsv(stringPlayerMap));
    }
}