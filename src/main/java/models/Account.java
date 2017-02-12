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
    @Column (name = "UAH")
    private double UAH;
    @Column (name = "USD")
    private double USD;
    @Column (name = "EUR")
    private double EUR;

    @OneToOne (fetch = FetchType.LAZY, mappedBy = "ACCOUNT")
    private Human human;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getUAH() {
        return UAH;
    }

    public void setUAH(double UAH) {
        this.UAH = UAH;
    }

    public double getUSD() {
        return USD;
    }

    public void setUSD(double USD) {
        this.USD = USD;
    }

    public double getEUR() {
        return EUR;
    }

    public void setEUR(double EUR) {
        this.EUR = EUR;
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
