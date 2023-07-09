package de.universeDawn.fightscriptanalyser.reader;

import de.universeDawn.fightscriptanalyser.data.Player;
import de.universeDawn.fightscriptanalyser.writer.CsvWriter;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

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
            builder.append("\n");
            line = br.readLine();
        }
        System.out.println(builder.toString());
        Map<String, Player> stringPlayerMap = collectDataService.collectDataFromScript(builder.toString());
        BufferedWriter bw = new BufferedWriter(new FileWriter("fight.csv"));
        bw.write(csvWriter.writeAsCsv(stringPlayerMap));
    }
}