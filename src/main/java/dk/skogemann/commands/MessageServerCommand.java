package dk.skogemann.commands;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import dk.skogemann.Command;

import java.util.stream.Collectors;

/**
 * @author Thomas Skogemann
 */
public class MessageServerCommand implements ServerCommand {

    public static final java.lang.String TAG = "MESSAGE";
    private String sender;
    private String message;

    public MessageServerCommand(String sender, String message) {
        this.sender = sender;
        this.message = message;
    }

    @Override
    public String serialize() {
        StringBuilder res = new StringBuilder(TAG+"#");
                res.append(sender+'#').append(message);
                return res.toString();
    }
}
