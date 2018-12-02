package application.services;


import application.models.Account;
import application.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Primary
public class AccountServiceImpl implements AccountService{
    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    @Override
    public void withdrawMoney(BigDecimal money, Long id) {
        if(!this.accountRepository.exists(id)){
            throw  new IllegalArgumentException("There is no account with the provided id");
        }

        Account acc = accountRepository.findOne(id);
        BigDecimal newBalance = acc.getBalance().subtract(money);

        acc.setBalance(newBalance);
        this.accountRepository.save(acc);
    }

    @Override
    public void transferMoney(BigDecimal money, Long id) {
        Account acc = accountRepository.findOne(id);

        if(acc == null){
            throw  new IllegalArgumentException("There is no account with the provided id");
        }
        if(acc.getUser() == null){
            throw  new RuntimeException("No user present for this account");
        }

        if(money.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Money cannot be negative");
        }

        BigDecimal newBalance = acc.getBalance().add(money);
        acc.setBalance(newBalance);
        this.accountRepository.save(acc);
    }
}
