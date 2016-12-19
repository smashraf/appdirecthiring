package com.appdirect.repository;

import com.appdirect.entity.UserAccount;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by covacsis on 18/12/16.
 */
public interface UserAccountRepository extends CrudRepository<UserAccount,String> {
   public UserAccount findByAccountIdentifier(String accountId);
}
