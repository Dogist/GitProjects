package at.htlpinkafeld.spring_microservices;

import at.htlpinkafeld.spring_microservices.dao.DaoBuilder;
import at.htlpinkafeld.spring_microservices.registration.RegistrationServer;
import at.htlpinkafeld.spring_microservices.services.AccountServer;
import at.htlpinkafeld.spring_microservices.services.CartServer;
import at.htlpinkafeld.spring_microservices.services.OrderServer;
import at.htlpinkafeld.spring_microservices.services.ProductsServer;
import org.springframework.boot.SpringApplication;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Martin Six
 */
public class Main {

    public static void main(String[] args) {

//      Test for DAOs
//        DaoBuilder.createAccountDataSource();
//        DaoBuilder.createCartDataSource();
//        DaoBuilder.createOrderDataSource();
//        DaoBuilder.createProductDataSource();
        String serverName = "NO-VALUE";

        switch (args.length) {
            case 2:
                System.setProperty("server.port", args[1]);
            //fall-through
            case 1:
                serverName = args[0].toLowerCase();
                break;
            default:
                System.out.println("Unknown server type: " + serverName);
                usage();
        }
        System.setProperty("spring.config.name", serverName + "-server");

        switch (serverName) {
            case "registration":
                SpringApplication.run(RegistrationServer.class, args);
                break;
            case "accounts":
                SpringApplication.run(AccountServer.class, args);
                break;
            case "orders":
                SpringApplication.run(OrderServer.class, args);
                break;
            case "products":
                SpringApplication.run(ProductsServer.class, args);
                break;
            case "carts":
                SpringApplication.run(CartServer.class, args);
                break;
            default:
                System.out.println("Unknown server type: " + serverName);
                usage();
        }

    }

    protected static void usage() {
        System.out.println("Usage: java -jar . . . <server-name> [server-port]");
    }
}
