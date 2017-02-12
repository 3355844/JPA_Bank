package models;

import javax.persistence.*;

/**
 * Created by 33558 on 12.02.2017.
 */
@Entity
@Table(name = "HUMAN")
public class Human {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column(name = "ID_HUMAN")
    private int idHuman;
    @Column (name = "NAME_HUMAN")
    private String nameHuman;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "ID_ACCOUNT")
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_TRANSACTION")
    private Transaction transaction;

    public int getIdHuman() {
        return idHuman;
    }

    public void setIdHuman(int idHuman) {
        this.idHuman = idHuman;
    }

    public String getNameHuman() {
        return nameHuman;
    }

    public void setNameHuman(String nameHuman) {
        this.nameHuman = nameHuman;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public Human() {
    }
}
