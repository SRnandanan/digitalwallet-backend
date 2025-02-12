package com.os.digitalwallet.service;

import com.os.digitalwallet.models.AccountInfo;
import com.os.digitalwallet.models.AccountTransactions;
import com.os.digitalwallet.models.Transaction;
import com.os.digitalwallet.repo.AccountInfoRepository;
import com.os.digitalwallet.repo.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService{

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountInfoRepository accountInfoRepository;

    private int senderBalance;

    private int receiverBalance;


    @Override
    public String addTransaction(Transaction transaction) {
        boolean validation = validateTransaction(transaction);
        if(!validation){
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Sender has no balance");
        }

        transaction.setTransactionId(generateTransactionId());
        transaction.setTransactionDate(LocalDate.now());
        transactionRepository.save(transaction);
        accountInfoRepository.updateBalanceByUpi(
                transaction.getSenderId(), this.senderBalance - transaction.getAmount());

        accountInfoRepository.updateBalanceByUpi(
                transaction.getReceiverId(), this.receiverBalance + transaction.getAmount());
        return "Transactions created successfully";
    }

    @Override
    public AccountTransactions getDebitTransactions(String senderId) {
        AccountTransactions transactions = new AccountTransactions();
        List<Transaction> retrievedTransactions = transactionRepository.findBySenderId(senderId);
        transactions.setTransactions(retrievedTransactions);
        return transactions;
    }

    @Override
    public AccountTransactions getCreditTransactions(String receiverId) {
        AccountTransactions transactions = new AccountTransactions();
        List<Transaction> retrievedTransactions = transactionRepository.findByReceiverId(receiverId);
        transactions.setTransactions(retrievedTransactions);
        return transactions;
    }

    private boolean validateTransaction(Transaction transaction){
        AccountInfo senderAccount = accountInfoRepository.findByUpi(transaction.getSenderId());
        AccountInfo receiverAccount = accountInfoRepository.findByUpi(transaction.getReceiverId());
        if(senderAccount == null || receiverAccount == null){
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "Account does not exist!");
        }
        this.senderBalance = senderAccount.getBalance();
        this.receiverBalance = receiverAccount.getBalance();
        return senderAccount.getBalance() > transaction.getAmount();
    }

    private String generateTransactionId() {
        return UUID.randomUUID().toString();
    }
}
