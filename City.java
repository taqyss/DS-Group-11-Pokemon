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

public class City {
    private String name;
    private List<City> adjacentCities;
    private List<Pokemon> wildPokemon;
    private Gym gym;

    public City(String name) {
        this.name = name;
        this.adjacentCities = new ArrayList<>();
        this.wildPokemon = new ArrayList<>();
    }

    public void addAdjacentCity(City city) {
        adjacentCities.add(city);
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

    public List<City> getAdjacentCities() {
        return new ArrayList<>(adjacentCities);
    }

    public List<Pokemon> getWildPokemon() {
        return new ArrayList<>(wildPokemon);
    }

    public Gym getGym() {
        return gym;
    }
}


