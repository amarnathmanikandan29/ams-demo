package com.retailbanking.ams.service;

import com.retailbanking.ams.enums.TransactionType;
import com.retailbanking.ams.exceptionhandling.ResourceNotFoundException;
import com.retailbanking.ams.model.bo.Accounts;
import com.retailbanking.ams.model.bo.Transactions;
import com.retailbanking.ams.model.dto.AccountsDto;
import com.retailbanking.ams.model.dto.TransactionsDto;
import com.retailbanking.ams.model.mapper.AccountsMapperService;
import com.retailbanking.ams.model.mapper.TransactionsMapperService;
import com.retailbanking.ams.repository.AccountRepository;
import com.retailbanking.ams.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    AccountsMapperService accountsMapperService;

    @Autowired
    TransactionsMapperService transactionsMapperService;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    AccountRepository accountRepository;

    @Override
    public boolean depositAmount(Integer accountId, double amount, TransactionType transactionType) {

        Accounts accounts = accountRepository.findByAccountId(accountId);
        if (accounts != null) {
            accounts.setAmount(accounts.getAmount() + amount);
            TransactionsDto transactionsDto = new TransactionsDto();
            transactionsDto.setAccountId(accountId);
            transactionsDto.setAmount(accounts.getAmount());
            transactionsDto.setTransactionType(transactionType.getId());

            Accounts accountsResult = accountRepository.save(accounts);

            if (accountsResult != null) {
                Transactions transactions = transactionsMapperService.convertToTransactionsBo(transactionsDto);
                transactionRepository.save(transactions);
                return true;
            }
        } else
            throw new ResourceNotFoundException("Invalid Account");
        return false;
    }

    @Override
    public boolean withdrawAmount(Integer accountId, double amount, TransactionType transactionType) {

        Accounts accounts = accountRepository.findByAccountId(accountId);
        if (accounts != null) {
            if (accounts.getAmount() != 0 || accounts.getAmount() > amount) {
                accounts.setAmount(accounts.getAmount() - amount);
                accountRepository.save(accounts);

                TransactionsDto transactionsDto = new TransactionsDto();
                transactionsDto.setAccountId(accountId);
                transactionsDto.setAmount(accounts.getAmount());
                transactionsDto.setTransactionType(transactionType.getId());

                Transactions transactions = transactionsMapperService.convertToTransactionsBo(transactionsDto);
                transactionRepository.save(transactions);

                return true;
            }
        } else
            throw new ResourceNotFoundException("Invalid Account");
        return false;
    }

    @Override
    public double getBalance(Integer accountId) {

        double balanceAmount = 0;
        Accounts account = accountRepository.findByAccountId(accountId);
        if (account != null) {
            AccountsDto accountsDto = accountsMapperService.convertToAccountsDto(account);
            balanceAmount = accountsDto.getAmount();
            return balanceAmount;
        } else
            throw new ResourceNotFoundException("Invalid Account");
    }

    @Override
    public List<TransactionsDto> getLastTenTransactions(Integer accountId) {

        List<TransactionsDto> TransactionsDtos = transactionsMapperService.convertToTransactionsDtoList(transactionRepository.findByAccountId(accountId));

        if (TransactionsDtos.size() != 0) {
            return TransactionsDtos;
        } else
            throw new ResourceNotFoundException("Invalid Account or No Transactions available");
    }
}
