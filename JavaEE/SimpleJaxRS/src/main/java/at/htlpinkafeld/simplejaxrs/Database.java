/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.simplejaxrs;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public class Database {

    private static final List<String> strList = new LinkedList<>();

    public Database() {
    }

    public static String getAll() {
        String allS = "[";
        for (String s : strList) {
            allS += s + ", ";
        }
        if (allS.endsWith(", ")) {
            allS = allS.substring(0, allS.length() - 2);
        }
        allS += "]";
        return allS;
    }

    public static void add(String e) {
        strList.add(e);
    }

    public static void delete(String s) {
        strList.remove(s);
    }

    public static String get(String s) {
        try {
            return strList.get(strList.indexOf(s));
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

}
