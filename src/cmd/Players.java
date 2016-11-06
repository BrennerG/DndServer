package cmd;

import model.Player;

//shows all the players basic information
public class Players extends Command {

    public Players(){
        this.name = "players";
    }

    public void execute() {
        //
    }

    public String stringOut(){
        return (commander + "request player status" );
    }
}
