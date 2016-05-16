package dk.skogemann.commands;

import dk.skogemann.Command;

import java.net.CookiePolicy;

/**
 * @author Thomas Skogemann
 */
public class LogoutClientCommand implements ClientCommand {
           public static final String TAG = "LOGOUT";

    @Override
    public String serialize() {
        return TAG+'#';
    }
}
