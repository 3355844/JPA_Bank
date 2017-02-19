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

    public double getBalance(Human human, String currency) {
        Exchanger exchanger = new Exchanger();
        double tempState = 0;
        for (Account account : human.getAccounts()) {
            if (account.getCurrency().equals(currency)) {
                tempState = tempState + account.getState();
            } else {
                if (currency.equals(EUR)) {
                    if (account.getCurrency().equals(UAH))
                        tempState = tempState + exchanger.getExchangeUahEur(account.getState());
                    if (account.getCurrency().equals(USD))
                        tempState = tempState + exchanger.getExchangeUsdEur(account.getState());
                }
                if (currency.equals(USD)) {
                    if (account.getCurrency().equals(UAH))
                        tempState = tempState + exchanger.getExchangeUahUsd(account.getState());
                    if (account.getCurrency().equals(EUR))
                        tempState = tempState + exchanger.getExchangeEurUsd(account.getState());
                }
                if (currency.equals(UAH)) {
                    if (account.getCurrency().equals(USD))
                        tempState = tempState + exchanger.getExchangeUsdUah(account.getState());
                    if (account.getCurrency().equals(EUR))
                        tempState = tempState + exchanger.getExchangeEurUah(account.getState());
                }
            }
        }
        return tempState;
    }

    public Human transfer(double money, Account accountFrom, Account accountTo) {
        if (accountFrom.getState() < money) {
            System.out.println("Transaction is failed: You have not how many money");
            return daoService.getHumanDao().getById(accountFrom.getHuman().getIdHuman());
        }
        accountFrom.setState(accountFrom.getState() - money);
        daoService.getAccountDao().update(accountFrom);
        return refill(accountFrom.getCurrency(), money, String.valueOf(accountFrom.getId()), accountTo);
    }

    public Human refill(String currency, double cash, String idFrom, Account account) {
        if (currency.equals(account.getCurrency())) {
            return refillProcess(currency, cash, idFrom, account);
        } else {
            return refillWithExchange(currency, cash, idFrom, account);
        }
    }

    private Human refillWithExchange(String currency, double cash, String idFrom, Account account) {
        Exchanger exchanger = new Exchanger();
        exchanger = daoService.getExchangerDao().add(exchanger);
        if (currency.equals(UAH)) {
            return exchangeUAH(cash, idFrom, account, exchanger);
        } else if (currency.equals(USD)) {
            return exchangeUSD(cash, idFrom, account, exchanger);
        } else {
            return exchangeEUR(cash, idFrom, account, exchanger);
        }
    }

    private Human exchangeEUR(double cash, String idFrom, Account account, Exchanger exchanger) {
        String currency;
        if (account.getCurrency().equals(USD)) {
            currency = USD;
            cash = exchanger.getExchangeEurUsd(cash);
            return refillProcess(currency, cash, idFrom, account, exchanger);
        } else {
            currency = UAH;
            cash = exchanger.getExchangeEurUah(cash);
            return refillProcess(currency, cash, idFrom, account, exchanger);
        }
    }

    private Human exchangeUSD(double cash, String idFrom, Account account, Exchanger exchanger) {
        String currency;
        if (account.getCurrency().equals(EUR)) {
            currency = EUR;
            cash = exchanger.getExchangeUsdEur(cash);
            return refillProcess(currency, cash, idFrom, account, exchanger);
        } else {
            currency = UAH;
            cash = exchanger.getExchangeUsdUah(cash);
            return refillProcess(currency, cash, idFrom, account, exchanger);
        }
    }

    private Human exchangeUAH(double cash, String idFrom, Account account, Exchanger exchanger) {
        String currency;
        if (account.getCurrency().equals(USD)) {
            currency = USD;
            cash = exchanger.getExchangeUahUsd(cash);
            return refillProcess(currency, cash, idFrom, account, exchanger);
        } else {
            currency = EUR;
            cash = exchanger.getExchangeUahEur(cash);
            return refillProcess(currency, cash, idFrom, account, exchanger);
        }
    }

    private Human refillProcess(String currency, double cash, String idFrom, Account account, Exchanger exchanger) {
        Human tempHuman;
        double tempState = account.getState() + cash;
        account.setState(tempState);
        daoService.getAccountDao().update(account);
        tempHuman = daoService.getHumanDao().getById(account.getHuman().getIdHuman());
        Transaction transaction = new Transaction();
        transaction.setState(cash);
        transaction.setIdAccountTo(account.getId());
        transaction.setIdAccountFrom(idFrom);
        transaction.setNameTransaction("Transaction: " + cash + " " + currency);
        transaction.setIdAccountTo(account.getId());
        List<Human> humanList = transaction.getHumans();
        humanList.add(tempHuman);
        transaction.setHumans(humanList);
        transaction = daoService.getTransactionDao().add(transaction);
        transaction.setExchanger(exchanger);
        transaction = daoService.getTransactionDao().update(transaction);
        exchanger.setTransaction(transaction);
        exchanger = daoService.getExchangerDao().update(exchanger);
        List<Transaction> transactionList = tempHuman.getTransactions();
        transactionList.add(transaction);
        tempHuman.setTransactions(transactionList);
        return daoService.getHumanDao().update(tempHuman);
    }

    private Human refillProcess(String currency, double cash, String idFrom, Account account) {
        Human tempHuman;
        double tempState = account.getState() + cash;
        account.setState(tempState);
        daoService.getAccountDao().update(account);
        tempHuman = daoService.getHumanDao().getById(account.getHuman().getIdHuman());
        Transaction transaction = new Transaction();
        transaction.setState(cash);
        transaction.setIdAccountTo(account.getId());
        transaction.setIdAccountFrom(idFrom);
        transaction.setNameTransaction("Transaction : " + cash + " " + currency);
        transaction.setIdAccountTo(account.getId());
        List<Human> humanList = transaction.getHumans();
        humanList.add(tempHuman);
        transaction.setHumans(humanList);
        transaction = daoService.getTransactionDao().add(transaction);
        List<Transaction> transactionList = tempHuman.getTransactions();
        transactionList.add(transaction);
        tempHuman.setTransactions(transactionList);
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