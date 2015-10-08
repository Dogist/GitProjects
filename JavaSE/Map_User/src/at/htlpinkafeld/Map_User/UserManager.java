/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.Map_User;

import java.util.*;

/**
 *
 * @author Martin Six
 */
public class UserManager {
    Map<String, User> users=new HashMap();
    
    public void add(User user){
        if(this.testEmail(user.getEmail()))
            users.put(user.getName(), user);
    }
    
    public User get(String username) throws NoSuchUserException{
        if(!users.containsKey(username))
            throw new NoSuchUserException();
        return users.get(username);
    }
    
    public void setEMail(String username, String email) throws NoSuchUserException{
        if(!users.containsKey(username))
            throw new NoSuchUserException();
        users.get(username).setEmail(email);
    }
    
    public void setPW(String username, String pw) throws NoSuchUserException, InvalidPasswordException{
        if(!users.containsKey(username))
            throw new NoSuchUserException();
        User u=users.get(username);
        if(u.getPw()==pw.hashCode()||u.getLastPW()==pw.hashCode())
            throw new InvalidPasswordException();
        u.setPw(pw.hashCode());
    }
    
    public void login(String username, String pw)throws NoSuchUserException, WrongPasswordException{
        if(!users.containsKey(username))
            throw new NoSuchUserException();
        if(users.get(username).getPw()!=pw.hashCode())
            throw new WrongPasswordException();
    }
    
    public List<User> getUserList(){
        return new ArrayList<>(users.values());
    }
    
    public List<User> getSortedList(){
        List l=this.getUserList();
        l.sort(null);
        return l;
    }
    
    public List<User> getSortedByMailList(){
        List l=this.getUserList();
        l.sort(new MailComparator());
        return l;
    }
    
    public List<User> getSortedByAgeList(){
        List l=this.getUserList();
        l.sort(new AgeComparator());
        return l;
    }
    
    private boolean testEmail(String email){
        boolean erg=true;
        for(User u:this.users.values())
            if(u.getEmail().compareTo(email)==0)
                erg=false;
        return erg;
    }
}
