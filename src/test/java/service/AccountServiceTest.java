package service;

import domain.Account;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repo.AccountRepo;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class AccountServiceTest {

    @Mock
    private AccountRepo accountRepo;

    private AccountService accountService;

    AccountServiceTest() {
        MockitoAnnotations.initMocks(this);
        this.accountService = new AccountServiceImpl(accountRepo);
    }

    @Test
    void testFindByName() {
        given(accountRepo.findByName("kira")).willReturn(Optional.of(new Account(1, "kira", "SH")));
        Optional<Account> result1 = accountService.findByName("kira");
        assertEquals(result1, Optional.of(new Account(1, "kira", "SH")));

        given(accountRepo.findByName("wizard")).willReturn(Optional.of(new Account(2, "wizard", "lilov")));
        Optional<Account> result2 = accountService.findByName("wizard");
        assertEquals(result2, Optional.of(new Account(2, "wizard", "lilov")));

        given(accountRepo.findByName("kirill")).willReturn(Optional.of(new Account(3, "kirill", "kirillov")));
        Optional<Account> result3 = accountService.findByName("kirill");
        assertEquals(result3, Optional.of(new Account(3, "kirill", "kirillov")));

        given(accountRepo.findByName("kto-to")).willReturn(Optional.empty());
        Optional<Account> result4 = accountService.findByName("kto-to");
        assertEquals(result4, Optional.empty());

        given(accountRepo.findByName(".NET")).willReturn(Optional.empty());
        Optional<Account> result5 = accountService.findByName(".NET");
        assertEquals(result5, Optional.empty());

        given(accountRepo.findByName("")).willReturn(Optional.empty());
        Optional<Account> result6 = accountService.findByName("");
        assertEquals(result6, Optional.empty());
    }


    @Test
    void testUpdateLastNameByFirstNameTrue() {
        given(accountRepo.updateLastNameByFirstName("andrey", "newFam")).willReturn(true);
        boolean result1 = accountService.updateLastNameByFirstName("andrey", "newFam");

        given(accountRepo.updateLastNameByFirstName("vlad", "newnewFam")).willReturn(true);
        boolean result2 = accountService.updateLastNameByFirstName("vlad", "newnewFam");

        given(accountRepo.updateLastNameByFirstName("emil", "newnewnewFam")).willReturn(true);
        boolean result3 = accountService.updateLastNameByFirstName("emil", "newnewnewFam");

        assertTrue(result1);
        assertTrue(result2);
        assertTrue(result3);
    }


    @Test
    void testUpdateLastNameByFirstNameFalse() {
        given(accountRepo.updateLastNameByFirstName("?", "newFam")).willReturn(false);
        boolean result1 = accountService.updateLastNameByFirstName("?", "newFam");

        given(accountRepo.updateLastNameByFirstName("", "newnewFam")).willReturn(false);
        boolean result2 = accountService.updateLastNameByFirstName("", "newnewFam");

        given(accountRepo.updateLastNameByFirstName("КТО-ТО", "newnewnewFam")).willReturn(false);
        boolean result3 = accountService.updateLastNameByFirstName("КТО-ТО", "newnewnewFam");

        assertFalse(result1);
        assertFalse(result2);
        assertFalse(result3);
    }
}
