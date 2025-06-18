package com.errday.servlet.reflection.core;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Account {

    private Long id;
    private String username;
    private String email;
    private String profile;

    public Account(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
