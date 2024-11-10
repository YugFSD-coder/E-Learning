package com.elearn.app.start_learn_back.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    private String userId;

    private String name;

    private String email;

    private String number;

    private String passsword;

    private String about;

    private boolean active;

    private boolean emailActive;

    private boolean smsVerified;

    private Date createAt;

    private String profilePath;

    private String resentOtp;
}
