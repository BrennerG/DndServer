package cmd;


public class Say extends Command {

    String msg = null;

    public Say( String msg) {
        this.msg = msg;
        this.name = "say";
    }

    public void execute() {
        //command-action
    }

    public String stringOut() {
       return ("[" + commander + "]\t " + msg);
    }
}
