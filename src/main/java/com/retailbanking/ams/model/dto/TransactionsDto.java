package com.retailbanking.ams.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.val;
import org.springframework.stereotype.Component;

@Component
public class TransactionsDto {

    @Getter
    @Setter
    private int transactionId;

    @Getter
    @Setter
    private int accountId;

    @Getter
    @Setter
    private double amount;

    @Getter
    @Setter
    private int TransactionType;

    @Override
    public String toString(){
        val transactionType = TransactionType;
        return "TransactionId "+transactionId+", AccountId:" +accountId+", Amount:"+amount+",TransactionType:"+ transactionType +"\n";
    }
}
