package dk.skogemann;

import dk.skogemann.commands.ClientCommand;
import dk.skogemann.commands.ServerCommand;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author Thomas Skogemann
 */
public class Client {

    private Socket socket;
    private OutputStream outputStream;
    private BufferedReader bufferedReader;
    private volatile boolean shutdown = false;

    void connect(String host, int port) throws IOException {
        socket = new Socket(host, port);
        outputStream = socket.getOutputStream();
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void send(ClientCommand command) throws IOException {
        outputStream.write((command.serialize() + "\n").getBytes());
    }


    public void start(ClientCallback callback) {

        Runnable clientReader = new Runnable() {
            @Override
            public void run() {
                try {
                    String line;
                    ServerCommandFactory cmdFactory = new ServerCommandFactory();
                    do {
                        line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        ServerCommand command = cmdFactory.read(line);
                        ServerCommand serverCommand = command;
                        callback.onData(serverCommand);
                    }
                    while (shutdown != true);
                } catch (IOException e) {
                    if (!shutdown) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t = new Thread(clientReader);
        t.start();
    }


    public void stop() throws IOException {
        shutdown = true;
        socket.close();
    }
}
