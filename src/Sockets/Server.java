package Sockets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.Socket;
import java.net.ServerSocket;

public class Server extends Thread {

        public static final int NTHREADS = 5;
        private static final int SERVERPORT = 1666;
        private ServerSocket serverSocket = null;
        private Socket clientSocket = null;
        private ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);

        public static void main( String[] args ){

                System.out.println("CIAO");
                System.exit(1);
        }

        public void run(){}
        public void init(){}
}