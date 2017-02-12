package models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by 33558 on 12.02.2017.
 */
@Entity
@Table(name = "HUMAN")
public class Human {
    @Id
    @Column(name = "ID_HUMAN")
    private int idHuman;
    @Column (name = "NAME_HUMAN")
    private String nameHuman;
    @ManyToOne
    private List<Account> accounts;

}
