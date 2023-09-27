package com.bqt.ecommerce.services.impl;

import com.bqt.ecommerce.constants.RoleConstant;
import com.bqt.ecommerce.entities.Account;
import com.bqt.ecommerce.entities.Role;
import com.bqt.ecommerce.entities.Staff;
import com.bqt.ecommerce.entities.User;
import com.bqt.ecommerce.exceptions.BadRequestException;
import com.bqt.ecommerce.exceptions.NotFoundException;
import com.bqt.ecommerce.payloads.request.ActiveAccountRequest;
import com.bqt.ecommerce.payloads.request.SignUpRequest;
import com.bqt.ecommerce.payloads.request.SignUpStaffRequest;
import com.bqt.ecommerce.payloads.response.AccountResponse;
import com.bqt.ecommerce.payloads.response.PaginationResponse;
import com.bqt.ecommerce.repositories.AccountRepository;
import com.bqt.ecommerce.repositories.RoleRepository;
import com.bqt.ecommerce.repositories.UserRepository;
import com.bqt.ecommerce.services.AccountService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void createAccount(SignUpRequest signUpRequest) {
        Role role;

        Account account = modelMapper.map(signUpRequest,Account.class);

        if (!RoleConstant.USER.equals(account.getRole().getName()) ){
            throw new BadRequestException("Chức vụ không tồn tại");
        }

        Optional<Role> findRole = this.roleRepository.findByName(RoleConstant.USER);
        if (!findRole.isPresent()) {
            role = modelMapper.map(account.getRole(),Role.class);
            roleRepository.save(role);
        } else {
            role = findRole.get();
        }

        account.setRole(role);

        boolean checkUsername = this.accountRepository.existsByUsername(account.getUsername());

        if (checkUsername){
            throw new BadRequestException("Tên tài khoản đã tồn tại!");
        }

        account.setPassword(encoder.encode(account.getPassword()));

        User user = new User();
        user.setAccount(account);
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        account.setUser(user);

        accountRepository.save(account);
    }

    @Override
    public PaginationResponse getListUser(int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1,limit);

        Page userPage = this.accountRepository.findByUserNotNull(pageable);

        List<Account> accounts = userPage.getContent();

        Type listType = new TypeToken<List<AccountResponse>>(){}.getType();

        List<AccountResponse> orderResponses = modelMapper.map(accounts,listType);

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(orderResponses);
        paginationResponse.setLastPage(userPage.isLast());
        paginationResponse.setPageNumber(userPage.getNumber());
        paginationResponse.setPageSize(userPage.getSize());
        paginationResponse.setTotalElements(userPage.getTotalElements());
        paginationResponse.setTotalPages(userPage.getTotalPages());

        return paginationResponse;
    }

    @Override
    public void inactiveAccount(Long idAccount) {
        Account account = this.accountRepository.findById(idAccount).orElseThrow(() -> new NotFoundException("Không tìm thấy tài khoản"));
        account.setStatus(false);
        this.accountRepository.save(account);
    }

    @Override
    public void activeAccount(Long idAccount, ActiveAccountRequest activeAccountRequest) {
        Account account = this.accountRepository.findById(idAccount).orElseThrow(() -> new NotFoundException("Không tìm thấy tài khoản"));
        account.setStatus(activeAccountRequest.isStatus());
        this.accountRepository.save(account);
    }

    @Override
    public PaginationResponse getListStaff(int page, int limit) {
        Pageable pageable = PageRequest.of(page - 1,limit);

        Page userPage = this.accountRepository.findByStaffNotNull(pageable);

        List<Account> accounts = userPage.getContent();

        Type listType = new TypeToken<List<AccountResponse>>(){}.getType();

        List<AccountResponse> orderResponses = modelMapper.map(accounts,listType);

        PaginationResponse paginationResponse = new PaginationResponse();
        paginationResponse.setContent(orderResponses);
        paginationResponse.setLastPage(userPage.isLast());
        paginationResponse.setPageNumber(userPage.getNumber());
        paginationResponse.setPageSize(userPage.getSize());
        paginationResponse.setTotalElements(userPage.getTotalElements());
        paginationResponse.setTotalPages(userPage.getTotalPages());

        return paginationResponse;
    }

    @Override
    public void createAccountStaff(SignUpStaffRequest signUpStaffRequest) {
        Role role;

        Account account = new Account();
        account.setUsername(signUpStaffRequest.getUsername());
        account.setPassword("123456");
        account.setRole(this.modelMapper.map(signUpStaffRequest.getRole(),Role.class));

        if (!RoleConstant.ADMIN.equals(account.getRole().getName()) ){
            throw new BadRequestException("Chức vụ không tồn tại");
        }

        boolean checkUsername = this.accountRepository.existsByUsername(account.getUsername());

        if (checkUsername){
            throw new BadRequestException("Tên tài khoản đã tồn tại!");
        }

        Optional<Role> findRole = this.roleRepository.findByName(RoleConstant.ADMIN);
        if (!findRole.isPresent()) {
            role = modelMapper.map(account.getRole(),Role.class);
            roleRepository.save(role);
        } else {
            role = findRole.get();
        }

        account.setRole(role);

        account.setPassword(encoder.encode(account.getPassword()));

        Staff staff = new Staff();
        staff.setAccount(account);
        staff.setBirthDay(signUpStaffRequest.getBirthDay());
        staff.setFirstName(signUpStaffRequest.getFirstName());
        staff.setLastName(signUpStaffRequest.getLastName());
        staff.setIdentification(signUpStaffRequest.getIdentification());
        staff.setPhoneNumber(signUpStaffRequest.getPhoneNumber());
        account.setStaff(staff);

        accountRepository.save(account);
    }
}
