/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.springworkshop.rest.Services;

import at.htlpinkafeld.springworkshop.pojo.Account;
import at.htlpinkafeld.springworkshop.service.ImbaService;
import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Six
 */
@Service
public class AccountService implements IAccountService {

    private final AtomicLong counter = new AtomicLong();

    @Autowired
    JdbcTemplate jdbcTemplate;

    SimpleJdbcInsert insertActor;

    public AccountService() {
//        insertActor = new SimpleJdbcInsert(jdbcTemplate).withTableName("accounts");

    }

    @Override
    public Account createAccount(String number, String owner, BigDecimal balance) {
        return ImbaService.createAccount(new Account(counter.incrementAndGet(), number, owner, balance));
    }

    @Override
    public void updateAccount(Long id, String number, String owner, BigDecimal balance) {
        ImbaService.updateAccount(new Account(id, number, owner, balance));
    }

    @Override
    public void deleteAccount(Long id) {
        ImbaService.deleteAccount(id);
    }

    @Override
    public Account getAccount(Long id) {
        return ImbaService.readAccount(id);
    }

    @Override
    public List<Account> getAccounts() {
        return ImbaService.readAccounts();
    }

}
