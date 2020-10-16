package service;

import domain.Account;
import repo.AccountRepo;
import repo.AccountRepoImpl;

import java.util.Optional;

public class AccountServiceImpl implements AccountService {

    private AccountRepo accountRepo;

    public AccountServiceImpl(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public Optional<Account> findByName(String name) {
        return accountRepo.findByName(name);
    }

    @Override
    public boolean updateLastNameByFirstName(String firstName, String newLastName) {
        return accountRepo.updateLastNameByFirstName(firstName, newLastName);
    }
}
