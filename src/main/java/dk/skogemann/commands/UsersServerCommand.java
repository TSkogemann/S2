package dk.skogemann.commands;

import com.sun.corba.se.spi.activation.Server;
import dk.skogemann.Command;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Thomas Skogemann
 */
public class UsersServerCommand implements ServerCommand {

    public static final String TAG = "USERS";
    private Collection<String> users;

    public UsersServerCommand(Collection<String> users) {
        this.users = users;
    }

    @Override
    public String serialize() {
        StringBuilder res = new StringBuilder(TAG+"#");
                res.append(users.stream().collect(Collectors.joining(",")));
                /*
                for (Iterator<String> iterator = receivers.iterator(); iterator.hasNext(); ) {
                    String receiver = iterator.next();
                    res.append(receiver);
                    if (iterator.hasNext()) {
                        res.append(',');
                    }
                }
                */
                return res.toString();
    }
}
