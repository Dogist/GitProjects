/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.lamda;

import at.htlpinkafeld.lamda.presentation.MainFrame;
import javax.swing.JFrame;

/**
 *
 * @author masix
 */
public class Swing_FlexibleStudentList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JFrame frame = new MainFrame();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(900, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
