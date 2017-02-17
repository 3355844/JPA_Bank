import models.Account;
import models.Human;
import service.BankService;
import service.PersistenceManager;

/**
 * Created by 33558 on 12.02.2017.
 */
public class Main {
    public static void main(String[] args) {
        BankService service = new BankService();
        Human human = service.createHuman("Human 18/02/17");

        human =  service.refill("UAH", 1000, human.getAccounts().get(0));

        System.out.println(human.toString());
        System.out.println("Finish");
        PersistenceManager.shutDown();
    }
}
