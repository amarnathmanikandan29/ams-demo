package com.retailbanking.ams;

import com.retailbanking.ams.api.AmsControllerApi;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AmsControllerApi.class)
public class AmsControllerApiTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AmsControllerApi amsControllerApi;

    @Test
    public void testCreateAccount() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers/1")
                        .param("customerId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateAccountButAlreadyExist() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/customers/1")
                        .param("customerId", "1"))
                .andExpect(status().isOk());

    }

    @Test
    public void testDepositAmount() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts/123/deposit/10")
                        .param("accountId", "123")
                        .param("amount", "10"))
                .andExpect(status().isOk());
    }

    @Test
    public void testWithdrawAmount() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/accounts/123/withdraw/10")
                        .param("accountId", "123")
                        .param("amount", "10"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetBalance() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/accounts/123/balance")
                        .param("accountId", "123"))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetLastTenTransactions() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/accounts/123/history")
                        .param("accountId", "123"))
                .andExpect(status().isOk());
    }
}