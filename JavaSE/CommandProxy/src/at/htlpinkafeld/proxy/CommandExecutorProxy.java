/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.proxy;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class CommandExecutorProxy implements CommandExecutor {

    private final static Map<String, String> userMap;
    private final static Map<String, String> userGroupMap;

    static {
        userMap = new HashMap<>();
        userMap.put("loik", "guest");
        userMap.put("six", "admin");
        userMap.put("gneis", "admin,developer");

        userGroupMap = new HashMap<>();
        userGroupMap.put("guest", "");
        userGroupMap.put("developer", "PrintParams,CheckInt");
        userGroupMap.put("admin", "CheckInt,Shutdown");
    }

    private final String user;
    private final CommandExecutor cEx;

    public CommandExecutorProxy(String user, CommandExecutor cEx) {
        this.user = user;
        this.cEx = cEx;
    }

    @Override
    public void runCommand(String cmd, String[] args) {
        boolean hasPerm = false;
        String[] uGroups = userMap.get(user).split(",");
        for (String uGroup : uGroups) {
            String[] permissions = userGroupMap.get(uGroup).split(",");
            for (String perm : permissions) {
                if (!perm.isEmpty() && cmd.endsWith(perm)) {
                    hasPerm = true;
                }
            }
        }
        if (hasPerm) {
            cEx.runCommand(cmd, args);
        } else {
            throw new NonAuthorizedException();

        }
    }

}
