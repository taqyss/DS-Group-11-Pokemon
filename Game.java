package project.pikachu; //3 days commit

import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.util.*;

public class Game {

    private GameMap gameMap;
    private Player player;
    private Scanner scanner;
    private Gym gym;

    public Game() {
        this.gameMap = new GameMap();
        this.player = new Player("Pallet Town");  // Starting location
        this.scanner = new Scanner(System.in);
        this.gym = new Gym("", new ArrayList<>(), 0, new ArrayList<>(), new ArrayList<>(), "" ,0 ,0 ,0 ,0 );
    }

    public void start() {
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            City currentCity = gameMap.getCity(player.getLocation());
            System.out.println("You are currently in " + currentCity.getName());

            //set the leader gym 
            if (currentCity.getName().equals("Pallet Town") || currentCity.getName().equals("Lavender Town")) {
                gym.setleaderName("- None");
            } else if (currentCity.getName().equals("Cinnabar Island")) {
                gym.setleaderName("Blaine");
                gym.setgymBadges("Volcano Badge");
                PokemonFactory.createPokemon("Growlithe");
                PokemonFactory.createPokemon("Ponyta");
                PokemonFactory.createPokemon("Magmar");
                PokemonFactory.createPokemon("Rapidash");
                PokemonFactory.createPokemon("Ninetales");
                PokemonFactory.createPokemon("Arcanine");
            } else if (currentCity.getName().equals("Celadon City")) {
                gym.setleaderName("Erika");
                gym.setgymBadges("Rainbow Badge");
                PokemonFactory.createPokemon("Victreebel");
                PokemonFactory.createPokemon("Tangela");
                PokemonFactory.createPokemon("Exeggcute");
                PokemonFactory.createPokemon("Parasect");
                PokemonFactory.createPokemon("Vileplume");
                PokemonFactory.createPokemon("Exeggutor");

            } else if (currentCity.getName().equals("Cerulean City")) {
                gym.setleaderName("Misty");
                gym.setgymBadges("Cascade Badge");
                PokemonFactory.createPokemon("Staryu");
                PokemonFactory.createPokemon("Horsea");
                PokemonFactory.createPokemon("Goldeen");
                PokemonFactory.createPokemon("Psyduck");
                PokemonFactory.createPokemon("Starmie");

            } else if (currentCity.getName().equals("Fuchsia City")) {
                gym.setleaderName("Koga");
                gym.setgymBadges("Soul Badge");
                PokemonFactory.createPokemon("Koffing");
                PokemonFactory.createPokemon("Muk");
                PokemonFactory.createPokemon("Arbok");
                PokemonFactory.createPokemon("Weezing");
                PokemonFactory.createPokemon("Nidoqueen");

            } else if (currentCity.getName().equals("Pewter City")) {
                gym.setleaderName("Brock");
                gym.setgymBadges("Boulder Badge");
                PokemonFactory.createPokemon("Geodude");
                PokemonFactory.createPokemon("Onix");
            } else if (currentCity.getName().equals("Saffron City")) {
                gym.setleaderName("Sabrina");
                gym.setgymBadges("Marsh Badge");
                PokemonFactory.createPokemon("Kadabra");
                PokemonFactory.createPokemon("Mr.Mime");
                PokemonFactory.createPokemon("Venomoth");
                PokemonFactory.createPokemon("Hypno");
                PokemonFactory.createPokemon("Slowbro");
                PokemonFactory.createPokemon("Alakazam");
            } else if (currentCity.getName().equals("Vermilion City")) {
                gym.setleaderName("Lt.Surge");
                gym.setgymBadges("Thunder Badge");
                PokemonFactory.createPokemon("Voltorb");
                PokemonFactory.createPokemon("Magnemite");
                PokemonFactory.createPokemon("Pikachu");
                PokemonFactory.createPokemon("Magneton");
                PokemonFactory.createPokemon("Raichu");
                PokemonFactory.createPokemon("Electabuzz");

            } else if (currentCity.getName().equals("Viridian City")) {
                gym.setleaderName("Giovanni");
                gym.setgymBadges("Earth Badge");
                gym.setleaderPokemon(PokemonFactory.createPokemon("Rhyhorn"));
                gym.setleaderPokemon(PokemonFactory.createPokemon("Dugtrio"));
                gym.setleaderPokemon(PokemonFactory.createPokemon("Marowak"));
                gym.setleaderPokemon(PokemonFactory.createPokemon("Nidoqueen"));
                gym.setleaderPokemon(PokemonFactory.createPokemon("Nidoking"));

            }

            player.addPokemon(PokemonFactory.createPokemon("Pikachu"));

            displayOptions(currentCity);

            System.out.println("+----------------------------------------------------------------------+");
            System.out.print("Your choice: ");
            String choice = scanner.nextLine();
            System.out.println("+----------------------------------------------------------------------+");

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
                case "4a":
                    //displayOptions();
                    break;
                case "4b":  // CHANGE 
                    System.out.println("Your Pokemon:");
                    for (Pokemon pokemon : player.getTeam()) {
                        PokemonFactory.ShowPokemon(pokemon);
                    }
                    System.out.println("+----------------------------------------------------------------------+");
                    scanner.nextLine();
                    break;

                case "4c":  //CHANGE
                    System.out.println("Your Badges:\n" + player.getBadges());
                    System.out.println("+----------------------------------------------------------------------+");
                    scanner.nextLine();
                    break;
                case "4d":
                    //displayOptions();
                    break;
                case "6":
                    if (currentCity.getName().equals("Lavender Town")) {
                        enterPokeMaze();
                    } else if (currentCity.getName().equals("Saffron City")) {
                        startRivalRace();
                    } else {
                        System.out.println("That option is not available here.");
                    }
                    break;
                case "5":
                    enterSafariZone();
                    break;
                case "7"://setiap leader 
                    GymBattle.battle(player, gym);
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

    private void displayOptions(City currentCity) { //CHANGE
        System.out.println("[1] Move to another city");
        System.out.println("[2] Open Map of Kanto");
        System.out.println("[3] Fight Wild Pokémon");
        System.out.println("[4] Player Options");
        System.out.printf("a.Show map   b.Show My Pokemon   c.Show My badges   d.Save and Exit\n");
        System.out.println("[5] Enter Safari Zone");
        if (currentCity.getName().equals("Lavender Town")) {
            System.out.println("[6] Enter PokeMaze");
        }
        if (currentCity.getName().equals("Saffron City")) {
            System.out.println("[6] Rival's Race");  // New option for Rival's Race only in Saffron City
        }
        System.out.println("[7] Challenge Gym Leader");
        System.out.println("[exit] Exit game\n");
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

    private void startRivalRace() {
        String startCity = "Saffron City";
        String destination = getRandomDestination(startCity);

        if (destination == null) {
            System.out.println("No valid destination found for the race.");
            return;
        }

        System.out.println("The battle has begun! Your rival Gary has challenged you to a race to " + destination + ".");
        List<String> shortestPath = gameMap.findShortestPath(startCity, destination);

        System.out.println("Shortest Path:");
        for (int i = 0; i < shortestPath.size() - 1; i++) {
            System.out.print(shortestPath.get(i) + " -> ");
        }
        System.out.println(destination);
        System.out.println("Good luck on your race!");
    }

    private String getRandomDestination(String startCity) {
        List<String> allCities = new ArrayList<>(gameMap.getAllCities());
        Collections.shuffle(allCities);

        for (String city : allCities) {
            if (!city.equals(startCity) && !gameMap.isDirectlyAdjacent(startCity, city)) {
                return city;
            }
        }
        return null; // This should not happen if there are valid destinations
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
        String map
                = "+------------------------------------------------------------------------------------+\n"
                + "Map of Kanto                                                                        \n\n"
                + "[Pewter City]-----------------------------------[Cerulean City]----------------------|\n"
                + "     |                                                  |                            |\n"
                + "     |                                                  |                            |\n"
                + "     |                                                  |                            |\n"
                + "     |                                                  |                            |\n"
                + "     |              [Celadon City]---------------[Saffron City]---------[Lavender Town]\n"
                + "     |                     |                            |                            |\n"
                + "[Viridian City]            |                            |                            |\n"
                + "     |                     |                            |                            |\n"
                + "     |                     |                            |                            |\n"
                + "     |                     |                      [Vermillion City]------------------|\n"
                + "     |                     |                                                         |\n"
                + "[Pallet Town]              |                                                         |\n"
                + "     |                     |                                                         |\n"
                + "     |               [Fuchsia City]--------------------------------------------------|\n"
                + "     |                     |                                                          \n"
                + "     |                     |                                                          \n"
                + "[Cinnabar Island]----------|                                                          \n"
                + "+------------------------------------------------------------------------------------+\n";

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
    }

}
