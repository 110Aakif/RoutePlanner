package com.routeplanner.service;


import com.routeplanner.interfaces.IDistanceCalculator;
import com.routeplanner.model.GeoLocation;
import com.routeplanner.model.Task;
import com.routeplanner.util.PermutationUtil;

import java.util.*;

public class RoutePlanner {

    private final IDistanceCalculator distanceCalculator;

    public RoutePlanner(IDistanceCalculator distanceCalculator) {
        this.distanceCalculator = distanceCalculator;
    }

    public static class Result {
        public List<Task> bestRoute;
        public double minTime;

        public Result(List<Task> bestRoute, double minTime) {
            this.bestRoute = bestRoute;
            this.minTime = minTime;
        }
    }

    public Result findBestRoute(
            GeoLocation aman,
            GeoLocation r1, double pt1,
            GeoLocation r2, double pt2,
            GeoLocation c1, GeoLocation c2
    ) {
        List<Task> tasks = new ArrayList<>(Arrays.asList(
                new Task(Task.Type.PICKUP, r1, "C1"),
                new Task(Task.Type.PICKUP, r2, "C2"),
                new Task(Task.Type.DELIVERY, c1, "C1"),
                new Task(Task.Type.DELIVERY, c2, "C2")
        ));

        List<List<Task>> routeOrders = new ArrayList<>();
        PermutationUtil permutation = new PermutationUtil();
        permutation.generateAllRouteOrders(tasks, 0, routeOrders);

        double minTime = Double.MAX_VALUE;
        List<Task> bestRoute = null;

        for (List<Task> route : routeOrders) {
            if (isValid(route)) {
                double time = computeTotalTime(aman, route, pt1, pt2);
                if (time < minTime) {
                    minTime = time;
                    bestRoute = new ArrayList<>(route);
                }
            }
        }

        return new Result(bestRoute, minTime);
    }

    private boolean isValid(List<Task> route) {
        Set<String> picked = new HashSet<>();
        for (Task task : route) {
            if (task.getType() == Task.Type.DELIVERY && !picked.contains(task.getConsumer())) {
                return false;
            }
            if (task.getType() == Task.Type.PICKUP) {
                picked.add(task.getConsumer());
            }
        }
        return true;
    }

    private double computeTotalTime(GeoLocation start, List<Task> route, double pt1, double pt2) {
        double elapsed = 0;
        GeoLocation current = start;
        Map<String, Double> prepTimes = Map.of("C1", pt1, "C2", pt2);

        for (Task task : route) {
            double travel = distanceCalculator.computeTravelTime(current, task.getLocation());
            elapsed += travel;

            if (task.getType() == Task.Type.PICKUP) {
                double wait = Math.max(prepTimes.get(task.getConsumer()) - elapsed, 0);
                elapsed += wait;
            }

            current = task.getLocation();
        }

        return elapsed;
    }
    
}
