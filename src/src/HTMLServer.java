package src;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTMLServer {
    private static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(8888);
        System.out.println("HTTP server now running");

        while(true) {
            Socket clientSocket = serverSocket.accept();

            HTMLThread thread = new HTMLThread(clientSocket);
            thread.run();
        }
    }
}
