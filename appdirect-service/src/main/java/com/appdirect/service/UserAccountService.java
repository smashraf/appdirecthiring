package com.appdirect.service;

import com.appdirect.entity.UserAccount;
import com.appdirect.exception.ServiceException;

import java.util.List;

public interface UserAccountService {
    public UserAccount getAccountById(String accountId) throws ServiceException;

    public void deleteAccount(UserAccount account);

    public UserAccount saveAccount(UserAccount account);

}
