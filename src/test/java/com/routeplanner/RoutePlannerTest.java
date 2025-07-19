package com.routeplanner;

import com.routeplanner.model.GeoLocation;
import com.routeplanner.service.HaversineCalculator;
import com.routeplanner.service.RoutePlanner;
import org.junit.Test;
import static org.junit.Assert.*;

public class RoutePlannerTest {

    @Test
    public void testValidShortestPath1() {
        GeoLocation aman = new GeoLocation("Aman", 12.935, 77.614);
        GeoLocation r1 = new GeoLocation("R1", 12.937, 77.620);
        GeoLocation r2 = new GeoLocation("R2", 12.931, 77.618);
        GeoLocation c1 = new GeoLocation("C1", 12.940, 77.625);
        GeoLocation c2 = new GeoLocation("C2", 12.928, 77.622);

        double pt1 = 10;
        double pt2 = 8;

        HaversineCalculator distanceCalculator = new HaversineCalculator();
        RoutePlanner planner = new RoutePlanner(distanceCalculator);
        RoutePlanner.Result result = planner.findBestRoute(aman, r1, pt1, r2, pt2, c1, c2);

        assertNotNull(result);
        assertEquals(4, result.bestRoute.size());
        assertTrue(result.minTime > 0);

        System.out.println(" Best Route:");
        result.bestRoute.forEach(task -> System.out.println(" -> " + task));
        System.out.printf("Total Time: %.2f minutes\n", result.minTime);
    }

    @Test
    public void testValidShortestPath2() {
        GeoLocation aman = new GeoLocation("Aman", 12.935, 77.614);
        GeoLocation r1 = new GeoLocation("R1", 12.937, 77.620);
        GeoLocation r2 = new GeoLocation("R2", 12.931, 77.618);
        GeoLocation c1 = new GeoLocation("C1", 12.940, 77.625);
        GeoLocation c2 = new GeoLocation("C2", 12.938, 77.622);

        double pt1 = 10; // preparation time at R1 (in minutes)
        double pt2 = 80;  // preparation time at R2 (in minutes)

        HaversineCalculator distanceCalculator = new HaversineCalculator();

        RoutePlanner planner = new RoutePlanner(distanceCalculator);
        RoutePlanner.Result result = planner.findBestRoute(aman, r1, pt1, r2, pt2, c1, c2);

        assertNotNull(result);
        assertEquals(4, result.bestRoute.size());
        assertTrue(result.minTime > 0);

        System.out.println(" Best Route: ");

        result.bestRoute.forEach(task -> System.out.println("->" + task));

        System.out.printf("Total Estimated time: %.2f minutes%n", result.minTime);
    }
}
