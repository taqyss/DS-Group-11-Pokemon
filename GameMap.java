/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_project;

/**
 *
 * @author Taqy
 */
import java.util.Arrays;
import java.util.HashMap;

    public class GameMap {
        private java.util.Map<String, City> cities;

        public GameMap() {
            cities = new HashMap<>();
            initializeCities();
        }

        private void initializeCities() {
            // Create all cities
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
            celadonCity.addAdjacentCity(vermilionCity);
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
    }
