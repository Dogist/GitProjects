/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.spring_microservices.account;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

/**
 *
 * @author Martin Six
 */
@Service
public class AccountService {

    private final Map<Long, Account> accounts;
    private final AtomicLong counter;

    public AccountService() {
        accounts = new HashMap<>();
        counter = new AtomicLong();
    }

    Account createAccount(String number, String owner, BigDecimal balance) {
        Long id = counter.incrementAndGet();
        Account a = new Account(id, number, owner, balance);
        accounts.put(id, a);
        return a;
    }

    void deleteAccount(Long id) {
        accounts.remove(id);
    }

    void updateAccount(Long id, String number, String owner, BigDecimal balance) {
        Account a = new Account(id, number, owner, balance);
        accounts.replace(id, a);
    }

    Account getAccount(Long id) {
        return accounts.get(id);
    }

    Collection<Account> getAccounts() {
        return accounts.values();
    }

}
