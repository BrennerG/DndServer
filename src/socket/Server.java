package socket;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.net.Socket;
import java.net.ServerSocket;

public class Server implements Runnable {

    public static final int NTHREADS = 5;
    protected int port;
    protected static ServerSocket serverSocket = null;
    private Socket clientSocket = null;
    private static ExecutorService executor = Executors.newFixedThreadPool(NTHREADS);
    protected Thread runningThread = null;
    private boolean running = true;

    public Server(int port){
        if( port != 0 ){
            this.port = port;
        } else {
            this.port = 1500;
        }
    }

    public void run(){
        System.out.println("RUN_Server.run()");

        synchronized (this){
            this.runningThread = Thread.currentThread();
        }

        openServerSocket();

        while( running ){
            try {
                clientSocket = this.serverSocket.accept();

             } catch( IOException e ){
                 System.out.println("IOException_Server.run()");
             }
             this.executor.execute(new Session(clientSocket));
         }
        this.executor.shutdown();
    }

    public void init(){
                new Thread(this).start();
        }

    public void openServerSocket(){
        try{
            this.serverSocket = new ServerSocket(this.port);

        } catch( IOException e ){
            System.err.println("IOException_Server.openserverSocket()");
            throw new RuntimeException("Cant open port_Server.openServerSocket");
        }
    }
}