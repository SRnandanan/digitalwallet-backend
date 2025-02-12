package com.os.digitalwallet.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_personal_info")
public class PersonalInfo {

    @Id
    @Column(name = "user_id")
    private String userId;

    @Column(name = "fName")
    private String firstName;

    @Column(name = "lName")
    private String lastName;

    @Column(name = "dob")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateOfBirth;

    @Column(name = "address")
    private String address;
}
