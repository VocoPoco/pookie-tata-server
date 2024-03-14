package com.pookietata.hacktues.repositories;

import com.pookietata.hacktues.models.Transaction;
import com.pookietata.hacktues.models.enums.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByUserId(Long userId);

    // Find transactions by type (e.g., "EARN", "SPEND")
    List<Transaction> findByTransactionType(TransactionType transactionType);

}
