package com.appdirect.repository;

import com.appdirect.entity.UserAccount;
import org.springframework.data.repository.CrudRepository;

public interface AppdirectUserRepository extends CrudRepository<UserAccount, String> {


	public UserAccount findByUuid(String uuid);
}
