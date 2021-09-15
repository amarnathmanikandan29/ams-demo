package com.retailbanking.ams.model.bo;

import lombok.Getter;
import lombok.Setter;
import lombok.val;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import javax.persistence.*;

@Entity
@Table(name = "Transactions")
public class Transactions {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int transactionId;

    @Getter
    @Setter
    @Column(name = "accountId")
    private int accountId;

    @Getter
    @Setter
    @Column(name = "amount")
    private double amount;

    @Getter
    @Setter
    @Column(name = "transactionType")
    private int TransactionType;

    @IgnoreForBinding
    @Override
    public String toString(){
        val transactionType = TransactionType;
        return "TransactionId "+transactionId+", AccountId:" +accountId+", Amount:"+amount+",TransactionType:"+ transactionType +"\n";
    }
}
