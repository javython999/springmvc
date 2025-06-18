package com.errday.servlet.handlermethod;

import com.errday.servlet.reflection.core.Account;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    public String processRequest(Account account) {
        return "Hello from MyService, " + account.getUsername() + " / " + account.getEmail();
    }
}
