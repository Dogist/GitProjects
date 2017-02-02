/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.spring_microservices.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 *
 * @author Martin Six
 */
@SpringBootApplication
@EnableEurekaServer
public class RegistrationServer {

    /**
     * Run the Application using Spring Boot and an embedded servlet engine.
     *
     * @param args
     */
    public static void main(String[] args) {

        System.setProperty("spring.config.name", "registration-server");

        SpringApplication.run(RegistrationServer.class, args);
    }
}
