package socket;
import cmd.Command;
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

    public Session(Socket socket) {
        clientSocket = socket;
    }

    public void run() {
        try{
           connect();

        } catch (IOException e){
            System.err.println("IOException_Session.run()-connect()");
        }

        Command readCommand = null;

        //Server magic
        while (running) {
            System.out.println("\nstarting Server magic");
            try {
                readCommand = (Command) ois.readObject();
                System.out.println("Command received: " + readCommand.getName());
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
            dos.writeUTF("Welcome to the DnDServer");
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
