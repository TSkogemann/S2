package dk.skogemann;

import dk.skogemann.commands.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Thomas Skogemann
 */
public class Server {

    ConcurrentHashMap<String, SocketReader> connections = new ConcurrentHashMap<>();

    public void start(int port) throws IOException {
        ServerSocket s = new ServerSocket(port);

        while (true) {
            Socket client = s.accept();
            Runnable reader = new SocketReader(client, this);
            Thread t = new Thread(reader);
            t.start();
        }
    }

    public void command(ClientCommand command, SocketReader reader) {
        if (command instanceof UserClientCommand) {
            UserClientCommand user = (UserClientCommand) command;
            connections.put(user.getName(), reader);
            clientsUpdated();

        }
        if (command instanceof SendClientCommand) {
            SendClientCommand send = (SendClientCommand) command;
                MessageServerCommand message = new MessageServerCommand(reader.getName(), send.getMessage());
            Collection<String> receivers = send.getReceivers();
            if (send.forAllUsers()) {
                receivers = connections.keySet();
            }
            for (String name : receivers) {
                try {
                    SocketReader socketReader = connections.get(name);
                    if (socketReader != null) {
                        socketReader.send(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void clientsUpdated() {
        UsersServerCommand users = new UsersServerCommand(connections.keySet());
        for (SocketReader socketReader : connections.values()) {
            try {
                socketReader.send(users);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void disconnected(String name) {
        System.out.println(name + " disconnected");
        connections.remove(name);
        clientsUpdated();
    }
}
