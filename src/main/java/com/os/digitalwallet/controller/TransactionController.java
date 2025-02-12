package com.os.digitalwallet.controller;

import com.os.digitalwallet.models.AccountInfo;
import com.os.digitalwallet.models.AccountTransactions;
import com.os.digitalwallet.models.Transaction;
import com.os.digitalwallet.models.Upi;
import com.os.digitalwallet.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;


    @PostMapping("/get")
    public ResponseEntity<AccountTransactions> getTransactions(
            @RequestParam(required = true) Boolean credit,
            @RequestBody Upi upi
    ){
        AccountTransactions response;
        if(credit){
            response = transactionService.getCreditTransactions(upi.getUpi());
        } else {
            response = transactionService.getDebitTransactions(upi.getUpi());
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<String> addTransaction(
            @RequestBody Transaction transaction
    ){
        String response = transactionService.addTransaction(transaction);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
