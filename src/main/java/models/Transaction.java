package models;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 33558 on 12.02.2017.
 */
@Entity
@Table(name = "TRANSACTION")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_TRANSACTION")
    private int id;
    @Column(name = "NAME_TRANSACTION")
    private String nameTransaction;
    @Column(name = "DATE_TRANSACTION")
    private Date date;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "transaction")
    private Exchanger exchanger;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameTransaction() {
        return nameTransaction;
    }

    public void setNameTransaction(String nameTransaction) {
        this.nameTransaction = nameTransaction;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Exchanger getExchanger() {
        return exchanger;
    }

    public void setExchanger(Exchanger exchanger) {
        this.exchanger = exchanger;
    }

    public Transaction() {
    this.date = new Date();
    }

}
