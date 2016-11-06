package socket;
import cmd.Command;
import controller.MasterController;
import model.Player;
import java.io.*;
import java.net.Socket;


public class Session extends Thread {

    Socket clientSocket = null;
    boolean running = true;
    OutputStream sOut;
    InputStream sIn;
    DataOutputStream dos;
    DataInputStream dis;
    ObjectInputStream ois;

    MasterController master;

    public Session(Socket clientSocket,MasterController master) {
        this.clientSocket = clientSocket;
        this.master = master;

        try{
           connect();

        } catch (IOException e){
            System.err.println("IOException_Session.Session()-connect()");
        }
    }

    public void run() {
        Command readCommand = null;

        while (running) {
            try {
                readCommand = (Command) ois.readObject();
                //System.out.println("Command received: " + readCommand.getName());
                System.out.println( readCommand.stringOut() );

            }catch (ClassNotFoundException e) {
                System.err.println("ClassNotFoundException_Session.run()-servermagic");
            }catch(IOException e ){
                System.err.println("IOException_Session.run()-servermagic");
            }
        }

        try {
            close();

        } catch( IOException e ){
            System.err.println("IOException_Session.run()-close");
        }

    }

    public void connect() throws IOException {
        if( clientSocket == null ){
            System.out.println("clientSocketNull_Session.run()");
            throw new IOException();
        }
        sOut = clientSocket.getOutputStream();
        sIn = clientSocket.getInputStream();
        dos = new DataOutputStream(sOut);
        dos.flush();
        dis = new DataInputStream(sIn);
        ois = new ObjectInputStream(sIn);
        System.out.println("IOStreams initiiert");

        //Das Stueck sollte in eine eigene character creation methode von pm - vllt
        Player helpPlayer;
        dos.writeUTF( "Please name your character:" );
        helpPlayer = master.getPm().createPlayer(dis.readUTF()); //read name
        dos.writeUTF( "welcome, " + helpPlayer.getName() + ", you are player#" + helpPlayer.getId() );
        dos.writeUTF( Integer.toString(helpPlayer.getId()) );
    }

    public void close() throws IOException {
        dis.close();
        ois.close();
        dos.close();
        sIn.close();
        sOut.close();
        clientSocket.close();
    }
}
