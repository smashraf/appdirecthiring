package appdirect.service;

import com.appdirect.entity.UserAccount;
import com.appdirect.exception.ServiceException;
import com.appdirect.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UserAccountServiceImpl implements  UserAccountService {
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserAccount getAccountById(String accountId) throws ServiceException {
        UserAccount account=userAccountRepository.findOne(accountId);
        if(account==null){
            throw new ServiceException("Account already exists for account ID",new RuntimeException());
        }
        return account;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAccount(UserAccount account) {
        userAccountRepository.delete(account);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserAccount saveAccount(UserAccount account) {
        return userAccountRepository.save(account);
    }



}
