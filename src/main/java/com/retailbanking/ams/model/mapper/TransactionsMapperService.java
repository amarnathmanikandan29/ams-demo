package com.retailbanking.ams.model.mapper;

import com.retailbanking.ams.model.bo.Transactions;
import com.retailbanking.ams.model.dto.TransactionsDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionsMapperService {

    @Autowired
    private ModelMapper modelMapper;

    public TransactionsMapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TransactionsDto convertToTransactionsDto(Transactions transactions) {

        TransactionsDto transactionsDto = modelMapper.map(transactions , TransactionsDto.class);
        return transactionsDto;
    }

    public Transactions convertToTransactionsBo(TransactionsDto transactionsDto) {

        Transactions transactions = modelMapper.map(transactionsDto , Transactions.class);
        return transactions;
    }

    public List<TransactionsDto> convertToTransactionsDtoList(List<Transactions> transactionsList) {

        Type listType = new TypeToken<List<TransactionsDto>>(){}.getType();
        List<TransactionsDto> transactionsDto = modelMapper.map(transactionsList,listType);

        return transactionsDto;
    }

    public List<Transactions> convertToTransactionsBoList(List<TransactionsDto> transactionsDtoList) {

        List<Transactions> transactionsList = transactionsDtoList
                .stream()
                .map(user -> modelMapper.map(transactionsDtoList, Transactions.class))
                .collect(Collectors.toList());

        return transactionsList;
    }
}
