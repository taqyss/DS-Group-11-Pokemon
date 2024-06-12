/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_project;

/**
 *
 * @author Taqy
 */
import java.util.*;

public class GameMap {
    private Map<String, City> cities;

    public GameMap() {
        cities = new HashMap<>();
        initializeCities();
    }

    private void initializeCities() {
        // Initialize cities and their connections
        City palletTown = new City("Pallet Town");
        City viridianCity = new City("Viridian City");
        City pewterCity = new City("Pewter City");
        City ceruleanCity = new City("Cerulean City");
        City lavenderTown = new City("Lavender Town");
        City vermilionCity = new City("Vermillion City");
        City celadonCity = new City("Celadon City");
        City fuchsiaCity = new City("Fuchsia City");
        City cinnabarIsland = new City("Cinnabar Island");
        City saffronCity = new City("Saffron City");

        // Setup connections based on the provided map distances
        palletTown.addAdjacentCity(viridianCity, 5);
        palletTown.addAdjacentCity(cinnabarIsland, 7);
        viridianCity.addAdjacentCity(palletTown, 5);
        viridianCity.addAdjacentCity(pewterCity, 8);
        pewterCity.addAdjacentCity(viridianCity, 8);
        pewterCity.addAdjacentCity(ceruleanCity, 12);
        ceruleanCity.addAdjacentCity(pewterCity, 12);
        ceruleanCity.addAdjacentCity(lavenderTown, 9);
        ceruleanCity.addAdjacentCity(saffronCity, 6);
        lavenderTown.addAdjacentCity(ceruleanCity, 9);
        lavenderTown.addAdjacentCity(vermilionCity, 5);
        lavenderTown.addAdjacentCity(fuchsiaCity, 11);
        lavenderTown.addAdjacentCity(saffronCity, 3);
        vermilionCity.addAdjacentCity(lavenderTown, 5);
        vermilionCity.addAdjacentCity(saffronCity, 3);
        vermilionCity.addAdjacentCity(fuchsiaCity, 7);
        celadonCity.addAdjacentCity(saffronCity, 4);
        celadonCity.addAdjacentCity(fuchsiaCity, 10);
        fuchsiaCity.addAdjacentCity(celadonCity, 10);
        fuchsiaCity.addAdjacentCity(vermilionCity, 7);
        fuchsiaCity.addAdjacentCity(cinnabarIsland, 5);
        fuchsiaCity.addAdjacentCity(lavenderTown, 11);
        cinnabarIsland.addAdjacentCity(fuchsiaCity, 5);
        cinnabarIsland.addAdjacentCity(palletTown, 7);
        saffronCity.addAdjacentCity(ceruleanCity, 6);
        saffronCity.addAdjacentCity(vermilionCity, 3);
        saffronCity.addAdjacentCity(celadonCity, 4);
        saffronCity.addAdjacentCity(lavenderTown, 3);

        // Add cities to the map
        cities.put(palletTown.getName(), palletTown);
        cities.put(viridianCity.getName(), viridianCity);
        cities.put(pewterCity.getName(), pewterCity);
        cities.put(ceruleanCity.getName(), ceruleanCity);
        cities.put(lavenderTown.getName(), lavenderTown);
        cities.put(vermilionCity.getName(), vermilionCity);
        cities.put(celadonCity.getName(), celadonCity);
        cities.put(fuchsiaCity.getName(), fuchsiaCity);
        cities.put(cinnabarIsland.getName(), cinnabarIsland);
        cities.put(saffronCity.getName(), saffronCity);
    }

    public City getCity(String name) {
        return cities.get(name);
    }

    public Set<String> getAllCities() {
        return cities.keySet();
    }

    public boolean isDirectlyAdjacent(String city1, String city2) {
        City city = cities.get(city1);
        if (city != null) {
            // Iterate over the entry set of the map
            for (Map.Entry<City, Integer> entry : city.getAdjacentCities().entrySet()) {
                City adjacent = entry.getKey();  // Get the city from the entry set
                if (adjacent.getName().equals(city2)) {
                    return true;  // Return true if the adjacent city's name matches city2
                }
            }
        }
        return false;  // Return false if no matching city is found or city1 is not in the map
    }

    public List<String> findShortestPath(String start, String end) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previousCities = new HashMap<>();
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

        // Initialize all cities with distance infinity except for the start city
        for (String city : cities.keySet()) {
            if (city.equals(start)) {
                distances.put(city, 0);
            } else {
                distances.put(city, Integer.MAX_VALUE);
            }
            priorityQueue.add(city);
        }

        while (!priorityQueue.isEmpty()) {
            String currentCity = priorityQueue.poll();
            if (currentCity.equals(end)) break;

            City cityObj = cities.get(currentCity);  // Get the City object for the currentCity name
            if (cityObj != null) {
                for (Map.Entry<City, Integer> neighborEntry : cityObj.getAdjacentCities().entrySet()) {
                    City neighbor = neighborEntry.getKey();
                    Integer distance = neighborEntry.getValue();
                    int altDistance = distances.get(currentCity) + distance;  // Correctly use the edge weight
                    if (altDistance < distances.get(neighbor.getName())) {
                        distances.put(neighbor.getName(), altDistance);
                        previousCities.put(neighbor.getName(), currentCity);
                        priorityQueue.add(neighbor.getName());
                    }
                }
            }
        }

        // Construct the path from end to start
        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = previousCities.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }

}
