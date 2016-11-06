package cmd;
import java.io.Serializable;


public class Command implements Serializable {

    protected String name = "default";
    protected String commander = "unknown";

    public void execute(){
        System.err.println("ParentClassExceptoid_Command.execute()");
    }

    public String stringOut(){
        return "ParentClassExceptoid_Command.stringOut()";
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getCommander() {
        return commander;
    }
    public void setCommander(String commander) {
        this.commander = commander;
    }

}
