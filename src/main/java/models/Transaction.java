package models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 33558 on 12.02.2017.
 */
@Entity
@Table (name = "TRANSACTION")
public class Transaction {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "ID_TRANSACTION")
    private int id;

    private Exchanger exchanger = new Exchanger();

    @OneToMany(mappedBy = "HUMAN", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Human> humans = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Human> getHumans() {
        return humans;
    }

    public void setHumans(List<Human> humans) {
        this.humans = humans;
    }

    public Transaction() {
    }
}
