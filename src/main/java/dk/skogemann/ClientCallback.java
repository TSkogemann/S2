package dk.skogemann;

import dk.skogemann.commands.ServerCommand;

/**
 * @author Thomas Skogemann
 */
public interface ClientCallback {

   public void onData(ServerCommand command);
}
