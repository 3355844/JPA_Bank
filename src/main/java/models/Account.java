package models;

import javax.persistence.*;

/**
 * Created by 33558 on 12.02.2017.
 */
@Entity
@Table(name = "ACCOUNT")
@NamedQueries({
        @NamedQuery(name = "Account.getAll",
                query = "SELECT a FROM Account a"),
        @NamedQuery(name = "Account.getByHumanIdAndCurrency",
                query = "SELECT a from Account a where a.currency = :currency and a.human.idHuman = :id"),
        @NamedQuery(name = "Account.getById", query = "SELECT a from Account a where a.id = :id")
})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ACCOUNT")
    private int id;
    @Column(name = "CURRENCY")
    private String currency;
    @Column(name = "STATE")
    private double state = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_HUMAN")
    private Human human;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", currency='" + currency + '\'' +
                ", state=" + state +
                ", human=" + human +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getState() {
        return state;
    }

    public void setState(double state) {
        this.state = state;
    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

    public Account() {
    }
}
