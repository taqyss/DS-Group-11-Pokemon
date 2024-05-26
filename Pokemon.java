
package project.pikachu; // 3 days commit
//kena buat current xp and hp 
//kena buat untuk xp and hp bila level naik

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

public class Pokemon {
    
    
    private String name;
    private String type;
    private int level = 5;
    private int HitPoints;
    private int currentHitPoints = 10;
    private int XP;
    private int currentXP = 10;//tambahan current XP
    private List<String> StrongAgainst;
    private List<String> WeakAgainst;
    private Map<String, Integer> Moves1;  // Move name to damage mapping
     private Map<String, Integer> Moves2;

    public Pokemon(String name, String type, int level, int baseHP , int XP ,ArrayList<String> StrongAgainst , ArrayList<String> WeakAgainst , Map <String , Integer> Moves1,Map <String , Integer> Moves2, int currentXP , int currentHitPoints) { // + Xp Strong weak current XP
        this.name = name;
        this.type = type;
        this.level = level;
        this.HitPoints = baseHP;
        this.currentHitPoints =currentHitPoints;
        this.XP =XP;
        this.currentXP=currentXP;
        this.StrongAgainst = new ArrayList<>();
        this.WeakAgainst = new ArrayList<>();
        this.Moves1 = new HashMap<>();
        this.Moves2 = new HashMap<>();
    }

    public Pokemon(String name) {
        this.name = name;
    }

    public void learnMove(String moveName, int damage) {
        Moves1.put(moveName, damage);
        Moves2.put(moveName, damage);
    }

    public String getName() {
        return name;
    }
    
    public void setName( String name) {
        this.name = name ;
    }

    public String getType() {
        return type;
    }
    
    public void setType ( String type){
        this.type=type;
    }

    public int getLevel() {
        return level;
    }
    
    public void setLevel(int level) {
        this.level=level;
    }
    
    public int getXP (){
        return XP;
    }
    
     public void setXP ( int XP ){
        this.XP = XP;
    }
    
    public int getcurrentXP (){
        return currentXP;
    }
    
    public void setcurrentXP ( int currentXP ){
        this.currentXP = currentXP;
    }
       
    public List<String> getStrongAgainst (){
        return new ArrayList<>(StrongAgainst);
    }
    
    public void setStrongAgainst(String StrongAgainst){
        this.StrongAgainst = new ArrayList<>();    
    }
    
    public List<String>  getWeakAgainst (){
        return new ArrayList<>(WeakAgainst);
    }
    
     public void setWeakAgainst (String WeakAgainst ){
        this.WeakAgainst  = new ArrayList<>();
    }
    
    public int getHitPoints() {
        return HitPoints;
    }

    public void setHitPoints(int hp) {
        this.HitPoints = hp;
    }
    
     public int getcurrentHitPoints() {
        return currentHitPoints;
    }

    public void setcurrentHitPoints(int currenthitPoints) {
        this.currentHitPoints = currenthitPoints;
    }

    public Map<String, Integer> getMoves() {
    Map<String, Integer> allMoves = new HashMap<>();
    allMoves.putAll(Moves1);
    allMoves.putAll(Moves2);
    return allMoves;
}
    
     public void setMoves1(Map<String, Integer> Moves1) {
        this.Moves1 = new HashMap<>();
    }
    
     public void setMoves2(Map<String, Integer> Moves2) {
        this.Moves2 = new HashMap<>();
    }
    

    public void levelUp() {
        this.level++;
        // Optionally increase hit points and move damage upon leveling up
        this.currentHitPoints += 5; // Increment HP by 5 upon leveling up
        this.currentXP += 10;
        Moves1.replaceAll((move, damage) -> damage + 2); // Increment damage by 2 for all moves
        Moves2.replaceAll((move, damage) -> damage + 2);
    }
    
    public void performAttack(Pokemon opponent, String move) {
    int damage = 0;
    boolean isSuperEffective = false;
    boolean isNotEffective = false;

    if (this.Moves1.containsKey(move)) {
        damage = this.Moves1.get(move);
    } else if (this.Moves2.containsKey(move)) {
        damage = this.Moves2.get(move);
    } else {
        System.out.println("Move not found!");
        return;
    }

    isSuperEffective = this.getStrongAgainst().contains(opponent.getType());
    isNotEffective = this.getWeakAgainst().contains(opponent.getType());

    if (isSuperEffective) {
        damage *= 1.5;
        System.out.println(this.name + " used " + move + "!");
        System.out.println("It's super effective!");
    } else if (isNotEffective) {
        damage *= 0.5;
        System.out.println(this.name + " used " + move + "!");
        System.out.println("It's not very effective.");
    } else {
        System.out.println(this.name + " used " + move + "!");
    }

    opponent.setHitPoints(opponent.getHitPoints() - damage);
    System.out.println(opponent.getName() + "'s HP drops " + (isSuperEffective ? "significantly" : "slightly") + ". [" + opponent.getName() + " HP: " + opponent.getcurrentHitPoints() + "/" + opponent.getHitPoints() + "]");
}

    
    
    //part checking pokemon and type strong weak hp xp so on (still kena tambah pokemon lain  
   public void checkPokemon (Pokemon pokemonName){
    if (pokemonName.equals("Pikachu")){
        setName("Pikachu");
        setType("Electric");
        StrongAgainst.add("Water");
        StrongAgainst.add("Flying");
        WeakAgainst.add("Ground");
    } else if (pokemonName.equals("Bulbasaur")){
        setName("Bulbasaur");
        setType("Grass/Poison");
        StrongAgainst.add("Water");
        StrongAgainst.add("Rock");
        StrongAgainst.add("Ground");
        WeakAgainst.add("Bug");
        WeakAgainst.add("Fire");
        WeakAgainst.add("Ice");
        WeakAgainst.add("Poison");
        WeakAgainst.add("Flying");
    } else if (pokemonName.equals("Squirtle")){
        setName("Squirtle");
        setType("Water");
    
        StrongAgainst.add("Fire");
        StrongAgainst.add("Rock");
        StrongAgainst.add("Ground");
        WeakAgainst.add("Electric");
        WeakAgainst.add("Grass");
    } else {
        System.out.println("Pokemon not in the list.");
    }
}
   
        
        
        
    }
   

