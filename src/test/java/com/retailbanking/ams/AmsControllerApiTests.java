package com.retailbanking.ams;

import com.retailbanking.ams.api.AmsControllerApi;
import com.retailbanking.ams.enums.TransactionType;
import com.retailbanking.ams.model.dto.TransactionsDto;
import com.retailbanking.ams.service.AccountServiceImpl;
import com.retailbanking.ams.service.TransactionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AmsControllerApi.class)
public class AmsControllerApiTests {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JdbcTemplate jdbcTemplate;

    @MockBean
    private AmsControllerApi mockAmsControllerApi;

    @MockBean
    private TransactionServiceImpl mockTransactionServiceImpl;

    @Mock
    private List<TransactionsDto> transactionsDtos = new ArrayList<>();

    @MockBean
    AccountServiceImpl mockAccountServiceImpl;
    @Test
    public void testCreateAccount() throws Exception {
        Mockito.when(mockAccountServiceImpl.createAccount(1)).thenReturn(true);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers/1")
                        .param("customerId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateAccountButAlreadyExist() throws Exception {
        Mockito.when(mockAccountServiceImpl.createAccount(1)).thenReturn(false);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers/1")
                        .param("customerId", "1"))
                .andExpect(status().isOk());

    }

    @Test
    public void testDepositAmount() throws Exception {
        Mockito.when(mockTransactionServiceImpl.depositAmount(1,10, TransactionType.DEPOSIT)).thenReturn(true);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts/123/deposit/10")
                        .param("accountId", "123")
                        .param("amount", "10"))
                .andExpect(status().isOk());
    }

    @Test
    public void testWithdrawAmount() throws Exception {
        Mockito.when(mockTransactionServiceImpl.depositAmount(1,10, TransactionType.WITHDRAWAL)).thenReturn(true);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts/123/withdraw/10")
                        .param("accountId", "123")
                        .param("amount", "10"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetBalance() throws Exception {
        double balanceAmount =10;
        Mockito.when(mockTransactionServiceImpl.getBalance(1)).thenReturn(balanceAmount);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/accounts/123/balance")
                        .param("accountId", "123"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetLastTenTransactions() throws Exception {
        Mockito.when(mockTransactionServiceImpl.getLastTenTransactions(1)).thenReturn(transactionsDtos);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/accounts/123/history")
                        .param("accountId", "123"))
                .andExpect(status().isOk());
    }
}