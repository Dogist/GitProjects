/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.springworkshop.rest.controller;

import at.htlpinkafeld.springworkshop.rest.Services.IGreeting;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Martin Six
 */
@RestController
public class GreetingController {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private IGreeting greeting;

    @RequestMapping(value = "/greeting", method = RequestMethod.GET)
    public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {


        return greeting.greet(counter.getAndIncrement(), name);
    }
}
