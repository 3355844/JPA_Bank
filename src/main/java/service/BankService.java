package service;

import models.Account;
import models.Exchanger;
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
        if (currency.equals(account.getCurrency())) {
            return refillProcess(currency, cash, account);
        } else {
            return refillWithExchange(currency, cash, account);
        }
    }

    private Human refillWithExchange(String currency, double cash, Account account) {
        Exchanger exchanger = new Exchanger();
        exchanger = daoService.getExchangerDao().add(exchanger);
        System.out.println(exchanger.toString());
        if (currency.equals(UAH)) {
            return exchangeUAH(cash, account, exchanger);
        } else if (currency.equals(USD)) {
            return exchangeUSD(cash, account, exchanger);
        } else {
            return exchangeEUR(cash, account, exchanger);
        }
    }

    private Human exchangeEUR(double cash, Account account, Exchanger exchanger) {
        String currency;
        if (account.getCurrency().equals(USD)) {
            currency = USD;
            cash = exchanger.getExchangeEurUsd(cash);
            return refillProcess(currency, cash, account, exchanger);
        } else {
            currency = UAH;
            cash = exchanger.getExchangeEurUah(cash);
            return refillProcess(currency, cash, account, exchanger);
        }
    }

    private Human exchangeUSD(double cash, Account account, Exchanger exchanger) {
        String currency;
        if (account.getCurrency().equals(EUR)) {
            currency = EUR;
            cash = exchanger.getExchangeUsdEur(cash);
            return refillProcess(currency, cash, account, exchanger);
        } else {
            currency = UAH;
            cash = exchanger.getExchangeUsdUah(cash);
            return refillProcess(currency, cash, account, exchanger);
        }
    }

    private Human exchangeUAH(double cash, Account account, Exchanger exchanger) {
        String currency;
        if (account.getCurrency().equals(USD)) {
            currency = USD;
            cash = exchanger.getExchangeUahUsd(cash);
            return refillProcess(currency, cash, account, exchanger);
        } else {
            currency = EUR;
            cash = exchanger.getExchangeUahEur(cash);
            return refillProcess(currency, cash, account, exchanger);
        }
    }

    private Human refillProcess(String currency, double cash, Account account, Exchanger exchanger) {
        Human tempHuman;
        double tempState = account.getState() + cash;
        account.setState(tempState);
        daoService.getAccountDao().update(account);
        tempHuman = daoService.getHumanDao().getById(account.getHuman().getIdHuman());
        Transaction transaction = new Transaction();
        transaction.setState(cash);
        transaction.setIdAccountTo(account.getId());
        transaction.setIdAccountFrom("cash refill");
        transaction.setNameTransaction("Fill cash: " + cash + " " + currency + " to account : " + account.getCurrency());
        transaction.setIdAccountTo(account.getId());
        List<Human> humanList = transaction.getHumans();
        humanList.add(tempHuman);
        transaction.setHumans(humanList);
        transaction = daoService.getTransactionDao().add(transaction);
        exchanger.setTransaction(transaction);
        daoService.getExchangerDao().update(exchanger);
        List<Transaction> transactionList = tempHuman.getTransactions();
        transactionList.add(transaction);
        tempHuman.setTransactions(transactionList);
        System.out.println(transaction.toString());
        return daoService.getHumanDao().update(tempHuman);
    }

    private Human refillProcess(String currency, double cash, Account account) {
        Human tempHuman;
        double tempState = account.getState() + cash;
        account.setState(tempState);
        daoService.getAccountDao().update(account);
        tempHuman = daoService.getHumanDao().getById(account.getHuman().getIdHuman());
        Transaction transaction = new Transaction();
        transaction.setState(cash);
        transaction.setIdAccountTo(account.getId());
        transaction.setIdAccountFrom("cash refill");
        transaction.setNameTransaction("Fill cash: " + cash + " " + currency + " to account : " + account.getCurrency());
        transaction.setIdAccountTo(account.getId());
        List<Human> humanList = transaction.getHumans();
        humanList.add(tempHuman);
        transaction.setHumans(humanList);
        transaction = daoService.getTransactionDao().add(transaction);
        List<Transaction> transactionList = tempHuman.getTransactions();
        transactionList.add(transaction);
        tempHuman.setTransactions(transactionList);
        System.out.println(transaction.toString());
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