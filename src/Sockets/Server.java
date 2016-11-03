package Sockets;
import com.sun.org.apache.xpath.internal.SourceTree;
import jdk.nashorn.internal.ir.SetSplitState;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.Socket;
import java.net.ServerSocket;

public class Server implements Runnable {

        public static final int NTHREADS = 5;
        public static final int SERVERPORT = 1666;
        private boolean running = true;
        private ServerSocket serverSocket = null;
        private Socket clientSocket = null;
        private ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);

        public void go(){
                new Thread(this).start();
        }

        public void run(){
            System.out.println("Server.run()");

            init();

            while(running){
                try{
                    this.serverSocket.accept();

                } catch ( IOException e ){
                    System.err.println("IOException_Server.run()");
                }
                this.executor.execute( new Session(clientSocket) );
            }
            this.executor.shutdown();
            System.out.println("Server.executor.shutdown()");
        }

        public void init(){
            System.out.println("Server.init()");
                try {
                        this.serverSocket = new ServerSocket(SERVERPORT);
                        System.out.println("serverSocket initialized_Server.init()");

                } catch( IOException e ){
                        System.err.println("IOEXception_Server.init()");
                }
        }

        public boolean isRunning(){ return running; }
}