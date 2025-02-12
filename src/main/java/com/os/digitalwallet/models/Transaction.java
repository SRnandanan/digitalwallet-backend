package com.os.digitalwallet.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Entity
@Data
@Table(name = "t_transaction")
public class Transaction {

    @Id
    @Column(name = "tid")
    private String transactionId;

    @Column(name = "senderId")
    private String senderId;

    @Column(name = "receiverId")
    private String receiverId;

    @Column(name = "amount")
    private int amount;

    @Column(name = "date")
    private LocalDate transactionDate;

}
