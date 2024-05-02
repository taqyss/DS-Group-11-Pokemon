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

public class GymBattle {
    private static Scanner scanner = new Scanner(System.in);

    public static void battle(Player player, Gym gym) {
        if (player.getTeam().isEmpty()) {
            System.out.println("You have no Pokémon to fight with! Catch or receive one first.");
            return;
        }
        if (gym.getLeaderPokemon().isEmpty()) {
            System.out.println("There seems to be an error: Gym Leader " + gym.getLeaderName() + " has no Pokémon.");
            return;
        }

        Pokemon playerPokemon = player.getTeam().get(0); // Assuming the player has Pokémon
        Pokemon gymPokemon = gym.getLeaderPokemon().get(0); // Assuming the gym leader has Pokémon
        System.out.println("You are challenging Gym Leader " + gym.getLeaderName() + "!");

        // Simple turn-based battle logic
        while (playerPokemon.getHitPoints() > 0 && gymPokemon.getHitPoints() > 0) {
            System.out.println("Your Pokémon: " + playerPokemon.getName() + " HP: " + playerPokemon.getHitPoints());
            System.out.println(gym.getLeaderName() + "'s Pokémon: " + gymPokemon.getName() + " HP: " + gymPokemon.getHitPoints());

            // Player's turn
            System.out.println("Choose an action: 1. Attack 2. Run");
            String choice = scanner.nextLine();
            if ("2".equals(choice)) {
                System.out.println("You ran away from the battle!");
                return;
            } else {
                // Simple attack logic
                attack(playerPokemon, gymPokemon);
            }

            // Gym Leader's turn
            if (gymPokemon.getHitPoints() > 0) {
                attack(gymPokemon, playerPokemon);
            }
        }

        if (playerPokemon.getHitPoints() > 0) {
            System.out.println("You won the battle!");
            player.addBadge(gym.getLeaderName() + " Badge");
        } else {
            System.out.println("You lost the battle!");
        }
    }

    private static void attack(Pokemon attacker, Pokemon defender) {
        int damage = attacker.getMoves().values().iterator().next(); // Simple logic, taking the first move's damage
        System.out.println(attacker.getName() + " attacks " + defender.getName() + " for " + damage + " damage!");
        defender.setHitPoints(defender.getHitPoints() - damage);
    }
}

