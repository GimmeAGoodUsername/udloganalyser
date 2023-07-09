package de.universeDawn.fightscriptanalyser.pathfinder;

import java.util.*;

public class Pathfinder {
     static class Point {
        public double x;
        public double y;
        public double z;

        public Point(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static List<Point> findShortestPath(List<Point> coordinates) {
        List<Point> path = new ArrayList<>();
        boolean[] visited = new boolean[coordinates.size()];

        // Start at the first coordinate
        path.add(coordinates.get(0));
        visited[0] = true;

        // Find the nearest unvisited coordinate and add it to the path
        for (int i = 0; i < coordinates.size() - 1; i++) {
            int nearestIndex = -1;
            double shortestDistance = Double.MAX_VALUE;
            Point current = path.get(i);

            for (int j = 0; j < coordinates.size(); j++) {
                if (!visited[j]) {
                    Point next = coordinates.get(j);
                    double distance = calculateDistance(current, next);

                    if (distance < shortestDistance) {
                        shortestDistance = distance;
                        nearestIndex = j;
                    }
                }
            }

            // Mark the nearest coordinate as visited and add it to the path
            visited[nearestIndex] = true;
            path.add(coordinates.get(nearestIndex));
        }

        // Return to the starting point
        path.add(coordinates.get(0));

        return path;
    }

    private static double calculateDistance(Point p1, Point p2) {
        double dx = p1.x - p2.x;
        double dy = p1.y - p2.y;
        double dz = p1.z - p2.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
    public void path(List<Point> coordinates){

        List<Point> fastestRoute = findShortestPath(coordinates);

        for (Point point : fastestRoute) {
            System.out.println("(" + point.x + ", " + point.y + ", " + point.z + ")");
        }
    }
}
