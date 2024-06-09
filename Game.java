
package ds_project; 
import java.util.Scanner;
import java.util.List;
import java.util.Random;
import java.util.*;
import java.util.ArrayList;

public class Game { //changes

    private GameMap gameMap;
    private Player player;
    private Scanner scanner;
    private Gym gym;
    private Wild wild;
    private Pokemon pokemon;
    private SaveManager saveManager;
    private AccountManager accountManager;
    private Game game;

    public Game() {
        this.gameMap = new GameMap();
        this.player = new Player("", "Pallet Town", new ArrayList<>(), new ArrayList<>());  // Starting location
        this.scanner = new Scanner(System.in);
        this.gym = new Gym("", new ArrayList<>(), 0, new ArrayList<>(), new ArrayList<>(), "", 0, 0, 0, 0);
        this.wild = new Wild("", new ArrayList<>(), 0, new ArrayList<>(), new ArrayList<>(), 0, 0, 0, 0);
        this.pokemon = new Pokemon("", "", 0, 0, 0, new ArrayList<>(), new ArrayList<>(), new HashMap<>(), new HashMap<>(), 0, 0);
        this.saveManager = new SaveManager();
        this.accountManager = new AccountManager(saveManager);
    }
    
    public Game(Player player){
        this.player = player;
        this.gameMap = new GameMap(); 
    }
    
    public Player getPlayer(){
        return player;
    }
    
    public void enter(){        
        System.out.println("Welcome to Pokemon Game!");
        System.out.println("[1] Create Account");
        System.out.println("[2] Login");
        int choice = scanner.nextInt();
        scanner.nextLine();
        switch (choice) {
            case 1:
                accountManager.createAccount();
                player = accountManager.getCurrentPlayer();
                if (player != null) {
                    player = saveManager.loadGame(player.getName());
                }
                System.out.println("Welcome, " + player.getName());
                start();
                break;
                
            case 2:
                if (accountManager.login()) {
                    player = accountManager.getCurrentPlayer();
                    if (player != null) {
                        player = saveManager.loadGame(player.getName());
                    }
                    System.out.println("Welcome, " + player.getName());
                    start();
                } 
                break;

            default:
                System.out.println("Invalid choice.");
                enter();
                break;
        }
    }
    
    

