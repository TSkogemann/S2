package dk.skogemann;

import dk.skogemann.commands.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author Thomas Skogemann
 */
public class SocketReader implements Runnable  {

    private Socket client;
    private Server server;
    private BufferedReader reader;
    private final OutputStream outputStream;
    private String name;

    public SocketReader(Socket client, Server server) throws IOException {
        this.client = client;
        this.server = server;
        reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        outputStream = client.getOutputStream();
    }

    @Override
    public void run() {
        try {
            String line;
            ClientCommandFactory cmdFactory = new ClientCommandFactory();
            line = reader.readLine();
            ClientCommand command = cmdFactory.read(line);
            if (command instanceof UserClientCommand) {
                name = ((UserClientCommand) command).getName();
                server.command( command, this);
                System.out.println(line + " detected as a user");
                do {
                    line = reader.readLine();
                    command = cmdFactory.read(line);
                    if (command instanceof SendClientCommand){
                        System.out.println(line + " detected as a send");
                        server.command(command, this);
                    }
                    if (command instanceof LogoutClientCommand){
                        System.out.println(line + " detected as a logout");
                        break;
                    }

                    System.out.println(line);
                } while (line != null);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }     finally {
            if (name != null) {
                server.disconnected(name);
            }
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(ServerCommand command) throws IOException {
        outputStream.write((command.serialize() + "\n").getBytes());
    }

    public String getName() {
        return name;
    }
}
