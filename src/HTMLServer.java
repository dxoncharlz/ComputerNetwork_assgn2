import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTMLServer {
    private static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(6789);
        System.out.println("TCP Echo server now running");

        while(true) {


        }
    }
}
