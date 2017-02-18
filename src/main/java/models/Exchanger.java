package models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 33558 on 12.02.2017.
 */
@Entity
@Table(name = "EXCHANGE")
@NamedQueries({
        @NamedQuery(name = "Exchanger.getAll", query = "SELECT e from Exchanger e"),
        @NamedQuery(name = "Exchanger.getByDate", query = "SELECT e from Exchanger e where e.date = :date")
})
public class Exchanger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_EXCHANGE")
    private int id;
    @Column(name = "DATE")
    private Date date;
    @Column
    private double pairUsdUah = 27.30;
    @Column
    private double pairUsdEur = 0.93;
    @Column
    private double pairEurUsd = 1.05;
    @Column
    private double pairEurUah = 29;
    @Column
    private double pairUahUsd = 0.0361;
    @Column
    private double pairUahEur = 0.0339;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TRANSACTION")
    private Transaction transaction;

    @Override
    public String toString() {
        return "Exchanger{" +
                "id=" + id +
                ", date=" + date +
                ", pairUsdUah=" + pairUsdUah +
                ", pairUsdEur=" + pairUsdEur +
                ", pairEurUsd=" + pairEurUsd +
                ", pairEurUah=" + pairEurUah +
                ", pairUahUsd=" + pairUahUsd +
                ", pairUahEur=" + pairUahEur +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public double getExchangeUsdUah(double usd) {
        return usd * pairUsdUah;
    }

    public void setPairUsdUah(double pairUsdUah) {
        this.pairUsdUah = pairUsdUah;
    }

    public double getExchangeUsdEur(double usd) {
        return usd * pairUsdEur;
    }

    public void setPairUsdEur(double pairUsdEur) {
        this.pairUsdEur = pairUsdEur;
    }

    public double getExchangeEurUsd(double eur) {
        return eur * pairEurUsd;
    }

    public void setPairEurUsd(double pairEurUsd) {
        this.pairEurUsd = pairEurUsd;
    }

    public double getExchangeEurUah(double eur) {
        return eur * pairEurUah;
    }

    public void setPairEurUah(double pairEurUah) {
        this.pairEurUah = pairEurUah;
    }

    public double getExchangeUahUsd(double uah) {
        return uah * pairUahUsd;
    }

    public void setPairUahUsd(double pairUahUsd) {
        this.pairUahUsd = pairUahUsd;
    }

    public double getExchangeUahEur(double uah) {
        return uah * pairUahEur;
    }

    public void setPairUahEur(double pairUahEur) {
        this.pairUahEur = pairUahEur;
    }

    public Exchanger(double pairUsdUah, double pairUsdEur, double pairEurUsd, double pairEurUah, double pairUahUsd, double pairUahEur) {
        this.pairUsdUah = pairUsdUah;
        this.pairUsdEur = pairUsdEur;
        this.pairEurUsd = pairEurUsd;
        this.pairEurUah = pairEurUah;
        this.pairUahUsd = pairUahUsd;
        this.pairUahEur = pairUahEur;
        this.date = new Date();
    }

    public Exchanger() {
        this.date = new Date();
    }
}
