package com.routeplanner.model;

public class Task {
    public enum Type { PICKUP, DELIVERY }

    private final Type type;
    private final GeoLocation location;
    private final String consumer;

    public Task(Type type, GeoLocation location, String consumer) {
        this.type = type;
        this.location = location;
        this.consumer = consumer;
    }

    public Type getType() {
        return type;
    }

    public GeoLocation getLocation() {
        return location;
    }

    public String getConsumer() {
        return consumer;
    }

    @Override
    public String toString() {
        return type + "@" + location.getName();
    }
}
