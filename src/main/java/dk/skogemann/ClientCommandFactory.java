package dk.skogemann;

import com.google.common.collect.Lists;
import dk.skogemann.commands.ClientCommand;
import dk.skogemann.commands.LogoutClientCommand;
import dk.skogemann.commands.SendClientCommand;
import dk.skogemann.commands.UserClientCommand;

import java.util.List;

/**
 * @author Thomas Skogemann
 */
public class ClientCommandFactory {


     public ClientCommand read (String line){
         if (line.startsWith(UserClientCommand.TAG)){
             String[] split = line.split("#");
             return new UserClientCommand(split[1]);
         }
         if (line.startsWith(SendClientCommand.TAG)) {
             String[] split = line.split("#");
             List<String> receivers = Lists.newArrayList(split[1].split(","));
             return new SendClientCommand(split[2], receivers);
         }

         return new LogoutClientCommand();
     }



}
