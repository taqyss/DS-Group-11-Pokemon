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

public class SaveManager{ 
    private static final String FILE = "accounts.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Map<String, Account> accounts;
    
    //make sure account is loaded
    public SaveManager(){
        accounts = loadAllGame();
        if(accounts == null){
            accounts = new HashMap<>();
        } 
    }
    
    //for saving all account data
    public void saveAllGame(){
        try(FileWriter writer = new FileWriter(FILE)){
            //.toJson to serialize the data
            gson.toJson(accounts, writer);
            System.out.println("Successfully Saved");
        } 
        catch(IOException e){
            System.out.println("Saving error --> "+e.getMessage());
        }
    }

    //for load all account data
    public Map<String, Account> loadAllGame(){
        try(Reader reader = new FileReader(FILE)){
            Type type = new TypeToken<Map<String, Account>>() {}.getType();
            //.fromJson to deserialize the data
            return gson.fromJson(reader, type);
        } 
        catch(IOException e){
            System.out.println("Loading error --> "+e.getMessage());
            return new HashMap<>();
        }
    }
    
    //for saving game state for an account
    public void saveGame(String username, Player player){
        Account account = accounts.get(username);
        if(account != null){
            //update the player object with the account
            account.setPlayer(player);
            //save all data
            saveAllGame();
        } 
        else{
            System.out.println("Account for "+username+" is not found");
        }
    }
    
    //for load game state for an account
    public Player loadGame(String username){
        if(accounts == null){
            loadAllGame();//make sure if accounts exists in loadAllGame()
        }
        Account account = accounts.get(username);
        if(account != null){
            return account.getPlayer();
        } 
        else{
            System.out.println("Account for "+username+" is not found");
            return null;
        }
    }
    
    public Map<String, Account> getAccounts(){
        return accounts;
    }
    
    //adding account information and saving it in file 
    public void addAccount(Account account){
        accounts.put(account.getUsername(), account);
        saveAllGame();
    }
}
