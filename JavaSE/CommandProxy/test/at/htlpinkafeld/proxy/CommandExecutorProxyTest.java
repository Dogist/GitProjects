/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.proxy;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Martin Six
 */
public class CommandExecutorProxyTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    /**
     * Test of runCommand method, of class CommandExecutorProxy. with user in
     * guest group and 0 permissions
     */
    @Test(expected = NonAuthorizedException.class)
    public void testRunCommandGuest() {
        String cmd = "PrintParams";
        String[] args = {"1", "2", "drei"};
        String user = "loik";
        CommandExecutor cEx = CommandService.getCommandExecutor(user);

        cEx.runCommand(cmd, args);
        cmd = "CheckInt";
        cEx.runCommand(cmd, args);
    }

    /**
     * Test of runCommand method, of class CommandExecutorProxy. with user in
     * admin group and CheckInt permission
     */
    @Test
    public void testRunCommandAdmin() {
        String cmd = "CheckInt";
        String[] args = {"1", "2", "3"};
        String user = "six";
        CommandExecutor cEx = CommandService.getCommandExecutor(user);

        cEx.runCommand(cmd, args);
    }

    /**
     * Test of runCommand method, of class CommandExecutorProxy. with user in
     * admin and developer group which have PrintParams and CheckInt permission
     */
    @Test
    public void testRunCommandDeveloper() {
        String cmd = "PrintParams";
        String[] args = {"1", "2", "3"};
        String user = "gneis";
        CommandExecutor cEx = CommandService.getCommandExecutor(user);

        cEx.runCommand(cmd, args);
        assertEquals("1;2;3;", outContent.toString());
        cmd = "CheckInt";
        cEx.runCommand(cmd, args);
    }
}
