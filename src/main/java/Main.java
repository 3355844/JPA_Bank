import models.Account;
import service.DaoService;
import service.PersistenceManager;

/**
 * Created by 33558 on 12.02.2017.
 */
public class Main {
    public static void main(String[] args) {
        DaoService daoService = new DaoService();
        Account account = new Account();
        account.setState(10000);
        account.setCurrency("UAH");
        daoService.getAccountDao().add(account);

        new PersistenceManager().shutDown();
        System.out.println("Finish");

    }
}
