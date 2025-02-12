package com.os.digitalwallet.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
@Data
@AllArgsConstructor
@Table(name = "t_account_info")
public class AccountInfo {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "upi_id")
    private String upi;

    @Column(name = "bankName")
    private String bank;

    @Column(name = "balance")
    private int balance;




}
