/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_project;

/**
 *
 * @author Taqy
 */
import java.util.HashMap;
import java.util.Map;

public class Pokemon {
    private String name;
    private String type;
    private int level;
    private int hitPoints;
    private Map<String, Integer> moves;  // Move name to damage mapping

    public Pokemon(String name, String type, int level, int baseHP) {
        this.name = name;
        this.type = type;
        this.level = level;
        this.hitPoints = baseHP;
        this.moves = new HashMap<>();
        initializeMoves();
    }
    
    private void initializeMoves() {
        // Example: Initialize with two moves
        moves.put("Tackle", 20); // Basic attack
        moves.put("Quick Attack", 30); // Faster basic attack
    }

    public void learnMove(String moveName, int damage) {
        moves.put(moveName, damage);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getLevel() {
        return level;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hp) {
        this.hitPoints = hp;
    }

    public Map<String, Integer> getMoves() {
        return new HashMap<>(moves);
    }

    public void levelUp() {
        this.level++;
        // Optionally increase hit points and move damage upon leveling up
        this.hitPoints += 5; // Increment HP by 5 upon leveling up
        moves.replaceAll((move, damage) -> damage + 2); // Increment damage by 2 for all moves
    }
    
    public void performAttack(Pokemon opponent, String move) {
        if (this.moves.containsKey(move)) {
            int damage = this.moves.get(move);
            opponent.setHitPoints(opponent.getHitPoints() - damage);
            System.out.println(this.name + " used " + move + " causing " + damage + " damage!");
        } else {
            System.out.println("Move not found!");
        }
    }
}

