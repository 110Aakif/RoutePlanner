package com.routeplanner;

import com.routeplanner.model.GeoLocation;
import com.routeplanner.service.HaversineCalculator;
import com.routeplanner.service.RoutePlanner;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("Enter Aman model.GeoLocation: ");
        double amanLat = input.nextDouble();
        double amanLon = input.nextDouble();
        GeoLocation amanGeoLocation = new GeoLocation("Aman", amanLat, amanLon);

        System.out.println("\n Enter Restaurant1 model.GeoLocation: ");
        double rest1Lat = input.nextDouble();
        double rest1Lon = input.nextDouble();
        double prepTime1 = input.nextDouble();
        GeoLocation rest1GeoLocation = new GeoLocation("R1", rest1Lat, rest1Lon);

        System.out.println("\n Enter Restaurant2 model.GeoLocation: ");
        double rest2Lat = input.nextDouble();
        double rest2Lon = input.nextDouble();
        double prepTime2 = input.nextDouble();
        GeoLocation rest2GeoLocation = new GeoLocation("R2", rest2Lat, rest2Lon);

        System.out.println("\n Enter Cust1 model.GeoLocation: ");
        double cust1Lat = input.nextDouble();
        double cust1Lon = input.nextDouble();
        GeoLocation cust1GeoLocation = new GeoLocation("C1", cust1Lat, cust1Lon);

        System.out.println("\n Enter Cust2 model.GeoLocation: ");
        double cust2Lat = input.nextDouble();
        double cust2Lon = input.nextDouble();
        GeoLocation cust2GeoLocation = new GeoLocation("C2", cust2Lat, cust2Lon);

        HaversineCalculator distanceCalculator = new HaversineCalculator();
        RoutePlanner planner = new RoutePlanner(distanceCalculator);

        RoutePlanner.Result result = planner.findBestRoute(amanGeoLocation, rest1GeoLocation, prepTime1, rest2GeoLocation, prepTime2, cust1GeoLocation, cust2GeoLocation);

        System.out.println(" Best Route: ");

        result.bestRoute.forEach(task -> System.out.println("->" + task));

        System.out.printf("Total Estimated time: %.2f minutes%n", result.minTime);


    }
}