package cmd;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


public class Invoker {

    private ArrayList<Command> cmdList;
    private String commanderName;

    public Invoker( String commanderName ) {
        cmdList = new ArrayList<Command>();
        cmdList.add( 0, new Say(null) );
        cmdList.add( 1, new Players() );
        this.commanderName = commanderName;
    }

    public Command interpret(String msg) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //System.out.println("RUN_Invoker.interpret( " + msg + " )");
        String[] words = msg.split("//s+");
        String searchName = null;
        Class<?> finalCommand;
        Constructor<?> constructor;
        Command returnCommand = null;
        boolean commandFound = false;

        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < cmdList.size(); j++) {
                searchName = cmdList.get(j).getName();
                if (words[i].equals(searchName)) {
                    commandFound = true;
                    break;
                }
            }
        }

        if( commandFound ) {
            finalCommand = Class.forName(searchName);
            constructor = finalCommand.getConstructor(String.class);
            returnCommand = (Command) constructor.newInstance(words[1]);
        } else {
            returnCommand = new Say( msg );
        }

        returnCommand.setCommander( commanderName );
        return returnCommand;
    }
}
