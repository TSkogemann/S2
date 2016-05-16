package dk.skogemann;

import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Thomas Skogemann
 */
public class MainServer {


    public static void main(String[] args) throws IOException {

        Server server = new Server();
        System.out.println("Starting server");
        server.start(9000);
        System.out.println("Server running");
    }

}



