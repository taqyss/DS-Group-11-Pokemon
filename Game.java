/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_project;

/**
 *
 * @author Taqy
 */
import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.util.*;

public class Game {
    private GameMap gameMap;
    private Player player;
    private Scanner scanner;

    public Game() {
        this.gameMap = new GameMap();
        this.player = new Player("Pallet Town");  // Starting location
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            City currentCity = gameMap.getCity(player.getLocation());
            System.out.println("You are currently in " + currentCity.getName());

            displayOptions();

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    movePlayer(currentCity);
                    break;
                case "2":
                    showMap(player.getLocation());
                    break;
                case "3":
                    //fightWildPokemon(currentCity);
                    break;
                case "4":
                    displayOptions();
                    break;
                case "5":
                    enterPokeMaze();
                    break;
                case "6":
                    enterSafariZone();
                    break;
                case "exit":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        scanner.close();
    }

    private void displayOptions() {
        System.out.println("[1] Move to another city");
        System.out.println("[2] Show Map of Kanto");
        System.out.println("[3] Fight Wild Pokémon");
        System.out.println("[4] Player Options");
        System.out.println("[5] Enter PokeMaze");
        System.out.println("[6] Enter Safari Zone");  // New option for Safari Zone
        System.out.println("[exit] Exit game");
    }

    private void enterSafariZone() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Safari Zone! Enter the Pokémon in your party (separated by a comma):");
        String input = scanner.nextLine();
        String[] pokemonNames = input.split(",");

        List<Pokemon> pokemons = new ArrayList<>();
        for (String name : pokemonNames) {
            name = name.trim();
            pokemons.add(new Pokemon(name));
        }

        System.out.println("You entered: " + Arrays.toString(pokemonNames));
        SafariZone safariZone = new SafariZone(pokemons);
        safariZone.sortPokemons();
        safariZone.printFinalSortedPokemons();
    }

    private void displayOptions(City city) {
        System.out.println("Options:");
        System.out.println("1. Move");
        System.out.println("2. Map of Kanto");
        if (city.getGym() != null) {
            System.out.println("Type 'gym' to challenge the gym leader: " + city.getGym().getLeaderName());
        }
        if (!city.getWildPokemon().isEmpty()) {
            System.out.println("Type 'wild' to encounter wild Pokémon");
        }
        System.out.println("Type 'exit' to exit the game");
    }


    private void movePlayer(City currentCity) {
        List<City> adjacentCities = currentCity.getAdjacentCities();
        if (adjacentCities.isEmpty()) {
            System.out.println("There are no cities to move to from here.");
            return;
        }

        System.out.println("Available cities to move to:");
        for (int i = 0; i < adjacentCities.size(); i++) {
            System.out.println((i + 1) + ". " + adjacentCities.get(i).getName());
        }

        System.out.print("Enter the number of the city you want to move to: ");
        int choice = scanner.nextInt() - 1;
        scanner.nextLine();  // Consume the newline left behind by nextInt()

        if (choice >= 0 && choice < adjacentCities.size()) {
            City chosenCity = adjacentCities.get(choice);
            player.setLocation(chosenCity.getName());
            System.out.println("Moving to " + chosenCity.getName() + "...");
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    
    public void showMap(String currentPlayerLocation) {
        String map = 
            "+------------------------------------------------------------------------------------+\n" +
            "Map of Kanto                                                                        \n\n" +
            "[Pewter City]-----------------------------------[Cerulean City]----------------------|\n" +
            "     |                                                  |                            |\n" +
            "     |                                                  |                            |\n" +
            "     |                                                  |                            |\n" +
            "     |                                                  |                            |\n" +
            "     |              [Celadon City]---------------[Saffron City]---------[Lavender Town]\n" +
            "     |                     |                            |                            |\n" +
            "[Viridian City]            |                            |                            |\n" +
            "     |                     |                            |                            |\n" +
            "     |                     |                            |                            |\n" +
            "     |                     |                      [Vermillion City]------------------|\n" +
            "     |                     |                                                         |\n" +
            "[Pallet Town]              |                                                         |\n" +
            "     |                     |                                                         |\n" +
            "     |               [Fuchsia City]--------------------------------------------------|\n" +
            "     |                     |                                                          \n" +
            "     |                     |                                                          \n" +
            "[Cinnabar Island]----------|                                                          \n" +
            "+------------------------------------------------------------------------------------+\n";   

        // Replace the current city name with a marker
        map = map.replace("[" + currentPlayerLocation + "]", "#" + currentPlayerLocation.toUpperCase() + "#");

        System.out.println(map);
    }


    public void encounterWildPokemon(List<Pokemon> wildPokemon) {
        Random rand = new Random();
        Pokemon pokemon = wildPokemon.get(rand.nextInt(wildPokemon.size())); // Select a random Pokémon
        System.out.println("A wild " + pokemon.getName() + " appears!");
        engageBattle(player, pokemon);  // Assuming you have a method to handle battles
    }
    
    public void engageBattle(Player player, Pokemon wildPokemon) {
        System.out.println("You've encountered a wild " + wildPokemon.getName() + "! HP: " + wildPokemon.getHitPoints());
        System.out.println("Choose an action: 1. Fight 2. Catch 3. Run");
        int choice = scanner.nextInt();
        scanner.nextLine();  // Consume newline left-over
        switch (choice) {
            case 1: // Fight
                performFight(player.getTeam().get(0), wildPokemon); // Simplified fighting logic
                break;
            case 2: // Catch
                if (tryToCatch(wildPokemon)) {
                    System.out.println("You caught " + wildPokemon.getName() + "!");
                    player.addPokemon(wildPokemon);
                } else {
                    System.out.println(wildPokemon.getName() + " escaped!");
                }
                break;
            case 3: // Run
                System.out.println("You ran away safely.");
                break;
            default:
                System.out.println("Invalid choice.");
                engageBattle(player, wildPokemon); // Re-engage battle on invalid input
                break;
        }
    }

    public boolean tryToCatch(Pokemon wildPokemon) {
        Random rand = new Random();
        return rand.nextInt(100) < 50; // 50% chance to catch
    }

    public void performFight(Pokemon playerPokemon, Pokemon wildPokemon) {
        String move = "Tackle"; // Simplify by using a default move
        if (playerPokemon.getMoves().containsKey(move)) {
            playerPokemon.performAttack(wildPokemon, move);
            if (wildPokemon.getHitPoints() > 0) {
                // Assuming wild Pokemon also uses Tackle for simplicity
                wildPokemon.performAttack(playerPokemon, move);
                System.out.println("Wild " + wildPokemon.getName() + " now has " + wildPokemon.getHitPoints() + " HP left.");
            }
        } else {
            System.out.println("No such move!");
        }
    }
    
    private void enterPokeMaze() {
        PokeMaze pokeMaze = new PokeMaze();
        pokeMaze.runMaze();
        // Control returns here after the maze is completed or the player is caught
        // You may want to add any updates to the player's state here
    }
}


