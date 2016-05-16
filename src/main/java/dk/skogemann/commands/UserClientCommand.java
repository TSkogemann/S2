package dk.skogemann.commands;

import dk.skogemann.Command;

/**
 * @author Thomas Skogemann
 */
public class UserClientCommand implements ClientCommand {

    private String name;
    public static final String TAG = "USER";

    public UserClientCommand(String name) {
        this.name = name;
    }

    @Override
    public String serialize() {
        return TAG+'#'+name;
    }

    public String getName() {
        return name;
    }
}
