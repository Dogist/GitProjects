/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.proxy;

/**
 *
 * @author Martin Six
 */
public class CommandService {

    public static CommandExecutor getCommandExecutor(String user) {

        return new CommandExecutorProxy(user, new ReflectionCommandExecutor());
    }

}
