package Sockets;

public class DmClient {

    public static void main( String[] args ){

        Server server = new Server();
        Server.init();
        server.go();
    }
}
