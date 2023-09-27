package com.bqt.ecommerce.utils;

import com.bqt.ecommerce.entities.Account;
import com.bqt.ecommerce.entities.User;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    public static Account getPrincipal(){
        Account account = (Account) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
        return account;
    }
}
