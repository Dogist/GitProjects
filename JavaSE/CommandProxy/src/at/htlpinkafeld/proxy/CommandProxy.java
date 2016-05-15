/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.proxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Martin Six
 */
public class CommandProxy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        CommandExecutor cEx = CommandService.getCommandExecutor("loik");
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);

        System.out.println("user eingeben um diesen zu verändern, sonst Commandos ausführen");

        String user = "loik";
        for (;;) {
            try {
                String inp = br.readLine();
                switch (inp) {
                    case "user":
                        System.out.println("geben sie einen neuen user ein");
                        user = br.readLine();
                        cEx = CommandService.getCommandExecutor(user);
                        System.out.println("User: " + user + "wurde ausgewählt");
                        break;
                    default:
                        if (!inp.isEmpty()) {
                            List<String> params = new ArrayList<>(Arrays.asList(inp.split(" ")));
                            String cmd = params.remove(0);

                            cEx.runCommand(cmd, params.stream().toArray(String[]::new));
                        }
                }
            } catch (IOException ex) {
                Logger.getLogger(CommandProxy.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
