package src;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class HTTPResponse {

    // used in every response's status line to declare HTTP version
    private final String HTTPVER = "HTTP/1.1";
    private final String EOL = "\r\n";
    private String requestedPath;
    private File requestedFile;

    // constructor for requests that expect a file delivered back to them
    public HTTPResponse(String path) {
        requestedPath = path;
        requestedFile = new File(requestedPath);
    }

    // constructor for requests that don't expect a file in the received response
    public HTTPResponse() {

    }

    // constructs a valid code 200 HTTP response
    public String code200() {
        String statusLine = HTTPVER + " 200 OK" + EOL;

        // general header fields
        ZonedDateTime now = ZonedDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.RFC_1123_DATE_TIME;
        String date = "Date: " + now.format(dtf) + EOL;                                // https://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.18
        String connection = "Connection: close" + EOL;                      // https://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.10

        // response header fields
        String server = "Server: Java server" + EOL;                   // https://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.38
        String acceptRanges = "Accept-Ranges: none" + EOL;                  // https://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.5

        // entity header fields
        String contentType = "Content-Type: text/html; charset=UTF-8" + EOL;      // https://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.17
        String contentEncoding = "Content-Encoding: UTF-8" + EOL;                                   // https://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.11
        String contentLength = String.valueOf("Content-Length: " + requestedFile.length() + EOL);      // https://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.13

        // add file contents
        String fileContents = "";
        Scanner scan = null;
        try {
            scan = new Scanner(requestedFile);
            while (scan.hasNext()) {
                fileContents += scan.nextLine() + EOL;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileContents += EOL;
        testToFile(statusLine + date + connection + server + acceptRanges + contentType + contentEncoding + contentLength + EOL + fileContents);
        return statusLine + date + connection + server + acceptRanges + contentType + contentEncoding + contentLength + EOL + fileContents;
    }


    // returns true if the specified file exists on the specified path, returns false if it doesn't
    public Boolean fileExists() {
        return requestedFile.exists();
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
