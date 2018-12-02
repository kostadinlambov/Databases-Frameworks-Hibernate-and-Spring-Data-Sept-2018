package application;

import application.models.Account;
import application.models.User;
import application.services.AccountService;
import application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@SpringBootApplication
@Component
public class ConsoleRunner implements CommandLineRunner {
    private final UserService userService;
    private final AccountService accountService;

    @Autowired
    public ConsoleRunner(UserService userService, AccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }


    @Override
    public void run(String... args) throws Exception {
        User user  = new User();
        user.setAge(20);
        user.setUsername("Stamat2");

        Account account = new Account();
        account.setBalance(new BigDecimal(200));
        account.setUser(user);

        Set<Account> accounts = user.getAccounts();
//        accounts.add(account);
        user.setAccounts(accounts);
//         user.setAccounts(Collections.singletonList(account));

          this.userService.registerUser(user);

//        User user = userService.findById(3L);
//        System.out.println(user.getUsername());
//
//        // this.accountService.transferMoney(new BigDecimal(-100), 1L);
//        this.accountService.transferMoney(new BigDecimal(2000), 1L);
//
//        this.accountService.withdrawMoney(new BigDecimal(600), 1L);


    }
}
