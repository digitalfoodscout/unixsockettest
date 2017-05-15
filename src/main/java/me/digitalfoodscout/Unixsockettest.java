package me.digitalfoodscout;

import com.etsy.net.JUDS;
import com.etsy.net.UnixDomainSocket;
import com.etsy.net.UnixDomainSocketServer;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Unixsockettest {
    public static void main(String[] args) {
        // Delete existing socket
        new File("test.socket").delete();

        try {
            // Create server
            UnixDomainSocketServer domainSocketServer = new UnixDomainSocketServer("test.socket", JUDS.SOCK_STREAM, 3);

            // Wait for clients
            while (true) {
                System.out.println("Waiting for client...");
                UnixDomainSocket socket = domainSocketServer.accept();
                System.out.println("Client connected!");

                // Write message to client
                // TODO: Handle each client in separate thread
                PrintStream printStream = new PrintStream(socket.getOutputStream());
                printStream.print("Hello from Java");
                printStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(2);
        }
    }
}
