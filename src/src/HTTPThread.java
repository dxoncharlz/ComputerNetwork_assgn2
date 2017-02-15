package src;

import java.net.Socket;

public class HTTPThread extends Thread {

    private Socket socket;

    public HTTPThread(Socket s) {
        socket = s;
    }

    @Override
    public void run() {

    }
}
