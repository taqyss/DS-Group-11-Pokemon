/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_project;

/**
 *
 * @author Taqy
 */
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class City {
    private String name;
    private Map<City, Integer> adjacentCities;  //map to store adjacent cities and distances
    private List<Pokemon> wildPokemon;
    private Gym gym;

    public City(String name) {
        this.name = name;
        this.adjacentCities = new HashMap<>();
        this.wildPokemon = new ArrayList<>();
    }

    public void addAdjacentCity(City city, int distance) {
        adjacentCities.put(city, distance);
    }

    public void addWildPokemon(Pokemon pokemon) {
        wildPokemon.add(pokemon);
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }

    public String getName() {
        return name;
    }

    public Map<City, Integer> getAdjacentCities() {
        return adjacentCities;  //getter to access the adjacent cities with distances
    }

    public List<Pokemon> getWildPokemon() {
        return new ArrayList<>(wildPokemon);
    }

    public Gym getGym() {
        return gym;
    }
}


