package com.bqt.ecommerce.controllers.auth;

import com.bqt.ecommerce.entities.Account;
import com.bqt.ecommerce.payloads.request.JwtAuthRequest;
import com.bqt.ecommerce.payloads.request.RoleRequest;
import com.bqt.ecommerce.payloads.request.SignUpStaffRequest;
import com.bqt.ecommerce.payloads.response.AccountResponse;
import com.bqt.ecommerce.payloads.response.JwtAuthResponse;
import com.bqt.ecommerce.security.jwt.JwtHelper;
import com.bqt.ecommerce.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import com.bqt.ecommerce.payloads.request.SignUpRequest;
import com.bqt.ecommerce.payloads.response.MessResponse;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private AccountService accountService;

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/denied")
    public ResponseEntity<MessResponse> showPageDenied(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessResponse("Truy cập bị từ chối"));
    }

    @PostMapping("/register")
    public ResponseEntity<MessResponse> createAccount(@RequestBody SignUpRequest signUpRequest){

        this.accountService.createAccount(signUpRequest);

        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("Đăng kí thành công"));
    }

    @PostMapping("/register/staff")
    public ResponseEntity<MessResponse> createAccountStaff(@RequestBody SignUpStaffRequest SignUpStaffRequest){

        this.accountService.createAccountStaff(SignUpStaffRequest);

        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("Đăng kí thành công"));
    }

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(jwtAuthRequest.getUsername(), jwtAuthRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Account account = (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String jwt = jwtHelper.generateJwtToken(authentication);

        JwtAuthResponse response = new JwtAuthResponse();

        AccountResponse accountResponse = new AccountResponse();

        accountResponse.setUsername(account.getUsername());
        RoleRequest role = new RoleRequest();
        role.setName(account.getRole().getName());
        accountResponse.setRole(role);

        response.setToken(jwt);
        response.setAccount(accountResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
