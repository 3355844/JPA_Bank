package models;

import javax.persistence.*;

/**
 * Created by 33558 on 12.02.2017.
 */
@Entity
@Table(name = "ACCOUNT")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_ACCOUNT")
    private int id;
    @Column(name = "CURRENCY")
    private String currency;
    @Column(name = "STATE")
    private double state;
    @OneToMany
    private Human human;

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

    public Account(int id, String currency, double state, Human human) {
        this.id = id;
        this.currency = currency;
        this.state = state;
        this.human = human;
    }

    public Account() {
    }
}
