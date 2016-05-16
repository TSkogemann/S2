package dk.skogemann.commands;

import dk.skogemann.Command;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Thomas Skogemann
 */
public class SendClientCommand implements ClientCommand {

    private List<String> receivers;
    private String message;
    public static final String TAG = "SEND";

    public SendClientCommand(String msg, List<String> receivers) {
        this.receivers = receivers;
        this.message = msg;
    }

    @Override
    public String serialize() {
        StringBuilder res = new StringBuilder("SEND#");
        res.append(receivers.stream().collect(Collectors.joining(",")));
        /*
        for (Iterator<String> iterator = receivers.iterator(); iterator.hasNext(); ) {
            String receiver = iterator.next();
            res.append(receiver);
            if (iterator.hasNext()) {
                res.append(',');
            }
        }
        */
        res.append('#').append(message);
        return res.toString();
    }

    public List<String> getReceivers() {
        return receivers;
    }

    public String getMessage() {
        return message;
    }

    public boolean forAllUsers() {
        if (receivers.size()==1 && receivers.get(0).equals("*")){
            return true;
        }
        return false;
    }
}
