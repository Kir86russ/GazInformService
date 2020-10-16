package repo;

import domain.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

class AccountRepoTest {

    private AccountRepo accountRepo;

    @BeforeEach
    void setUp() {
        accountRepo = new AccountRepoImpl();
    }

    @Test
    void testFindByName() {
        Optional<Account> result1 = accountRepo.findByName("kira");
        assertEquals(result1, Optional.of(new Account(1, "kira", "SH")));

        Optional<Account> result2 = accountRepo.findByName("wizard");
        assertEquals(result2, Optional.of(new Account(2, "wizard", "lilov")));

        Optional<Account> result3 = accountRepo.findByName("kirill");
        assertEquals(result3, Optional.of(new Account(3, "kirill", "kirillov")));

        Optional<Account> result4 = accountRepo.findByName("");
        assertEquals(result4, Optional.empty());

        Optional<Account> result5 = accountRepo.findByName("?");
        assertEquals(result5, Optional.empty());

        Optional<Account> result6 = accountRepo.findByName("КТО-ТО");
        assertEquals(result6, Optional.empty());
    }

    @Test
    void testUpdateLastNameByFirstNameFalse() {
        boolean result1 = accountRepo.updateLastNameByFirstName("?", "newFam");
        assertFalse(result1);

        boolean result2 = accountRepo.updateLastNameByFirstName("", "newnewFam");
        assertFalse(result2);

        boolean result3 = accountRepo.updateLastNameByFirstName("КТО-ТО", "newnewnewFam");
        assertFalse(result3);
    }

    @Test
    void testUpdateLastNameByFirstNameTrue() {
        boolean result1 = accountRepo.updateLastNameByFirstName("andrey", "NEWWEEWWEWE");
        assertTrue(result1);

        boolean result2 = accountRepo.updateLastNameByFirstName("vlad", "newnewFam");
        assertTrue(result2);

        boolean result3 = accountRepo.updateLastNameByFirstName("emil", "newnewnewFam");
        assertTrue(result3);
    }

}
