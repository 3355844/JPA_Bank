package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 33558 on 12.02.2017.
 */
@Entity
@Table(name = "human")
public class Human {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID_HUMAN")
    private int idHuman;
    @Column(name = "NAME_HUMAN")
    private String nameHuman;
    @OneToMany(mappedBy = "human", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List< Account> accounts = new ArrayList<>();


    @Override
    public String toString() {
        return "Human{" +
                "idHuman=" + idHuman +
                ", nameHuman='" + nameHuman + '\'' +
                ", accounts=" + accounts +
                '}';
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

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

    public Human() {
    }
}
