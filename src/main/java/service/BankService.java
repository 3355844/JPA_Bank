package service;

import models.Account;
import models.Human;
import models.Transaction;

import java.util.List;

/**
 * Created by 33558 on 15.02.2017.
 */

public class BankService {
    private String UAH = "UAH";
    private String USD = "USD";
    private String EUR = "EUR";
    private DaoService daoService = new DaoService();

    public Human refill(String currency, double cash, Account account) {
        Human tempHuman = null;
        if (currency.equals(account.getCurrency())) {
            double tempState = account.getState() + cash;
            account.setState(tempState);
            daoService.getAccountDao().update(account);
            tempHuman = daoService.getHumanDao().getById(account.getHuman().getIdHuman());
            Transaction transaction = new Transaction();
            transaction.setState(cash);
            transaction.setIdAccountTo(account.getId());
            transaction.setIdAccountFrom("cash refill");
            transaction.setNameTransaction("Fill cash: " + cash + " " + currency +" to account :" + account.getCurrency());
            transaction.setIdAccountTo(account.getId());
            transaction = daoService.getTransactionDao().add(transaction);
            tempHuman.getTransactions().add(transaction);
            System.out.println(transaction.toString());
        }
        return daoService.getHumanDao().update(tempHuman);
    }

    public Human createHuman(String name) {
        Human human = new Human();
        human.setNameHuman(name);
        human = daoService.getHumanDao().add(human);
        Account accountUAH = daoService.getAccountDao().add(new Account(UAH));
        Account accountUSD = daoService.getAccountDao().add(new Account(USD));
        Account accountEUR = daoService.getAccountDao().add(new Account(EUR));
        accountUAH.setHuman(human);
        accountUSD.setHuman(human);
        accountEUR.setHuman(human);
        accountUAH = daoService.getAccountDao().update(accountUAH);
        accountUSD = daoService.getAccountDao().update(accountUSD);
        accountEUR = daoService.getAccountDao().update(accountEUR);
        List<Account> accounts = daoService.getAccountDao().getAllByHumanId(human.getIdHuman());
        human.setAccounts(accounts);
        Human result = daoService.getHumanDao().update(human);
        return result;
    }
}
