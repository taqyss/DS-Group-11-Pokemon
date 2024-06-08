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
    private static final String SAVE_FILE = "testing.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Map<String, Account> accounts;
    
    
    public SaveManager(){
        accounts = loadAllGame();
        if (accounts == null) {
            accounts = new HashMap<>();
        } 
    }
    
    public void saveAllGame() {
        try (FileWriter writer = new FileWriter(SAVE_FILE)) {
            gson.toJson(accounts, writer);
            System.out.println("Data saved successfully");
        } catch (IOException e) {
            System.out.println("Saving error --> " + e.getMessage());
        }
    }

    public Map<String, Account> loadAllGame() {
        try (Reader reader = new FileReader(SAVE_FILE)) {
            Type type = new TypeToken<Map<String, Account>>() {}.getType();
            return gson.fromJson(reader, type);
        } catch (IOException e) {
            System.out.println("Loading error --> " + e.getMessage());
            return new HashMap<>();
        }
    }
    
    public void saveGame(String username, Player player){
        Account account = accounts.get(username);
        if (account != null) {
            account.setPlayer(player);
            saveAllGame();
        } else {
            System.out.println("Failed to save game: account not found for username " + username);
        }
    }
    
    public Player loadGame(String username){
        Account account = accounts.get(username);
        if (account != null) {
            return account.getPlayer();
        } else {
            System.out.println("Failed to load game: account not found for username " + username);
            return null;
        }
    }
    
    public Map<String, Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        accounts.put(account.getUsername(), account);
    }
}

