package src;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class HTTPResponse {

    private final String HTTPVER = "HTTP/1.1";

    public String code200() {
        String statusLine = HTTPVER + " 200 OK\r\n";
        testToFile(statusLine);
        return "";
    }

    private void testToFile(String msg) {
        File file = new File("test.txt");
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file);
            pw.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            pw.close();
        }
    }
}
