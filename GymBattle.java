package project.pikachu; //3 weeks commit

import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class GymBattle {

    private static Scanner scanner = new Scanner(System.in);

    public static void battle(Player player, Gym gym) {
        if (player.getTeam().stream().noneMatch(pokemon -> pokemon.getcurrentHitPoints() > 0)) {
            System.out.println("None of your Pokémon have HP left! You need to restore their HP.");
            System.out.println("Please go to the nearest Pokemon Center to heal your Pokémon.");
            System.out.println("+----------------------------------------------------------------------+");
            return; // Stop execution if no Pokémon have HP
        }

        if (player.getTeam().isEmpty()) {
            System.out.println("You have no Pokémon to fight with! Catch or receive one first.");
            System.out.println("+----------------------------------------------------------------------+");
            return;
        }

        if (player.getLocation().equals("Pallet Town") || player.getLocation().equals("Lavender Town")) {
            System.out.println("There is no Gym Leader in this town");
            return;
        }

        if (gym.getleaderPokemon().isEmpty()) {
            System.out.println("There seems to be an error: Gym Leader " + gym.getleaderName() + " has no Pokémon.");
            return;
        }

        System.out.println("You are about to challenge Gym Leader " + gym.getleaderName() + "!");
        System.out.println("Prepare yourself for an intense battle! ");

        List<Pokemon> gymTeam = new ArrayList<>(gym.getleaderPokemon()); // Copy the gym leader's team
        boolean playerWon = true;

        for (Pokemon gymPokemon : gymTeam) {
            Pokemon playerPokemon = player.getTeam().get(0); // Assuming the player has Pokémon

            System.out.println("Your Pokémon: " + playerPokemon.getName() + " - Level: " + playerPokemon.getLevel());
            System.out.println("+----------------------------------------------------------------------+");
            System.out.println("Battle Start: Trainer " + player.getName() + " vs. Gym Leader " + gym.getleaderName() + "!");
            //tambah code to show how many pokemon do leader have
            System.out.println(gym.getleaderName() + " sends out " + gymPokemon.getName() + " [Level: " + gymPokemon.getLevel() + "]!");

            if (playerPokemon.getStrongAgainst().containsAll(gymPokemon.getWeakAgainst())) {
                System.out.println(playerPokemon.getName() + " is sent out! Its " + playerPokemon.getType() + " type is strong against the opponent's " + gymPokemon.getName() + ".");
            } else if (gymPokemon.getStrongAgainst().containsAll(playerPokemon.getWeakAgainst())) {
                System.out.println(playerPokemon.getName() + " is sent out! Its " + playerPokemon.getType() + " type is weak against the opponent's " + gymPokemon.getName() + ".");
            } else {
                System.out.println(playerPokemon.getName() + " is sent out! Its " + playerPokemon.getType() + " type is normal against the opponent's " + gymPokemon.getName() + ".");
            }

            // Battle loop
            int round = 1;
            int gainXP = 0;
            while (playerPokemon.getcurrentHitPoints() > 0 && gymPokemon.getcurrentHitPoints() > 0) {
                System.out.println("Round " + round + ":");
                System.out.println(playerPokemon.getName() + "'s Moves:");
                Map<String, Integer> moves = playerPokemon.getMoves();
                int index = 1;
                for (String move : moves.keySet()) {
                    System.out.println(index + ". " + move);
                    index++;
                }
                System.out.println(index + ". Run"); // Adding the "Run" option
                System.out.print("Which move will " + playerPokemon.getName() + " use?\nYour choice: ");
                int selectedMoveIndex = scanner.nextInt();

                if (selectedMoveIndex == index) { // Player chose to run
                    if (wildrun()) {
                        return; // End the battle if the player chooses to run
                    }
                } else {
                    String selectedMove = playerPokemon.getMoves().keySet().toArray(new String[0])[selectedMoveIndex - 1];
                    System.out.println("+----------------------------------------------------------------------+");
                    attack(playerPokemon, gymPokemon, selectedMove);
                }

                if (gymPokemon.getcurrentHitPoints() <= 0) {
                    System.out.println(gymPokemon.getName() + " fainted!\n");
                    int XPGained = 5 * gymPokemon.getLevel();
                    playerPokemon.setcurrentXP(playerPokemon.getcurrentXP() + XPGained); // Add the gained XP to the current XP

                    // Check for level up
                    while (playerPokemon.getcurrentXP() >= playerPokemon.getXP()) {
                        int excessXP = playerPokemon.getcurrentXP() - playerPokemon.getXP();
                        playerPokemon.levelUp(playerPokemon.getXP(), playerPokemon.getHitPoints());
                        playerPokemon.setcurrentXP(excessXP); // Set the remaining XP after leveling up
                    }

                    System.out.println(playerPokemon.getName() + " gained " + XPGained + " XP.");
                    System.out.println(playerPokemon.getName() + " [XP: " + playerPokemon.getcurrentXP() + "/" + playerPokemon.getXP() + "]");

                    if (playerPokemon.getcurrentXP() >= playerPokemon.getXP()) {
                        int oldLevel = playerPokemon.getLevel() - 1;
                        System.out.println(playerPokemon.getName() + " leveled up. ");
                        System.out.println(playerPokemon.getName() + " [Level " + oldLevel + " --> Level " + playerPokemon.getLevel() + "]");
                    }

                    break;
                }

                // Gym Leader's turn
                System.out.println("+----------------------------------------------------------------------+");
                String gymMove = gymPokemon.getMoves().keySet().iterator().next(); // Gym Pokemon's first move
                attack(gymPokemon, playerPokemon, gymMove);

                if (playerPokemon.getcurrentHitPoints() <= 0) {
                    System.out.println(playerPokemon.getName() + " fainted!");
                    playerWon = false;
                    break;
                }

                round++;
            }

            if (!playerWon) {
                System.out.println("You were defeated by Gym Leader " + gym.getleaderName() + ".");
                System.out.println("+----------------------------------------------------------------------+");
                return; // Exit the method if the player lost
            }
        }

        if (playerWon) {
            System.out.println("You won with Gym Leader " + gym.getleaderName() + " and earned the " + gym.getgymBadges() + "!");
            player.addBadge(gym.getgymBadges());
            /*
        System.out.println(playerPokemon.getName()+ " gained 40xp. ");
        System.out.println(playerPokemon.getName()+ " gained 40xp. ");
             */
            System.out.println("+----------------------------------------------------------------------+");
        }
    }

    private static void attack(Pokemon attacker, Pokemon defender, String move) {
        Map<String, Integer> moves = attacker.getMoves();
        int damage = moves.get(move);
        boolean isSuperEffective = attacker.getStrongAgainst().contains(defender.getType());
        boolean isNotEffective = attacker.getWeakAgainst().contains(defender.getType());

        if (isSuperEffective) {
            damage *= 1.5;
            System.out.println(attacker.getName() + " used " + move + "!");
            System.out.println("It's super effective!");
        } else if (isNotEffective) {
            damage *= 0.5;
            System.out.println(attacker.getName() + " used " + move + "!");
            System.out.println("It's not very effective.");
        } else {
            System.out.println(attacker.getName() + " used " + move + "!");
        }

        defender.setcurrentHitPoints(defender.getcurrentHitPoints() - damage);
        System.out.println(defender.getName() + "'s HP drops " + (isSuperEffective ? "significantly" : "slightly") + ". [" + defender.getName() + " HP: " + defender.getcurrentHitPoints() + "/" + defender.getHitPoints() + "]");
    }

    private static boolean wildrun() {
        System.out.println("You chose to run away from the battle!");
        System.out.println("The battle has ended.");
        System.out.println("+----------------------------------------------------------------------+");
        return true; // S true to indicate the player has run   
    }
}

