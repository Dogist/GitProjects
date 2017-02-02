/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.spring_microservices.account;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Martin Six
 */
@RestController
@RequestMapping(value = "accounts")
public class AccountsController {

    private Logger logger = Logger.getLogger(AccountsController.class.getName());

    @Autowired
    AccountService accountService;

    @RequestMapping(method = RequestMethod.PUT)
    public Account createAccount(@RequestParam(value = "number", defaultValue = "123") String number,
            @RequestParam(value = "owner", defaultValue = "Martin") String owner,
            @RequestParam(value = "balance", defaultValue = "1000") BigDecimal balance) throws JsonProcessingException {
        return accountService.createAccount(number, owner, balance);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public void updateAccount(@PathVariable Long id,
            @RequestParam(value = "number") String number,
            @RequestParam(value = "owner") String owner,
            @RequestParam(value = "balance") BigDecimal balance) {
        accountService.updateAccount(id, number, owner, balance);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void updateAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Account getAccount(@PathVariable Long id) throws JsonProcessingException {
        return accountService.getAccount(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Collection<Account> getAllAccounts() throws JsonProcessingException {
        return accountService.getAccounts();
    }

}
