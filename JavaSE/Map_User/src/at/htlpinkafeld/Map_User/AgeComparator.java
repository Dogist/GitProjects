/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.Map_User;

import java.util.Comparator;

/**
 *
 * @author Martin Six
 */
public class AgeComparator implements Comparator<User>{

    @Override
    public int compare(User o1, User o2) {
        int x;
        if((x=o1.getBirthday().compareTo(o2.getBirthday()))!=0)
            return x;
        return o1.compareTo(o2);
    }
}
