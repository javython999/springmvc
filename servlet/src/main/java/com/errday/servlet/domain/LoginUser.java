package com.errday.servlet.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginUser {

    private long id;
    private String username;
    private String email;

}
