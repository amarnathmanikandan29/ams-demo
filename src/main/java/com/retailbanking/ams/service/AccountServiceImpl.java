package com.retailbanking.ams.service;

import com.retailbanking.ams.exceptionhandling.ResourceAlreadyExists;
import com.retailbanking.ams.model.bo.Accounts;
import com.retailbanking.ams.model.dto.AccountsDto;
import com.retailbanking.ams.model.mapper.AccountsMapperService;
import com.retailbanking.ams.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountsMapperService accountsMapperService;

    @Autowired
    AccountsDto accountsDto;

    @Override
    public boolean createAccount(Integer customerId) {

        accountsDto.setCustomerId(customerId);

        Accounts accounts = accountsMapperService.convertToAccountsBo(accountsDto);
        List<Accounts> accountsList = accountRepository.findByCustomerId(accounts.getCustomerId());

        if (accountsList.size() == 0) {
            Accounts accountsResult = accountRepository.save(accounts);
            if (!"".equals(accountsResult)) {
                return true;
            }
        }
        else
            throw new ResourceAlreadyExists("Customer already exist");
        return false;
    }
}
