package socket;
import cmd.Command;
import cmd.Invoker;
import model.Player;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client {

    private String ip = new String();
    private int port = 1500;
    private Socket socket;
    OutputStream sOut;
    InputStream sIn;
    ObjectOutputStream oos;
    DataOutputStream dos;
    DataInputStream dis;
    private static Scanner scanner = null;
    private static Invoker invoker = null;
    private static String playerName;
    private static int playerId;

    public Client(String ipAdr, int port) {
        this.ip = ipAdr;
        this.port = port;
        this.scanner  = new Scanner(System.in).useDelimiter("//n");
    }

    public Client( String ipAdr ) {
        this.ip = ipAdr;
        this.port = 1500;
        this.scanner  = new Scanner(System.in).useDelimiter("//n");
    }

    public static void main(String[] args) {
        String ip = "localhost";
        Client client = new Client( ip );
        client.connect();

        invoker = new Invoker( playerName );
        String msg = "default";
        Command sendCommand = null;

        while( !msg.equals("quit") ){
            msg = scanner.nextLine();
            try {
                sendCommand = invoker.interpret( msg );

            } catch( Exception e ){
                System.err.println("CommandUnknownException_Client.main()");
            }

            try {
                client.oos.writeObject( sendCommand );
                client.oos.flush();

            } catch( IOException e ){
                System.err.println("IOException_Client.main()-writingObject");
            }
        }

        client.close();
        System.exit(1);
    }

    public void connect() {
        try {
            socket = new Socket(ip, port);
            sOut = socket.getOutputStream();
            sIn = socket.getInputStream();
            oos = new ObjectOutputStream( sOut );
            dos = new DataOutputStream(sOut);
            dis = new DataInputStream( sIn );

            System.out.println( dis.readUTF() ); //name your character
            playerName = scanner.nextLine();
            dos.writeUTF( playerName );
            System.out.println( dis.readUTF() ); //welcome
            playerId =  Integer.parseInt((dis.readUTF()));

        } catch (IOException e) {
            System.err.println("IOException_Client.connect()");
            System.exit(0);
        } catch (Exception e) {
            System.err.println("Exception_Client.connect()");
            System.exit(0);
        }
    }

    public void close() {
        try {
            dis.close();
            oos.close();
            sIn.close();
            sOut.close();
            socket.close();

        } catch (IOException e) {
            System.err.println("IOException");
        }
    }
}
