package com.retailbanking.ams.repository;

import com.retailbanking.ams.model.bo.Transactions;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<Transactions, Integer> {

    @Query(value = "select * from (select * from transactions order by transaction_id desc limit 10) var1 where account_Id = ?1 order by transaction_Id asc", nativeQuery = true)
    List<Transactions> findByAccountId(Integer accountId );
}
