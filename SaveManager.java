/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ds_project;

import com.google.gson.Gson;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.lang.*;

public class SaveManager { //changes
    private static final String Save_File = "players.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Map<String, Account> accounts;
    
    public SaveManager(){
        accounts = loadAllGame();
        if (accounts == null) {
            accounts = new HashMap<>();
        }
    }
    
    public void saveAllGame() {
        try(FileWriter write = new FileWriter(Save_File)){
            gson.toJson(accounts, write);
            System.out.println("Player's Game saved successfully");
        }
        catch(IOException e){
            System.out.println("Saving error --> "+e.getMessage());
        }
    }

    public Map<String, Account> loadAllGame() {
        try(Reader read = new FileReader(Save_File)){
            Type type = new TypeToken<Map<String, Account>>() {}.getType();
            return gson.fromJson(read, type);
        }
        catch(IOException e){
            System.out.println("Loading error --> "+e.getMessage());
            return new HashMap<>();
        }
    }
    
    public void saveGame(String username, Player player){
        Account account = accounts.get(username);
        if (account != null) {
            account.setPlayer(player);
            saveAllGame();
        }
    }
    
    public Player loadGame(String username){
        Account account = accounts.get(username);
    if (account != null) {
        return account.getPlayer();
    }
    return null;
    }
    
    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.put(account.getUsername(), account);
        saveAllGame();
    }
    
}

