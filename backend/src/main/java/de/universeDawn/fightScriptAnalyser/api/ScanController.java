package de.universeDawn.fightscriptanalyser.api;

import de.universeDawn.fightscriptanalyser.api.scan.ScanRequest;
import de.universeDawn.fightscriptanalyser.pathfinder.Pathfinder;
import de.universeDawn.fightscriptanalyser.scan.Point;
import de.universeDawn.fightscriptanalyser.scan.ScanCalc;
import de.universeDawn.fightscriptanalyser.scan.ScanCreep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/scanApi")
public class ScanController {

    @Autowired
    private ScanCalc scanCalc;

    @Autowired
    private ScanCreep scanCreep;

    private Pathfinder pathfinder = new Pathfinder();

    @PostMapping(value = "/scan", produces = "application/json")
    public List<String> getPoints2Scan(@RequestBody ScanRequest scanRequest){
        List<String> points = new ArrayList<>();
        if(scanRequest.creep()){
            points = scanCreep.getPoints(scanRequest.amount(), scanRequest.scanrange());
        }else{
            points = scanCalc.getPoints(scanRequest.amount(), scanRequest.scanrange());
        }
        List<Pathfinder.Point> pointList = pathfinder.convertToPoint(points);
        List<Pathfinder.Point> path = pathfinder.path(pointList);
        List<String> stringPath = new ArrayList<>();
        for(Pathfinder.Point p : path){
            stringPath.add(Math.round(p.x)+"-"+Math.round(p.y)+"-"+Math.round(p.z));
        }
        return stringPath;
    }
}
