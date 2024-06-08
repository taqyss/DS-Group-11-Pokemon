
package ds_project;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.*;

public class AccountManager {
    private Map<String, Account> accounts; //stores username --> password
    private Player currentPlayer;
    private final SaveManager saveManager;
    Scanner sc = new Scanner(System.in);
    
    public AccountManager(SaveManager saveManager){
        this.accounts = saveManager.getAccounts();
        if(this.accounts==null){
            this.accounts = new HashMap<>();
        }
        this.saveManager = saveManager;
    }
    
    public void createAccount(){
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();
        
        if(accounts.containsKey(username)){
            System.out.println("Account already exists");
        }
        else{
        Player player = new Player(username, "Pallet Town", new ArrayList<>(), new ArrayList<>());
        Account account = new Account(username, password, player);
        saveManager.addAccount(account);
        currentPlayer = player;
        }
    }
    
    public boolean login(){
        while (true) {
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();
            
            boolean found = false;
        for (Account account : accounts.values()) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)) {
                System.out.println("Login Successful");
                currentPlayer = saveManager.loadGame(username);
                found = true;
                break;
            }
        }

        if (found) {
            return true;
        } else {
            System.out.println("Invalid username or password");
            System.out.println("Would you like to create a new account? (yes/no)");
            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("yes")) {
                createAccount();
                return true;
            } else if (choice.equalsIgnoreCase("no")) {
                System.out.println("Login again");
            } else {
                System.out.println("Invalid choice. Please enter yes/no");
            }
        }
    }
    }
    
    public Player getCurrentPlayer() {
        return currentPlayer;
    }
}
