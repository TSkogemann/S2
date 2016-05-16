package dk.skogemann;

import com.google.common.collect.Lists;
import dk.skogemann.commands.*;

import java.util.List;

/**
 * @author Thomas Skogemann
 */
public class ServerCommandFactory {


     public ServerCommand read (String line){
         if (line.startsWith(UsersServerCommand.TAG)){
             String[] split = line.split("#");
             return new UsersServerCommand(Lists.newArrayList(split[1].split(",")));
         }
         if (line.startsWith(MessageServerCommand.TAG)) {
             String[] split = line.split("#");
             return new MessageServerCommand(split[1],split[2]);
         }
         throw new IllegalStateException("Unknown command " + line);
     }



}
