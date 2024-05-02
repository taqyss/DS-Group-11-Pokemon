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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player {
    private String name;
    private String location;  // Current city location
    private List<Pokemon> team;
    private Set<String> badges;

    public Player(String startingLocation) {
        this.location = startingLocation;
        this.team = new ArrayList<>();
        this.badges = new HashSet<>();
        // Default name can be set here or passed via constructor
        this.name = "Ash";
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Additional methods to manage player's Pokémon and badges
    public void addPokemon(Pokemon pokemon) {
        team.add(pokemon);
    }

    public void addBadge(String badge) {
        badges.add(badge);
    }

    public List<Pokemon> getTeam() {
        return new ArrayList<>(team);
    }

    public Set<String> getBadges() {
        return new HashSet<>(badges);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


