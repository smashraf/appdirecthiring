package com.appdirect.service;

import com.appdirect.entity.UserAccount;
import com.appdirect.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by covacsis on 19/12/16.
 */
@Component
public class UserAccountServiceImpl implements  UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Override
    public UserAccount getAccountById(String accountId) {
        return userAccountRepository.findOne(accountId);
    }

    @Override
    public void deleteAccount(UserAccount account) {
        userAccountRepository.delete(account);
    }

    @Override
    public UserAccount saveAccount(UserAccount account) {
        return userAccountRepository.save(account);
    }

    @Override
    public void deleteAccount(String accountId) {
        UserAccount account = getAccountById(accountId);
        userAccountRepository.delete(account);
    }

    @Override
    public List<UserAccount> getAllAccount() {
        return (List<UserAccount>)userAccountRepository.findAll();
    }

}
