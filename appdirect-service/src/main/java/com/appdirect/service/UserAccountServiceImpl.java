package com.appdirect.service;

import com.appdirect.entity.UserAccount;
import com.appdirect.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public List<UserAccount> getAllAccount() {
        return (List<UserAccount>)userAccountRepository.findAll();
    }

}
