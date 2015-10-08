/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.Map_User;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Martin Six
 */
public class UserManagerUI {
    public static void main(String[] args) {
        UserManager users=new UserManager();
        Scanner sc=new Scanner(System.in);
        String name;
        char opt;
        List<User> l;
        do{
            System.out.println("a=add, g=get, e=setEMail, p=setPW, l=login, L=getUserList, S=getSortedList, M=getSortedByMailList, A=getSortedByAgeList, q=quit\n");
            switch(opt=sc.next().charAt(0)){
                case 'a':
                    String email;
                    String pw;
                    String firstname;
                    String surname;
                    String birthday;
                    System.out.print("Username: ");
                    name=sc.next();
                    System.out.print("E-Mail: ");
                    email=sc.next();
                    System.out.print("Password: ");
                    pw=sc.next();
                    System.out.print("Firstname: ");
                    firstname=sc.next();
                    System.out.print("Surname: ");
                    surname=sc.next();
                    System.out.print("birthday(YYYY-MM-DD): ");
                    birthday=sc.next();
                    users.add(new User(name, email, pw, firstname, surname, Date.valueOf(birthday)));
                    break;
                case 'g':
                    System.out.print("Username: ");
                    System.out.println(users.get(sc.next()));
                    break;
                case 'e':
                    System.out.print("Username: ");
                    name=sc.next();
                    System.out.print("New E-Mail: ");
                    users.setEMail(name, sc.next());
                    break;
                case 'p':
                    System.out.print("Username: ");
                    name=sc.next();
                    System.out.print("New Password: ");
                    users.setPW(name, sc.next());
                    break;
                case 'l':
                    System.out.print("Username: ");
                    name=sc.next();
                    System.out.print("Password: ");
                    users.login(name, sc.next());
                    break;
                case 'L':
                    l=users.getUserList();
                    for(User u:l)
                        System.out.println(u);
                    break;
                case 'S':
                    l=users.getSortedList();
                    for(User u:l)
                        System.out.println(u);
                    break;
                case 'M':
                    l=users.getSortedByMailList();
                    for(User u:l)
                        System.out.println(u);
                    break;
                case 'A':
                    l=users.getSortedByAgeList();
                    for(User u:l)
                        System.out.println(u);
                    break;
            }            
        }while(opt!='q');
    }
}
