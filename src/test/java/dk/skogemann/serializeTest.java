package dk.skogemann;

import com.google.common.collect.Lists;
import dk.skogemann.commands.*;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Thomas Skogemann
 */
public class serializeTest {

    @Test
    public void testSendSerialize(){
         SendClientCommand test = new SendClientCommand("Bla bla test", Lists.newArrayList("Lars","Thomas", "Sofus"));
        Assert.assertEquals("SEND#Lars,Thomas,Sofus#Bla bla test",test.serialize());
    }

     @Test
    public void testUserSerialize(){
           UserClientCommand test = new UserClientCommand("Thomas");
         Assert.assertEquals("USER#Thomas", test.serialize());
     }

    @Test
    public void testLogoutSerialize(){
        LogoutClientCommand test = new LogoutClientCommand();
        Assert.assertEquals("LOGOUT#", test.serialize());
    }

    @Test
    public void testUsersSerialize(){
        UsersServerCommand test = new UsersServerCommand(Lists.newArrayList("Lars","Thomas", "Sofus"));
        Assert.assertEquals("USERS#Lars,Thomas,Sofus",test.serialize());
    }

    @Test
        public void testMessageSerialize(){
            MessageServerCommand test = new MessageServerCommand("Thomas", "Hello World");
            Assert.assertEquals("MESSAGE#Thomas#Hello World",test.serialize());
        }
}
