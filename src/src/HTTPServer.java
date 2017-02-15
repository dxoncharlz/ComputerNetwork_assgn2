package src;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {
    private static ServerSocket serverSocket;

    public static void main(String[] args) throws IOException {

        // create server socket to listen on port 8888 for connections
        serverSocket = new ServerSocket(8888);
        System.out.println("HTTP server now running");

        // main loop of the server. Terminates manually
        while(true) {
            // accept incoming connection
            Socket clientSocket = serverSocket.accept();

            // create a new thread that will handle the request, and pass the connection socket to it
            HTTPThread thread = new HTTPThread(clientSocket);
            thread.run();
        }
    }
}
