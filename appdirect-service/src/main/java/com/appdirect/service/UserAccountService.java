package com.appdirect.service;

import com.appdirect.entity.UserAccount;

import java.util.List;

public interface UserAccountService {
    public UserAccount getAccountById(String accountId);

    public void deleteAccount(UserAccount account);

    public UserAccount saveAccount(UserAccount account);


    public List<UserAccount> getAllAccount();

}
