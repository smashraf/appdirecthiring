package com.appdirect.repository;

import com.appdirect.entity.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccount,String> {
   public UserAccount findByAccountIdentifier(String accountId);
}
