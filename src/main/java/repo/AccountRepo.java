package repo;

import domain.Account;
import java.util.Optional;

public interface AccountRepo {

    Optional<Account> findByName(String name);

    boolean updateLastNameByFirstName(String firstName, String newLastName);

}
