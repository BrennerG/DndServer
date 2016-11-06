package controller;
import model.Player;
import java.util.ArrayList;
import java.util.List;


public class PlayerManagement {

    private List<Player> playerList;
    private int playerCount = 0;
    private int maxPlayerCount = 10;

    public PlayerManagement(){
        playerList = new ArrayList<>();
    }

    public synchronized Player createPlayer( String name ){
        Player player = new Player( name, playerCount );
        this.addPlayer( player );
        return player;
    }

    public synchronized void addPlayer( Player player ){
        this.playerList.add( player.getId(), player );
        playerCount++;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public synchronized void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }
}
