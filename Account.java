
package ds_project;


import java.io.Serializable;

public class Account implements Serializable{
    private String username;
    private String password;
    private Player player;
    private String playerName;
    
    //constructor for new account object
    public Account(String username, String password, Player player){
        this.username = username;
        this.password = password;
        this.playerName = playerName;
        this.player = player; //connect player object with this account
    }
    
    //getter and setter
    public String getPlayerName(){
        return playerName;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword(){
        return password;
    }

    public Player getPlayer(){
        return player;
    }

    public void setPlayer(Player player){
        this.player = player;
    }
}
