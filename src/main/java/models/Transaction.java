package models;

import javax.persistence.*;
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

    @OneToMany
    private List<Human> humans;
}
