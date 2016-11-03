package Sockets;
import java.io.*;
import java.net.Socket;
//import java.util.Scanner;

public class Client {

    private String ip = new String();
    private int port;
    private Socket socket;
    OutputStream sOut;
    InputStream sIn;
    DataOutputStream dos;
    DataInputStream dis;
    private static boolean connected = true;

    public Client(String ipAdr, int port) {
        this.ip = ipAdr;
        this.port = port;
    }

    public static void main(String[] args) {
        String ip = "localhost";
        int port = 1666;

        Client client = new Client(ip, port);
        client.connect();

        System.exit(1);
    }

    public void connect() {
        try {
            System.out.println("Client.connect()");
            socket = new Socket(ip, port);
            sOut = socket.getOutputStream();
            sIn = socket.getInputStream();
            dos = new DataOutputStream(sOut);
            dis = new DataInputStream(sIn);
            System.out.println("IOStreams initialized_Client.connect()");
            System.out.println(dis.readUTF());

        } catch (IOException e) {
            System.err.println("IOException_Client.connect()");
        } catch (Exception e) {
            System.err.println("Exception_Client.connect()");
        }
    }

    public void close() {
        try {
            dis.close();
            dos.close();
            sIn.close();
            sOut.close();
            socket.close();

        } catch (IOException e) {
            System.err.println("IOException");
        }

    }
}
