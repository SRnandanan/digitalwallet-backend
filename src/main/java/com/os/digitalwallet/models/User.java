package com.os.digitalwallet.models;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    
}
