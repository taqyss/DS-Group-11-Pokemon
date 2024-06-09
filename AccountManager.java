
package ds_project;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.*;

public class AccountManager{
    private Map<String, Account> accounts; //stores username --> password
    private Player currentPlayer;
    private final SaveManager saveManager;
    Scanner sc = new Scanner(System.in);
    
    public AccountManager(SaveManager saveManager){
        //initialzing accounts with data loaded from SaveManager
        this.accounts = saveManager.loadAllGame();
        if(this.accounts==null){
            this.accounts = new HashMap<>(); //if null, make new Map
        }
        this.saveManager = saveManager;
    }
    
    //for creating account
    public void createAccount(){
        //enter username and password
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        
        //check if the account with that username exists or not
        if(accounts.containsKey(username)){
            System.out.println("Account already exists, plaease login");
            login();
        }
        else{
            //create new player with its new account
            Player player = new Player(username, "Pallet Town", new ArrayList<>(), new ArrayList<>());
            Account account = new Account(username, password, player);
            //adding new account to the SaveManager
            saveManager.addAccount(account);
            currentPlayer = player;
        }
    }
    
    //for account login
    public boolean login(){
        while(true){
            //enter username and password
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            
            //searching the username and password through the accounts map
            boolean found = false;
            for(Account account : accounts.values()) {
            if(account.getUsername().equals(username) && account.getPassword().equals(password)) {
                System.out.println("Login Successful");
                //load Player object based on the username from SaveManage
                currentPlayer = saveManager.loadGame(username);
                found = true;
                break;
            }
        }

        if(found){
            return true;
        } 
        else{
            //if the account is not exists or wrong username or password
            System.out.println("Invalid username or password");
            System.out.println("Would you like to create a new account? (yes/no)");
            String choice = sc.nextLine();
            //if choose yes, then prompt to make new account
            if(choice.equalsIgnoreCase("yes")){
                createAccount();
                return true;
            } 
            //if no, prompt to login again
            else if(choice.equalsIgnoreCase("no")){
                System.out.println("Login again");
            } 
            else{
                System.out.println("Invalid choice. Please enter yes/no");
            }
        }
    }
    }
    
    //getter to get current player
    public Player getCurrentPlayer(){
        return currentPlayer;
    }
}
