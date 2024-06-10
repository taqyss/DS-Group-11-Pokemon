/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package project.pikachu; //3 weeks commit

import java.util.ArrayList;
import java.util.List;


public class Gym {
    
    private String leaderName=" ";
    private List<Pokemon> leaderPokemon;
    private int leaderlevel;
    private List<String> leaderStrongAgainst;
    private List<String> leaderWeakAgainst;
    private String gymBadges;
    private int leaderHitPoints;
    private int leadercurrentHitPoints;
    private int leaderXP;
    private int leadercurrentXP;
    

    public Gym(String leaderName , List<Pokemon> leaderPokemon , int leaderlevel , List<String> leaderStrongAgainst , List<String>  leaderWeakAgainst , String gymBadges , int leaderHitPoints , int leaderXP , int leadercurrentHitPoints , int leadercurrentXP ) {
        this.leaderName = leaderName;
        this.leaderPokemon = new ArrayList<>();
        this.leaderlevel=leaderlevel;
        this.leaderStrongAgainst=new ArrayList<>();
        this.leaderWeakAgainst=new ArrayList<>();
        this.gymBadges=gymBadges;
        this.leaderXP=leaderXP;
        this.leadercurrentXP=leadercurrentXP;
        this.leaderHitPoints=leaderHitPoints;
        this.leadercurrentHitPoints=leadercurrentHitPoints;
    }

 public void reset() {
        this.leaderName = " ";
        this.leaderPokemon.clear();
        this.leaderlevel = 0;
        this.leaderStrongAgainst.clear();
        this.leaderWeakAgainst.clear();
        this.gymBadges = " ";
        this.leaderHitPoints = 0;
        this.leadercurrentHitPoints = 0;
        this.leaderXP = 0;
        this.leadercurrentXP = 0;
    }  
    
    

    public void addPokemon(Pokemon pokemon) {
        leaderPokemon.add(pokemon);
    }

    public String getleaderName() {
        return leaderName;
    }
    
    public void setleaderName(String leaderName) {
        this.leaderName=leaderName;
    }
    
     public String getgymBadges() {
        return gymBadges;
    }
    
    public void setgymBadges(String gymBadges) {
        this.gymBadges=gymBadges;
    }
    

    public List<Pokemon> getleaderPokemon() {
        return new ArrayList<>(leaderPokemon);
    }
    
     public void setleaderPokemon(Pokemon pokemon) {
     this.leaderPokemon.add(pokemon);
    }
    
    public int getleaderLevel(){
        return leaderlevel;
    }
    
      public List<String>  getleaderStrongAgainst (){
        return new ArrayList<>(leaderStrongAgainst);
    }
    
    public void setleaderStrongAgainst(String StrongAgainst){
        this.leaderStrongAgainst =new ArrayList<>();   
    }
    
    public List<String> getleaderWeakAgainst (){
        return new ArrayList<>(leaderWeakAgainst);
    }
    
     public void setleaderWeakAgainst (String WeakAgainst ){
        this.leaderWeakAgainst = new ArrayList<>();
     }    
     
        public int getleaderHitPoints(){
        return leaderHitPoints;
    }

     public void setleaderHitPoints(int hp) {
        this.leaderHitPoints = leaderHitPoints;
    }
    
     public int getleadercurrentHitPoints() {
        return leadercurrentHitPoints;
    }
     
     public int getleaderXP (){
        return leaderXP;
    }
    
     public void setleaderXP ( int leaderXP ){
        this.leaderXP = leaderXP;
    }
    
    public int getleadercurrentXP (){
        return leadercurrentXP;
    }
    
    public void setleadercurrentXP ( int leadercurrentXP ){  
        this.leadercurrentXP = leadercurrentXP;
    }
       

    public void setleadercurrentHitPoints(int leadercurrenthitPoints) {
        this.leadercurrentHitPoints = leadercurrenthitPoints;
    }
}

