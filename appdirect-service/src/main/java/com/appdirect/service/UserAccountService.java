package com.appdirect.service;

import com.appdirect.entity.UserAccount;

import java.util.List;

/**
 * Created by covacsis on 18/12/16.
 */
public interface UserAccountService {
    public UserAccount getAccountById(String accountId);

    public void deleteAccount(UserAccount account);

    public UserAccount saveAccount(UserAccount account);

    public void deleteAccount(String accountId);

    public List<UserAccount> getAllAccount();

}
