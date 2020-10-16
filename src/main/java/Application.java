
import repo.AccountRepo;
import repo.AccountRepoImpl;
import service.AccountService;
import service.AccountServiceImpl;

public class Application {


    public static void main(String[] args) {
        AccountRepo accountRepo = new AccountRepoImpl();
        AccountService accountService = new AccountServiceImpl(accountRepo);

        // Testing
        accountService.findByName("kira");
        accountService.updateLastNameByFirstName("kira", "SH");
    }
}
