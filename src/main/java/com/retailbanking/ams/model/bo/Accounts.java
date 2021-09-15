package com.retailbanking.ams.model.bo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Accounts")
public class Accounts {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int accountId;

    @Getter
    @Setter
    @Column(name = "customerId")
    private int customerId;

    @Getter
    @Setter
    @Column(name = "amount")
    private double amount;
}
