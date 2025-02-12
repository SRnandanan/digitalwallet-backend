package com.os.digitalwallet.service;

import com.os.digitalwallet.models.AccountTransactions;
import com.os.digitalwallet.models.Transaction;

public interface TransactionService {

    public String addTransaction(Transaction transaction);

    public AccountTransactions getDebitTransactions(String senderId);

    public AccountTransactions getCreditTransactions(String receiverId);
}
