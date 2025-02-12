package com.os.digitalwallet.repo;


import com.os.digitalwallet.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findBySenderId(String senderId);

    List<Transaction> findByReceiverId(String receiverId);
}
