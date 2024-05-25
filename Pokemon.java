package project.pikachu; //3 weeks commit

import java.util.Scanner;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class GymBattle {

    private static Scanner scanner = new Scanner(System.in);

    public static void battle(Player player, Gym gym) {
    if (player.getTeam().isEmpty()) {
        System.out.println("You have no Pokémon to fight with! Catch or receive one first.");
        System.out.println("+----------------------------------------------------------------------+");
        return;
    }
    if (player.getLocation().equals("Pallet Town") || player.getLocation().equals("Lavender Town")) {
        System.out.println("There is no Gym Leader in this town");
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
        while (playerPokemon.getcurrentHitPoints() > 0 && gymPokemon.getcurrentHitPoints() > 0) {
            System.out.println("Round " + round + ":");
            System.out.println(playerPokemon.getName() + "'s Moves:");
            Map<String, Integer> moves = playerPokemon.getMoves();
            int index = 1;
            for (String move : moves.keySet()) {
                System.out.println(index + ". " + move);
                index++;
            }

            System.out.print("Which move will " + playerPokemon.getName() + " use?\nYour choice: ");
            int selectedMoveIndex = scanner.nextInt();
            String selectedMove = playerPokemon.getMoves().keySet().toArray(new String[0])[selectedMoveIndex - 1];
            System.out.println("+----------------------------------------------------------------------+");

            attack(playerPokemon, gymPokemon, selectedMove);
            if (gymPokemon.getcurrentHitPoints() <= 0) {
                System.out.println(gymPokemon.getName() + " fainted!");
                playerPokemon.setcurrentXP(40); // Example XP gain
                System.out.println(playerPokemon.getName() + " gained 40 XP.");
                System.out.println(playerPokemon.getName() + " [XP: " + playerPokemon.getcurrentXP() + "/" + playerPokemon.getXP() + "]");
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
            break; // Exit loop if player lost
        }
    }

    if (playerWon) {
        System.out.println("You defeated Gym Leader " + gym.getleaderName() + " and earned the " + gym.getgymBadges() + "!");
        player.addBadge(gym.getgymBadges());
        System.out.println("+----------------------------------------------------------------------+");
    } else {
        System.out.println("You were defeated by Gym Leader " + gym.getleaderName() + ".");
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
}


/*
        int damage = attacker.getMoves().values().iterator().next(); // Simple logic, taking the first move's damage
        System.out.println(attacker.getName() + " attacks " + defender.getName() + " for " + damage + " damage!");
        defender.setHitPoints(defender.getHitPoints() - damage);
 */

