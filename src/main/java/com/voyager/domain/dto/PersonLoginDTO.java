package com.voyager.domain.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class PersonLoginDTO implements Serializable {
    private String phone;

    private String password;
}