    public void start() {
        //just nak try jer letak pokemon dulu jadi tak--------------------------------------------------------------------------------------------------------------------------
        if (player.getTeam() == null || player.getTeam().isEmpty()) {
            player.addPokemon(PokemonFactory.createPokemon("starterBulbasaur"));
            System.out.println("Starter Pokémon added: " + player.getTeam().get(0).getName());
        }

        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            City currentCity = gameMap.getCity(player.getLocation());
            System.out.println("You are currently in " + currentCity.getName());

            //set the leader gym and wild pokemon--------------------------------------------------------------------------------------------------------------------------------
            if (currentCity.getName().equals("Pallet Town")) {
                gym.setleaderName("- None");

            } else if (currentCity.getName().equals("Lavender Town")) {

                gym.setleaderName("- None");

            } else if (currentCity.getName().equals("Cinnabar Island")) {
                gym.setleaderName("Blaine");
                gym.setgymBadges("Volcano Badge");
                gym.setleaderPokemon(PokemonFactory.createPokemon("Growlithe"));
                gym.setleaderPokemon(PokemonFactory.createPokemon("Ponyta"));
                gym.setleaderPokemon(PokemonFactory.createPokemon("Magmar"));

            } else if (currentCity.getName().equals("Celadon City")) {
                gym.setleaderName("Erika");
                gym.setgymBadges("Rainbow Badge");
                gym.setleaderPokemon(PokemonFactory.createPokemon("Victreebel"));
                gym.setleaderPokemon(PokemonFactory.createPokemon("Tangela"));
                gym.setleaderPokemon(PokemonFactory.createPokemon("Exeggcute"));

            } else if (currentCity.getName().equals("Cerulean City")) {
                gym.setleaderName("Misty");
                gym.setgymBadges("Cascade Badge");
                gym.setleaderPokemon(PokemonFactory.createPokemon("Staryu"));
                gym.setleaderPokemon(PokemonFactory.createPokemon("Horsea"));
                gym.setleaderPokemon(PokemonFactory.createPokemon("Goldeen"));

            } else if (currentCity.getName().equals("Fuchsia City")) {
                gym.setleaderName("Koga");
                gym.setgymBadges("Soul Badge");
                gym.setleaderPokemon(PokemonFactory.createPokemon("Koffing"));
                gym.setleaderPokemon(PokemonFactory.createPokemon("Muk"));
                gym.setleaderPokemon(PokemonFactory.createPokemon("Arbok"));

            } else if (currentCity.getName().equals("Pewter City")) {
                gym.setleaderName("Brock");
                gym.setgymBadges("Boulder Badge");
                gym.setleaderPokemon(PokemonFactory.createPokemon("Geodude"));
                gym.setleaderPokemon(PokemonFactory.createPokemon("Onix"));

            } else if (currentCity.getName().equals("Saffron City")) {
                gym.setleaderName("Sabrina");
                gym.setgymBadges("Marsh Badge");
                gym.setleaderPokemon(PokemonFactory.createPokemon("Kadabra"));
                gym.setleaderPokemon(PokemonFactory.createPokemon("Venomoth"));
                gym.setleaderPokemon(PokemonFactory.createPokemon("Hypno"));

            } else if (currentCity.getName().equals("Vermilion City")) {
                gym.setleaderName("Lt.Surge");
                gym.setgymBadges("Thunder Badge");
                gym.setleaderPokemon(PokemonFactory.createPokemon("Voltorb"));
                gym.setleaderPokemon(PokemonFactory.createPokemon("Magnemite"));
                gym.setleaderPokemon(PokemonFactory.createPokemon("Pikachu"));

            } else if (currentCity.getName().equals("Viridian City")) {
                gym.setleaderName("Giovanni");
                gym.setgymBadges("Earth Badge");
                gym.setleaderPokemon(PokemonFactory.createPokemon("Rhyhorn"));
                gym.setleaderPokemon(PokemonFactory.createPokemon("Dugtrio"));
                gym.setleaderPokemon(PokemonFactory.createPokemon("Marowak"));

            }

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

                    // Random pick set for wildpokemon
                    Random rad = new Random();
                    int randomchoice = rad.nextInt(10) + 1;

                    if (randomchoice >= 7 && randomchoice <= 10) {
                        List<String> randomallPokemon = Arrays.asList(
                                "Gastly", "Cubone", "Meowth", "Rattata", "Nidoranf", "Nidoranm",
                                "Ponyta", "Magmar", "Grimer", "Abra", "Mr. Mime", "Drowzee",
                                "Venonat", "Koffing", "Ekans", "Pidgey", "Bellsprout", "Magnemite",
                                "Voltorb", "Diglett", "Poliwag", "Goldeen", "Horsea", "Onix",
                                "Sandshrew", "Geodude", "Bulbasaur", "Ivysaur", "Venusaur",
                                "Charmander", "Charmeleon", "Charizard", "Squirtle", "Wartortle",
                                "Blastoise", "Caterpie", "Metapod", "Butterfree", "Weedle",
                                "Kakuna", "Beedrill", "Pidgeotto", "Pidgeot", "Raticate",
                                "Spearow", "Fearow", "Arbok", "Pikachu", "Raichu", "Sandslash",
                                "Nidorina", "Nidoqueen", "Nidorino", "Nidoking", "Clefairy",
                                "Clefable", "Vulpix", "Ninetales", "Jigglypuff", "Wigglytuff",
                                "Zubat", "Golbat", "Oddish", "Gloom", "Vileplume", "Paras",
                                "Parasect", "Venomoth", "Diglett", "Dugtrio", "Persian", "Psyduck",
                                "Golduck", "Mankey", "Primeape", "Growlithe", "Arcanine"
                        );

                        int radchoice = rad.nextInt(randomallPokemon.size());
                        wild.setwildPokemon(PokemonFactory.createPokemon(randomallPokemon.get(radchoice)));
                    } // common pokemon-------------------------------------------------------------------------------------------------------------------------------------------
                    else if (randomchoice >= 1 && randomchoice <= 6) {
                        Map<String, List<String>> locationPokemons = new HashMap<>();
                        locationPokemons.put("Pallet Town", Arrays.asList("Bulbasaur", "Pidgey", "Rattata"));
                        locationPokemons.put("Lavender Town", Arrays.asList("Gastly", "Cubone", "Meowth"));
                        locationPokemons.put("Cinnabar Island", Arrays.asList("Ponyta", "Magmar", "Grimer"));
                        locationPokemons.put("Celadon City", Arrays.asList("Pidgey", "Rattata", "Bellsprout"));
                        locationPokemons.put("Cerulean City", Arrays.asList("Poliwag", "Goldeen", "Horsea"));
                        locationPokemons.put("Fuchsia City", Arrays.asList("Venonat", "Koffing", "Ekans"));
                        locationPokemons.put("Pewter City", Arrays.asList("Geodude", "Onix", "Sandshrew"));
                        locationPokemons.put("Saffron City", Arrays.asList("Abra", "Mr. Mime", "Drowzee"));
                        locationPokemons.put("Vermilion City", Arrays.asList("Magnemite", "Voltorb", "Diglett"));
                        locationPokemons.put("Viridian City", Arrays.asList("Pidgey", "Rattata", "NidoranF"));

                        List<String> pokemons = locationPokemons.getOrDefault(player.getLocation(), Arrays.asList("Pidgey", "Rattata", "NidoranF"));
                        int choicewild = rad.nextInt(pokemons.size());
                        wild.setwildPokemon(PokemonFactory.createPokemon(pokemons.get(choicewild)));
                    }
                    
                    System.out.println("Your Pokemon:");
                    for (int i = 0; i < player.getTeam().size(); i++) {
                        Pokemon pokemon = player.getTeam().get(i);
                        System.out.println((i + 1) + ": " + pokemon.getName() + " - Level: " + pokemon.getLevel());
                    }
                    System.out.println("+----------------------------------------------------------------------+");
                    System.out.print("Please choose your pokemon: ");
                    int choosepokewild = scanner.nextInt() - 1;
                    scanner.nextLine(); // consume the newline character

                    if (choosepokewild >= 0 && choosepokewild < player.getTeam().size()) {
                        Pokemon chosenPokemon = player.getTeam().get(choosepokewild);
                        System.out.println("You have chosen: " + chosenPokemon.getName());
                        WildPokemonBattle.wildbattle(player, wild, player.getLocation(), chosenPokemon);
                        wild.remove(); // to avoid a loop
                    } else {
                        System.out.println("Invalid choice. Please try again.");
                    }
                    break;
                case "4a":
                    int turn = 0;
                    System.out.println("Your Pokemon:");
                    for (Pokemon pokemon : player.getTeam()) {
                        PokemonFactory.ShowPokemon(pokemon);
                        if (turn >= 0) {
                            System.out.println("+----------------------------------------------------------------------+");
                        }
                        turn++;
                    }
                    if (turn == 0) {
                        System.out.println("+----------------------------------------------------------------------+");
                    }
                    scanner.nextLine();
                    break;

                case "4b":
                    System.out.println("Your Badges:\n" + player.getBadges());
                    System.out.println("+----------------------------------------------------------------------+");
                    scanner.nextLine();
                    break;
                case "4c":
                    System.out.println("Saving game for player: " + player.getName());
                    saveManager.saveGame(player.getName(), player);
                    isRunning = false;
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
                    System.out.println("Your Pokemon:");
                    for (int i =0 ; i < player.getTeam().size(); i++) {
                        Pokemon pokemon = player.getTeam().get(i);
                        System.out.println((i+1) + ": " + pokemon.getName() + " - Level: " + pokemon.getLevel());
                    }
                    System.out.println("+----------------------------------------------------------------------+");
                    System.out.print("Please choose your pokemon: ");
                    Scanner scangym = new Scanner (System.in);
                    int choosepokegym = scangym.nextInt()-1;
                    scanner.nextLine();

                    if (choosepokegym >= 0 && choosepokegym  < player.getTeam().size()) {
                        Pokemon chosenPokemon = player.getTeam().get(choosepokegym );
                        System.out.println("You have chosen: " + chosenPokemon.getName());
                    }
                   
                    GymBattle.battle(player, gym);
                    break;
                case "8":
                    pokemon.PokemonCenter(player);

                    System.out.println("Welcome to the Pokémon Center! Your Pokémon are being healed. Please wait a moment...");

                    try {
                        Thread.sleep(2000); // Delay for 3 seconds (3000 milliseconds)
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Restore the interrupted status
                    }

                    System.out.println("Your Pokémon are fully healed! Thank you for waiting.");
                    System.out.println("+----------------------------------------------------------------------+");
                    break;
                case "exit":
                    isRunning = false;
                    System.out.println("Game Not Saved");
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
        System.out.print("[3] Fight Wild Pokémon ");

        if (currentCity.getName().equals("Pallet Town")) {
            System.out.print("[Pidgey, Rattata, Bulbasaur are common]\n");
        } else if (currentCity.getName().equals("Lavender Town")) {
            System.out.print("[Gastly, Cubone, Meowth are common]\n");
        } else if (currentCity.getName().equals("Cinnabar Island")) {
            System.out.print("[Ponyta, Magmar, Grimer are common]\n");
        } else if (currentCity.getName().equals("Celadon City")) {
            System.out.print("[Pidgey, Rattata, Bellsprout are common]\n");
        } else if (currentCity.getName().equals("Cerulean City")) {
            System.out.print("[Poliwag, Goldeen, Horsea are common]\n");
        } else if (currentCity.getName().equals("Fuchsia City")) {
            System.out.print("[Venonat, Koffing, Ekans are common]\n");
        } else if (currentCity.getName().equals("Pewter City")) {
            System.out.print("[Onix, Geodude, Sandshrew are common]\n");
        } else if (currentCity.getName().equals("Saffron City")) {
            System.out.print("[Abra, Mr. Mime, Drowzee are common]\n");
        } else if (currentCity.getName().equals("Vermilion City")) {
            System.out.print("[Magnemite, Voltorb, Diglett are common]\n");
        } else if (currentCity.getName().equals("Viridian City")) {
            System.out.print("[Pidgey, Rattata, Nidoran are common]\n");
        }

        System.out.println("[4] Player Options");
        System.out.printf("a.Show My Pokemon   b.Show My badges   c.Save and Exit\n");
        System.out.println("[5] Enter Safari Zone");
        if (currentCity.getName().equals("Lavender Town")) {
            System.out.println("[6] Enter PokeMaze");
        }
        if (currentCity.getName().equals("Saffron City")) {
            System.out.println("[6] Rival's Race");  // New option for Rival's Race only in Saffron City
        }
        System.out.println("[7] Challenge Gym Leader");
        System.out.println("[8] Go to Pokemon Center");
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


