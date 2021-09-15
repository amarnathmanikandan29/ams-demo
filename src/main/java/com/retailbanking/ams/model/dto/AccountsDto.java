package com.retailbanking.ams.model.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class AccountsDto {

    @Getter
    @Setter
    private int customerId;
    @Getter
    @Setter
    private int accountId;
    @Getter
    @Setter
    private double amount;
}
