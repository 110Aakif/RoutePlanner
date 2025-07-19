# Route Optimizer

A modular and extensible route planning system that calculates the optimal delivery route for a given set of pickup and drop points using geographical coordinates.

---

## Overview

This project calculates the shortest delivery route by evaluating all permutations of tasks while respecting delivery constraints (i.e., a pickup must occur before its corresponding drop). It uses geographic coordinates (latitude and longitude) and applies the **Haversine formula** to compute real-world distances.

---

## Key Components

### 1. **GeoLocation**
- Represents a physical point with a name, latitude, and longitude.
- Encapsulates all location data.

### 2. **Task**
- Represents a pickup or drop action associated with a GeoLocation.
- Contains metadata such as type (PICKUP or DROP) and a task ID for grouping.

### 3. **DistanceCalculator (Interface)**
- Abstracts the distance computation logic.
- Enables swapping between multiple strategies (e.g., Haversine, Euclidean, external APIs).

### 4. **HaversineCalculator**
- Concrete implementation of `IDistanceCalculator` using the Haversine formula for spherical distance.

### 5. **RoutePlanner**
- Core logic that:
  - Generates all valid permutations of pickup-drop sequences.
  - Uses the injected `IDistanceCalculator` to evaluate the total distance.
  - Selects the most optimal route.
- Designed to be **modular** and **testable**.

---

## Design Principles Applied

| Principle       | Description |
|----------------|-------------|
| **Modularity** | Code is organized into focused classes (model, service, util). |
| **Encapsulation** | Internal details of how distance is calculated or tasks are validated are hidden. |
| **Readability** | Method names and structure are clear and self-explanatory. |
| **Extensibility** | New distance strategies or optimization rules can be added without changing existing logic. |

---

## How to Run

1. Clone the repository
2. Make sure you have Java 21 and Maven installed
3. Navigate to the project root and run:

```bash
mvn clean install
mvn exec:java -Dexec.mainClass="Main"
