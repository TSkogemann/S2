package dk.skogemann;

import com.google.common.collect.Lists;
import dk.skogemann.commands.LogoutClientCommand;
import dk.skogemann.commands.SendClientCommand;
import dk.skogemann.commands.ServerCommand;
import dk.skogemann.commands.UserClientCommand;

import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.UUID;

/**
 * @author Thomas Skogemann
 */
public class MainClient {

    public static void main(String[] args) throws IOException {
        Client client = new Client();
        client.connect("localhost",9000);
        client.send(new UserClientCommand(UUID.randomUUID().toString()));
        client.start(new ClientCallback() {
            @Override
            public void onData(ServerCommand command) {
                System.out.println(command.serialize());
            }
        });

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            String s = reader.readLine();
            if (s == null || s.equals("")){
                continue;
            }
            if (s.equalsIgnoreCase("logout")) {
                client.send(new LogoutClientCommand());
                client.stop();
                break;
            } else {
                client.send(new SendClientCommand(s, Lists.newArrayList("*")));
            }
        }

    }
}
