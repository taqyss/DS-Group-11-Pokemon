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

public class SafariZone {
    private List<Pokemon> pokemons;
    private List<Pokemon> sortedPokemons;

    public SafariZone(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
        this.sortedPokemons = new ArrayList<>(Collections.nCopies(pokemons.size(), null));
    }

    public void sortPokemons() {
        System.out.println("Sorting your Pokémon according to their unique preferences...");

        // Step 1: Place Eevee at the beginning
        placePokemon("Eevee", 0);
        printStep("Step 1: Eevee insists on being positioned either at the beginning of the lineup to showcase its adaptability.");

        // Step 2: Place Pikachu in the center
        placePokemon("Pikachu", sortedPokemons.size() / 2);
        printStep("Step 2: Pikachu demands to be placed at the center of the arrangement.");

        // Step 3: Place Snorlax at the end
        placePokemon("Snorlax", sortedPokemons.size() - 1);
        printStep("Step 3: Snorlax insists on being positioned at the end of the lineup to ensure maximum relaxation.");

        // Step 4: Place Jigglypuff next to Pikachu
        placePokemonNextTo("Jigglypuff", "Pikachu");
        printStep("Step 4: Jigglypuff prefers to be surrounded by other 'cute' Pokémon for morale purposes.");

        // Step 5: Place Bulbasaur ensuring it's not next to Charmander
        placePokemonAvoiding("Bulbasaur", "Charmander");
        printStep("Step 5: Bulbasaur refuses to be placed next to Charmander.");

        // Step 6: Place Machop next to Snorlax
        placePokemonNextTo("Machop", "Snorlax");
        printStep("Step 6: Machop demands to be placed next to the heaviest Pokémon in the lineup to show off its strength.");

        // Step 7: Place Charmander in any remaining spot
        placeRemainingPokemon("Charmander");
        printStep("Step 7: Charmander is placed in any remaining spot.");

        System.out.println("+----------------------------------------------------------------------+");
        System.out.println("Your Pokémon are now sorted! Enjoy your adventure in the Safari Zone!");
        System.out.println("+----------------------------------------------------------------------+");
    }

    private void placePokemon(String name, int position) {
        int index = findPokemonIndex(name);
        if (index != -1) {
            sortedPokemons.set(position, pokemons.get(index));
        }
    }

    private void placePokemonNextTo(String name, String neighbor) {
        int neighborIndex = findPokemonIndex(neighbor, sortedPokemons);
        if (neighborIndex != -1) {
            if (neighborIndex > 0 && sortedPokemons.get(neighborIndex - 1) == null) {
                sortedPokemons.set(neighborIndex - 1, pokemons.get(findPokemonIndex(name)));
            } else if (neighborIndex < sortedPokemons.size() - 1 && sortedPokemons.get(neighborIndex + 1) == null) {
                sortedPokemons.set(neighborIndex + 1, pokemons.get(findPokemonIndex(name)));
            }
        }
    }

    private void placePokemonAvoiding(String name, String avoidNeighbor) {
        int avoidIndex = findPokemonIndex(avoidNeighbor, sortedPokemons);
        if (avoidIndex == -1) {
            // If the avoidNeighbor is not found, place the Pokémon in any remaining spot
            placeRemainingPokemon(name);
            return;
        }

        for (int i = 0; i < sortedPokemons.size(); i++) {
            if (sortedPokemons.get(i) == null && (i == 0 || !isNeighbor(i - 1, avoidNeighbor))
                    && (i == sortedPokemons.size() - 1 || !isNeighbor(i + 1, avoidNeighbor))) {
                sortedPokemons.set(i, pokemons.get(findPokemonIndex(name)));
                break;
            }
        }
    }

    private boolean isNeighbor(int index, String name) {
        return index >= 0 && index < sortedPokemons.size() && sortedPokemons.get(index) != null && sortedPokemons.get(index).getName().equals(name);
    }

    private void placeRemainingPokemon(String name) {
        for (int i = 0; i < sortedPokemons.size(); i++) {
            if (sortedPokemons.get(i) == null) {
                sortedPokemons.set(i, pokemons.get(findPokemonIndex(name)));
                break;
            }
        }
    }

    private int findPokemonIndex(String name) {
        for (int i = 0; i < pokemons.size(); i++) {
            if (pokemons.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private int findPokemonIndex(String name, List<Pokemon> list) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) != null && list.get(i).getName().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    private void printStep(String message) {
        System.out.println(message);
        System.out.println("Partial Sort: " + sortedPokemons);
    }

    public void printFinalSortedPokemons() {
        System.out.println("Final Sorted List: " + sortedPokemons);
    }
}





