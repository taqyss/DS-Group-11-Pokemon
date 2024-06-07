
package ds_project;


import java.io.Serializable;

public class Account implements Serializable{
    private String username;
    private String password;
    private Player player;
    private String playerName;
    
    public Account(String username, String password, String playerName){
        this.username = username;
        this.password = password;
        this.playerName = playerName;
    }
    
    public String getPlayerName(){
        return playerName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
