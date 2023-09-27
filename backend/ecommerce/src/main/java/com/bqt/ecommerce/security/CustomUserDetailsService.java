package com.bqt.ecommerce.security;


import com.bqt.ecommerce.entities.Account;
import com.bqt.ecommerce.exceptions.NotFoundException;
import com.bqt.ecommerce.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = this.accountRepository.findByUsername(username).orElseThrow( () -> new NotFoundException("Tài khoản không tồn tại!"));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return account;
    }
}
