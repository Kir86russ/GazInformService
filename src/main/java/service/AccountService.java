package service;

import domain.Account;
import java.util.Optional;

public interface AccountService {

    Optional<Account> findByName(String name);

    boolean updateLastNameByFirstName(String firstName, String newLastName);

}
