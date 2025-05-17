package com.os.digitalwallet.controller;

import com.os.digitalwallet.models.AccountInfo;
import com.os.digitalwallet.models.AccountTransactions;
import com.os.digitalwallet.models.Transaction;
import com.os.digitalwallet.models.Upi;
import com.os.digitalwallet.service.AccountInfoService;
import com.os.digitalwallet.service.TransactionService;
import com.os.digitalwallet.utils.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    
    @Autowired
    private TransactionService transactionService;

    @Autowired
    private AccountInfoService accountService;


    @PostMapping
    public ResponseEntity<?> getTransactions(
            @RequestParam(required = true) Boolean credit,
            @RequestBody Upi upi,
            @RequestHeader("Authorization") String token
    ){
        /**
         * First validate if the upi belongs to the user who is making transaction
         * Get user from JWT
         * Get UPI from user account table
         * Match them
         * */
        String userName = UserUtil.getAuthenticatedUser(token);
        AccountInfo userAccount = accountService.getAccountInfo(userName);
        if(!userAccount.getUpi().equals(upi.getUpi())){
            return new ResponseEntity<>("You do not have permissions to access this resource", HttpStatus.FORBIDDEN);
        }
        AccountTransactions response;
        if(credit){
            response = transactionService.getCreditTransactions(upi.getUpi());
        } else {
            response = transactionService.getDebitTransactions(upi.getUpi());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> addTransaction(
            @RequestBody Transaction transaction,
            @RequestHeader("Authorization") String token
    ){
        /**
         * Validate if the sender's upi matches with the authenticated user
         * */
        String userName = UserUtil.getAuthenticatedUser(token);
        AccountInfo userAccount = accountService.getAccountInfo(userName);
        if(!userAccount.getUpi().equalsIgnoreCase(transaction.getSenderId())){
            return new ResponseEntity<>("You do not have permissions to access this resource", HttpStatus.FORBIDDEN);
        }
        String response = transactionService.addTransaction(transaction);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
