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

public class Gym {
    private String leaderName;
    private List<Pokemon> leaderPokemon;

    public Gym(String leaderName) {
        this.leaderName = leaderName;
        this.leaderPokemon = new ArrayList<>();
    }

    public void addPokemon(Pokemon pokemon) {
        leaderPokemon.add(pokemon);
    }

    public String getLeaderName() {
        return leaderName;
    }

    public List<Pokemon> getLeaderPokemon() {
        return new ArrayList<>(leaderPokemon);
    }
}


