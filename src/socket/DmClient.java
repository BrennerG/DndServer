package socket;
import controller.*;

public class DmClient {

    public static void main( String[] args ){

        Server server = new Server(0);
        server.init();
    }
}
