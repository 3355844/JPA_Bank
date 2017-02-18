package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 33558 on 12.02.2017.
 */
@Entity
@Table(name = "TRANSACTION")
@NamedQueries({
        @NamedQuery(name = "Transaction.getAll", query = "SELECT t FROM Transaction t"),
        @NamedQuery(name = "Transaction.getByDate", query = "SELECT t from Transaction t where t.date = :date")}
)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_TRANSACTION")
    private int id;
    @Column(name = "NAME_TRANSACTION")
    private String nameTransaction;
    @Column(name = "DATE_TRANSACTION")
    private Date date;
    @Column(name = "FROM_ACCOUNT")
    private String idAccountFrom;
    @Column(name = "TO_ACCOUNT")
    private int idAccountTo;
    @Column(name = "STATE")
    private double state;
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "transaction")
    private Exchanger exchanger;
    @ManyToMany
    @JoinTable(name = "HUMAN_TRANSACTION",
            joinColumns = {@JoinColumn(name = "ID_TRANSACTION", referencedColumnName = "ID_TRANSACTION")},
            inverseJoinColumns = {@JoinColumn(name = "ID_HUMAN", referencedColumnName = "ID_HUMAN")}
    )
    private List<Human> humans = new ArrayList<>();

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", nameTransaction='" + nameTransaction + '\'' +
                ", date=" + date +
                ", idAccountFrom='" + idAccountFrom + '\'' +
                ", idAccountTo=" + idAccountTo +
                ", state=" + state +
                ", exchanger=" + exchanger +
                '}';
    }

    public double getState() {
        return state;
    }

    public void setState(double state) {
        this.state = state;
    }

    public String getIdAccountFrom() {
        return idAccountFrom;
    }

    public void setIdAccountFrom(String idAccountFrom) {
        this.idAccountFrom = idAccountFrom;
    }

    public int getIdAccountTo() {
        return idAccountTo;
    }

    public void setIdAccountTo(int idAccountTo) {
        this.idAccountTo = idAccountTo;
    }

    public List<Human> getHumans() {
        return humans;
    }

    public void setHumans(List<Human> humans) {
        this.humans = humans;
    }

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
