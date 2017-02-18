import models.Account;
import models.Human;
import models.Transaction;
import service.BankService;
import service.PersistenceManager;

/**
 * Created by 33558 on 12.02.2017.
 */
public class Main {
    public static void main(String[] args) {
        BankService service = new BankService();
        Human human = service.createHuman("Human 18/02/17");
        human = service.refill("EUR", 1000, "cash", human.getAccounts().get(0));
        human = service.refill("USD", 2000, "cash", human.getAccounts().get(2));
        human = service.transfer(300, human.getAccounts().get(0), human.getAccounts().get(1));
        for (Transaction t : human.getTransactions()) {
            System.out.println(t.toString());
        }
        System.out.println("Finish");
        PersistenceManager.shutDown();
    }
}
