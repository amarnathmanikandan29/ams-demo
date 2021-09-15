package com.retailbanking.ams.model.mapper;

import com.retailbanking.ams.model.bo.Accounts;
import com.retailbanking.ams.model.dto.AccountsDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountsMapperService {

    @Autowired
    private ModelMapper modelMapper;

    public AccountsMapperService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AccountsDto convertToAccountsDto(Accounts accounts) {

        AccountsDto accountsDto = modelMapper.map(accounts , AccountsDto.class);
        return accountsDto;
    }

    public Accounts convertToAccountsBo(AccountsDto accountsDto) {

        Accounts accounts =  modelMapper.map(accountsDto , Accounts.class);
        return accounts;
    }
}
