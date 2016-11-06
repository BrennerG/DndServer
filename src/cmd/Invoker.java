package cmd;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;


public class Invoker {

    private ArrayList<Command> cmdList;

    public Invoker() {
        cmdList = new ArrayList<Command>();
        cmdList.add( 0, new Say(null) );
    }

    public Command interpret(String msg) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        System.out.println("RUN_Invoker.interpret( " + msg + " )");

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
            System.out.println( "RETURN_Invoker.interpret( Say ) = " + returnCommand.stringOut() );
        }

        return returnCommand;
    }
}
