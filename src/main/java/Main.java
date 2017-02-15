import models.Account;
import models.Exchanger;
import models.Human;
import models.Transaction;
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

        Exchanger exchanger = new Exchanger();
        daoService.getExchangerDao().add(exchanger);

        Human human = new Human();
        human.setNameHuman("Andriy");
        daoService.getHumanDao().add(human);

        Transaction transaction = new Transaction();
        transaction.setNameTransaction(human.getNameHuman() + ": new Transaction");
        daoService.getTransactionDao().add(transaction);

        PersistenceManager.shutDown();
        System.out.println("Finish");
    }
}
