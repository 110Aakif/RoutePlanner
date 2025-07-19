package com.routeplanner.interfaces;

import com.routeplanner.model.GeoLocation;

public interface IDistanceCalculator {

    double calculateDistance(GeoLocation a, GeoLocation b);

    double computeTravelTime(GeoLocation a, GeoLocation b);

}
