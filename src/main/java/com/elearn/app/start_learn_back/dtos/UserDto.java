package com.elearn.app.start_learn_back.dtos;

import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
public class UserDto {

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
