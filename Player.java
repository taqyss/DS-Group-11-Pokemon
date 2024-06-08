
package project.pikachu; //3 weeks commit

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Player implements Serializable{ //CHANGES new updated
    
    private String name;
    private String location;  // Current city location
    private List<Pokemon> team; //pokemon dlm team 
    private List<String> badges;

    public Player(String name, String startingLocation, List<Pokemon> team, List<String> badges) {
        this.location = startingLocation;
        this.team = team;
        this.badges = badges;
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // Additional methods to manage player's Pokémon and badges
    public void addPokemon(Pokemon pokemon) {
        if (team == null) {
            team = new ArrayList<>();
        }
        team.add(pokemon);
    }

    public void addBadge(String badge) {
        if (badges == null) {
            badges = new ArrayList<>();
        }
        badges.add(badge);
    }

    public List<Pokemon> getTeam() {
        return new ArrayList<>(team);
    }

    public Set<String> getBadges() {   //CHANGES
        if (badges == null || badges.isEmpty()) {
        Set<String> noBadges = new HashSet<>();
        noBadges.add("- None");
        return noBadges;
    }

        return new HashSet<>(badges);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void CatchPokemon(Pokemon wild) {
        if (getTeam().size() >=0 && getTeam().size() <6) {
            addPokemon(wild);
        } else {
            System.out.println("You already have 6 Pokemon");
        }
    }
}
