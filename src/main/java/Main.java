import models.Account;
import models.Exchanger;
import models.Human;
import models.Transaction;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by 33558 on 12.02.2017.
 */
public class Main {
    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("Bank");
        EntityManager manager = factory.createEntityManager();
        Human human = manager.find(Human.class, 1);
//        System.out.println(human.getNameHuman());

//        Account accountUSD = manager.find(Account.class, 3);
//        accountUSD.setCurrency("EUR");
//        accountUSD.setHuman(human);
//        accountUSD.setState(200000);
//        manager.getTransaction().begin();
//        manager.merge(accountUSD);
//        manager.getTransaction().commit();
        Transaction transaction = manager.find(Transaction.class, 8);
        Exchanger exchanger = manager.find(Exchanger.class, 1);
        exchanger.setTransaction(transaction);
        transaction.setNameTransaction(human.getNameHuman());
//        transaction.setExchanger(exchanger);
        manager.getTransaction().begin();
        manager.merge(exchanger);

//        manager.merge(transaction);
        manager.getTransaction().commit();
//        for (Account account : human.getAccounts()) {
//            System.out.println(account.getCurrency());
//            System.out.println(account.getState());
//        }
        manager.close();
        factory.close();
    }
}
