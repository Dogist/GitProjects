/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.spring_microservices.services;

import at.htlpinkafeld.spring_microservices.cart.CartsWebApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

/**
 *
 * @author Martin Six
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(CartsWebApplication.class)
public class CartServer {

    /**
     * Run the Application using Spring Boot and an embedded servlet engine.
     *
     * @param args
     */
    public static void main(String[] args) {

        System.setProperty("spring.config.name", "carts-server");

        SpringApplication.run(CartServer.class, args);
    }
}
