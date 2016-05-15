/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.proxy;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class ReflectionCommandExecutor implements CommandExecutor {

    @Override
    public void runCommand(String cmd, String[] args) {
        try {

            cmd = "at.htlpinkafeld.proxy." + cmd;

            Class<Command> cmdClass = (Class<Command>) Class.forName(cmd);
            Command commandObj = cmdClass.newInstance();
            commandObj.run(args);
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(ReflectionCommandExecutor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
