package de.universeDawn.fightscriptanalyser.scan;


import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.ArrayList;
import java.util.List;

@ApplicationScope
@Service
public class ScanCalc {
    private int currentX = 0;
    private int currentY = 0;
    private int currentZ = 0;

    public void reset() {
        int currentX = 0;
        int currentY = 0;
        int currentZ = 0;
    }

    public List<String> getPoints(int amount, int scanrange) {
        int counter = 0;
        List<String> pointList = new ArrayList<String>();
        if (currentX == 0 && currentY == 0 && currentZ == 0) {
            currentX = scanrange;
            currentZ = scanrange;
            currentY = scanrange;
        }
        int actualScanBox = (scanrange*2)+1;
        for (int x = currentX; x < 500; x = x + actualScanBox) {
            for (int y = currentY; y < 500; y = y + actualScanBox) {
                for (int z = currentZ; z < 500; z = z + actualScanBox) {
                    currentZ=z;
                    currentY=y;
                    currentX=x;
                    checkPoints(actualScanBox);
                    counter++;
                    pointList.add(x+"-"+y+"-"+z);
                    if (counter > amount) {
                        return pointList;
                    }

                }
            }
        }


        return pointList;
    }

    private void checkPoints(int scanrange) {
        if ((currentX + scanrange) >= 500) {
            currentX = scanrange;
        }
        if ((currentY + scanrange) >= 500) {
            currentY = scanrange;
        }
        if ((currentZ + scanrange) >= 500) {
            currentZ = scanrange;
        }

    }



}
