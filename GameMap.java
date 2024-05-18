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

        // Connect cities as per the Kanto map
        palletTown.addAdjacentCity(viridianCity);
        palletTown.addAdjacentCity(cinnabarIsland);
        viridianCity.addAdjacentCity(palletTown);
        viridianCity.addAdjacentCity(pewterCity);
        pewterCity.addAdjacentCity(viridianCity);
        pewterCity.addAdjacentCity(ceruleanCity);
        ceruleanCity.addAdjacentCity(pewterCity);
        ceruleanCity.addAdjacentCity(lavenderTown);
        ceruleanCity.addAdjacentCity(saffronCity);
        lavenderTown.addAdjacentCity(ceruleanCity);
        lavenderTown.addAdjacentCity(vermilionCity);
        vermilionCity.addAdjacentCity(lavenderTown);
        vermilionCity.addAdjacentCity(saffronCity);
        vermilionCity.addAdjacentCity(fuchsiaCity);
        celadonCity.addAdjacentCity(saffronCity);
        celadonCity.addAdjacentCity(fuchsiaCity);
        fuchsiaCity.addAdjacentCity(celadonCity);
        fuchsiaCity.addAdjacentCity(vermilionCity);
        fuchsiaCity.addAdjacentCity(cinnabarIsland);
        cinnabarIsland.addAdjacentCity(fuchsiaCity);
        cinnabarIsland.addAdjacentCity(palletTown);
        saffronCity.addAdjacentCity(ceruleanCity);
        saffronCity.addAdjacentCity(vermilionCity);
        saffronCity.addAdjacentCity(celadonCity);
        saffronCity.addAdjacentCity(lavenderTown);

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
            for (City adjacent : city.getAdjacentCities()) {
                if (adjacent.getName().equals(city2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<String> findShortestPath(String start, String end) {
        Map<String, Integer> distances = new HashMap<>();
        Map<String, String> previousCities = new HashMap<>();
        PriorityQueue<String> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(distances::get));

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

            for (City neighbor : cities.get(currentCity).getAdjacentCities()) {
                int altDistance = distances.get(currentCity) + 1; // Assuming all edges have the same weight
                if (altDistance < distances.get(neighbor.getName())) {
                    distances.put(neighbor.getName(), altDistance);
                    previousCities.put(neighbor.getName(), currentCity);
                    priorityQueue.add(neighbor.getName());
                }
            }
        }

        List<String> path = new ArrayList<>();
        for (String at = end; at != null; at = previousCities.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);
        return path;
    }
}
