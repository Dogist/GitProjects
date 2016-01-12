/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.rs_todomanagement.dao;

import at.htlpinkafeld.rs_todomanagement.model.Todo;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Martin Six
 */
public class TodoDAO {
    
    private static TodoDAO INSTANCE;
    Map<String,Todo> map;
    
    private TodoDAO() {
        map=new HashMap();
        map.put("1", new Todo("1","Hi","Hello"));
        map.put("2", new Todo("2","Walter", "Rauchwarter"));
        map.put("3", new Todo("3","NoPlan","dont want to think"));
    }
    
    public static TodoDAO getInstance() {
        if(INSTANCE==null){
            INSTANCE=new TodoDAO();
        }
        return INSTANCE;
    }
    
    public Map<String, Todo> getModel(){
        return this.map;
    }

}
