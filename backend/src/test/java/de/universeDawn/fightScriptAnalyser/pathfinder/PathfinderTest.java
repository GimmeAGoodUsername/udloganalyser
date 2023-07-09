package de.universeDawn.fightscriptanalyser.pathfinder;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PathfinderTest {


    @Test
    void test() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("coords.txt"));
        List<Pathfinder.Point> coordinateList = new ArrayList<>();


        String line = br.readLine();
        while ( line !=null){
            String[] split = line.split("-");
            coordinateList.add(new Pathfinder.Point(Integer.parseInt(split[0]),Integer.parseInt(split[1]),Integer.parseInt(split[2])));
            line = br.readLine();
        }

        Pathfinder pathfinder = new Pathfinder();
        pathfinder.path(coordinateList);
    }

}