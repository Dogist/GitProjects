/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.springworkshop.rest.controller;

import at.htlpinkafeld.springworkshop.pojo.Account;
import at.htlpinkafeld.springworkshop.rest.Services.IAccountService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Martin Six
 */
@RestController
public class AccountController {

    @Autowired
    private JdbcTemplate jt;

    @Autowired
    private IAccountService accountService;

    @Autowired
    ObjectMapper mapper;

    @RequestMapping(value = "/account", method = RequestMethod.PUT)
    public Account createAccount(@RequestParam(value = "number", defaultValue = "123") String number,
            @RequestParam(value = "owner", defaultValue = "Martin") String owner,
            @RequestParam(value = "balance", defaultValue = "1000") BigDecimal balance) throws JsonProcessingException {
        return accountService.createAccount(number, owner, balance);

    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.POST)
    public void updateAccount(@PathVariable Long id,
            @RequestParam(value = "number") String number,
            @RequestParam(value = "owner") String owner,
            @RequestParam(value = "balance") BigDecimal balance) {
        accountService.updateAccount(id, number, owner, balance);
    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.DELETE)
    public void updateAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

    @RequestMapping(value = "/account/{id}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable Long id) throws JsonProcessingException {
        return accountService.getAccount(id);
    }

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public List<Account> getAllAccounts() throws JsonProcessingException {
        return accountService.getAccounts();
    }

}
