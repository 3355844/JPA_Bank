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
    @ManyToOne
    private Transaction transaction;

}
