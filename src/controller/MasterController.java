package controller;

public class MasterController {

    PlayerManagement pm;

    public MasterController(){
        pm = new PlayerManagement();
    }

    public PlayerManagement getPm() {
        return pm;
    }
}
