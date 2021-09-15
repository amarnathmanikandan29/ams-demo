package com.retailbanking.ams.service;

import com.retailbanking.ams.enums.TransactionType;
import com.retailbanking.ams.model.dto.TransactionsDto;

import java.util.List;

public interface TransactionService {

    boolean depositAmount(Integer accountId,double amount,TransactionType transactionType);
    boolean withdrawAmount(Integer accountId,double amount,TransactionType transactionType);

    double getBalance(Integer accountId);
    List<TransactionsDto> getLastTenTransactions(Integer accountId);
}
