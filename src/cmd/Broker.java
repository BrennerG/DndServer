package cmd;


public class Broker {

    public void receiveCommand(Command cmd ){

        cmd.execute();
    }
}
