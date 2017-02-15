package src;

import java.net.Socket;

public class HTMLThread extends Thread {

    private Socket socket;

    public HTMLThread(Socket s) {
        socket = s;
    }

    @Override
    public void run() {

    }
}
