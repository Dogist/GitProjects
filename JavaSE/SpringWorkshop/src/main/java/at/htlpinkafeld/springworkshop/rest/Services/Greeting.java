/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.springworkshop.rest.Services;

import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Six
 */
@Service
public class Greeting implements IGreeting {

    @Override
    public String greet(Long id, String name) {
        return "Hello! You are number " + id + ": " + name;
    }

}
