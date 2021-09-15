package com.retailbanking.ams.repository;

import com.retailbanking.ams.model.bo.Accounts;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends CrudRepository<Accounts, Integer> {

    List<Accounts> findByCustomerId(Integer customerId);

    Accounts findByAccountId(Integer accountId);
}
