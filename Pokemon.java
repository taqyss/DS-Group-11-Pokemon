
package project.pikachu; // 3 days commit

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Pokemon {//change dikit
    
    
    private String name;
    private String type;
    private int level;
    private int HitPoints;
    private int currentHitPoints;
    private int XP;
    private int currentXP ;//tambahan current XP
    private List<String> StrongAgainst;
    private List<String> WeakAgainst;
    private Map<String, Integer> Moves1;  // Move name to damage mapping
     private Map<String, Integer> Moves2;

    public Pokemon(String name, String type, int level, int baseHP, int XP, ArrayList<String> StrongAgainst, ArrayList<String> WeakAgainst, Map <String , Integer> Moves1, Map <String , Integer> Moves2, int currentXP, int currentHitPoints) { // + Xp Strong weak current XP
        this.name = name;
        this.type = type;
        this.level = level;
        this.HitPoints = baseHP;
        this.currentHitPoints =currentHitPoints;
        this.XP =XP;
        this.currentXP=currentXP;
        this.StrongAgainst =StrongAgainst;
        this.WeakAgainst =WeakAgainst;
        this.Moves1 =Moves1;
        this.Moves2 =Moves2;
    }
    
    //constructor utk other pokemon yng kita catch
     public Pokemon(Pokemon other) {
        this.name = other.name;
        this.type = other.type;
        this.level = other.level;
        this.HitPoints = other.HitPoints;
        this.currentHitPoints = other.currentHitPoints;
        this.XP = other.XP;
        this.currentXP = other.currentXP;
        this.StrongAgainst = new ArrayList<>(other.StrongAgainst);
        this.WeakAgainst = new ArrayList<>(other.WeakAgainst);
        this.Moves1 = new HashMap<>(other.Moves1);
        this.Moves2 = new HashMap<>(other.Moves2);
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

    public int getCurrentHitPoints() {
        return currentHitPoints;
    }

    public int getCurrentXP() {
        return currentXP;
    }

    public Map<String, Integer> getMoves1() {
        return Moves1;
    }

    public Map<String, Integer> getMoves2() {
        return Moves2;
    }
     
    
    
    public void levelUp(int XP , int HitPoints) {
        this.level++;
        // increase hit points and move damage upon leveling up
        //HP increase 5 
        setHitPoints (HitPoints += 25);
        //XP 1-10  (stay 100XP)
        //XP 11-30 (200 XP)
        if (this.level==11)
            setXP (XP += 100);
        //XP up 31 (300XP
        else if (this.level == 31)
            setXP (XP += 100);
        
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
    
    
    public void PokemonCenter  (Player player){
        
      for (Pokemon pokemon : player.getTeam()) {
        pokemon.setcurrentHitPoints(pokemon.getHitPoints());      
    }
    }

        @Override
    public String toString() {
        return name;  // Simple and clear, just showing the name for now
    }
}
   


  
