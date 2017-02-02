/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package at.htlpinkafeld.springworkshop.rest.Services;

import at.htlpinkafeld.springworkshop.pojo.Account;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Martin Six
 */
public interface IAccountService {

    public Account createAccount(String number, String owner, BigDecimal balance);

    public void updateAccount(Long id, String number, String owner, BigDecimal balance);

    public void deleteAccount(Long id);

    public Account getAccount(Long id);

    public List<Account> getAccounts();

}
