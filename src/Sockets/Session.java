package Sockets;
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Session extends Thread {

    Socket clientSocket = null;
    boolean running = true;


    public Session(Socket socket) {
        clientSocket = socket;
    }

    public void run() {
        try {
            if( clientSocket == null ){
                System.out.println("clientSocket=null_Session.run()");
            }
            OutputStream sOut = clientSocket.getOutputStream();
            InputStream sIn = clientSocket.getInputStream();
            /*
            ObjectOutputStream oos = new ObjectOutputStream(sOut);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(sIn));
            */
            DataOutputStream dos = new DataOutputStream(sOut);
            dos.flush();
            DataInputStream dis = new DataInputStream(sIn);
            System.out.println("IOStreams initiiert");

            dos.writeUTF("Welcome to the DnDServer");

            //Server magic
            while (running) {
                System.out.println("starting Server magic");
            }

            //close
            dis.close();
            sIn.close();
            sOut.close();
            clientSocket.close();

        } catch (IOException e) {
            System.err.println("IOException: I/O-Streams nicht initiiertbar");
        }
    }
}
