package com.retailbanking.ams.api;

import com.retailbanking.ams.enums.TransactionType;
import com.retailbanking.ams.exceptionhandling.BadRequestException;
import com.retailbanking.ams.model.dto.TransactionsDto;
import com.retailbanking.ams.service.AccountServiceImpl;
import com.retailbanking.ams.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class AmsControllerApi {

    @Autowired
    AccountServiceImpl accountServiceImpl;

    @Autowired
    TransactionServiceImpl transactionServiceImpl;

    @PostMapping(value = "/customers/{customerId}")
    public ResponseEntity<String> createAccount(@PathVariable("customerId") int customerId) throws Exception {
        try {
            Boolean result = accountServiceImpl.createAccount(customerId);
            if (result) {
                return new ResponseEntity<String>(String.format("account created"), HttpStatus.CREATED);

            } else {
                return new ResponseEntity<String>(String.format("account created"), HttpStatus.ALREADY_REPORTED);
            }
        } catch (BadRequestException ex) {
            throw new BadRequestException("Bad request and its parameter format");
        }
    }

    @PostMapping(value = "/accounts/{accountId}/deposit/{amount}")
    public ResponseEntity<String> depositAmount(@PathVariable("accountId") int accountId, @PathVariable("amount") double amount) {
        try {
            Boolean result = transactionServiceImpl.depositAmount(accountId, amount, TransactionType.DEPOSIT);
            if (result) {
                return new ResponseEntity<String>(String.format("amount is deposited"), HttpStatus.CREATED);
            }
            return new ResponseEntity<String>(String.format("Invalid parameter entered"), HttpStatus.BAD_REQUEST);
        } catch (BadRequestException ex) {
            throw new BadRequestException("Bad request and its parameter format");
        }
    }

    @PostMapping(value = "/accounts/{accountId}/withdraw/{amount}")
    public ResponseEntity<String> withdrawAmount(@PathVariable("accountId") int accountId, @PathVariable("amount") double amount) {
        try {
            Boolean result = transactionServiceImpl.withdrawAmount(accountId, amount, TransactionType.WITHDRAWAL);
            return new ResponseEntity<String>(String.format("amount withdrawn"), HttpStatus.OK);
        } catch (BadRequestException ex) {
            throw new BadRequestException("Bad request and its parameter format");
        }
    }

    @GetMapping(value = "/accounts/{accountId}/balance")
    public ResponseEntity<String> getBalance(@PathVariable("accountId") int accountId) {
        try {
            Double balanceAmount = transactionServiceImpl.getBalance(accountId);
            return new ResponseEntity<String>(String.format("balanced Amount: %.2f ", balanceAmount), HttpStatus.OK);
        } catch (BadRequestException ex) {
            throw new BadRequestException("Bad request and its parameter format");
        }
    }

    @GetMapping(value = "/accounts/{accountId}/history")
    public ResponseEntity<String> getLastTenTransactions(@PathVariable("accountId") int accountId) {
        try {
            List<TransactionsDto> transactionsList = transactionServiceImpl.getLastTenTransactions(accountId);

            return new ResponseEntity<String>(String.format("Below are the list of last ten transactions: \n %s ", transactionsList.toString()), HttpStatus.OK);
        } catch (BadRequestException ex) {
            throw new BadRequestException("Bad request and its parameter format");
        }
    }
}
