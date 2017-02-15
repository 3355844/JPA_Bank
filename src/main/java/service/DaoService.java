package service;

import dao.AccountDaoImpl;
import dao.ExchangerDaoImpl;
import dao.HumanDaoImpl;
import dao.TransactionDaoImpl;

/**
 * Created by 33558 on 15.02.2017.
 */
public class DaoService {
    private HumanDaoImpl humanDao = new HumanDaoImpl();
    private AccountDaoImpl accountDao = new AccountDaoImpl();
    private ExchangerDaoImpl exchangerDao = new ExchangerDaoImpl();
    private TransactionDaoImpl transactionDao = new TransactionDaoImpl();

    public DaoService() {
    }

    public HumanDaoImpl getHumanDao() {
        return humanDao;
    }

    public AccountDaoImpl getAccountDao() {
        return accountDao;
    }

    public ExchangerDaoImpl getExchangerDao() {
        return exchangerDao;
    }

    public TransactionDaoImpl getTransactionDao() {
        return transactionDao;
    }
}
