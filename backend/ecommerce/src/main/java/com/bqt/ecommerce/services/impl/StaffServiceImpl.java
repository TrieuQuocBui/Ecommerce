package com.bqt.ecommerce.services.impl;

import com.bqt.ecommerce.entities.Account;
import com.bqt.ecommerce.entities.Product;
import com.bqt.ecommerce.entities.Staff;
import com.bqt.ecommerce.entities.User;
import com.bqt.ecommerce.exceptions.NotFoundException;
import com.bqt.ecommerce.payloads.request.RoleRequest;
import com.bqt.ecommerce.payloads.request.StaffRequest;
import com.bqt.ecommerce.payloads.response.AccountResponse;
import com.bqt.ecommerce.payloads.response.StaffResponse;
import com.bqt.ecommerce.payloads.response.UserResponse;
import com.bqt.ecommerce.repositories.AccountRepository;
import com.bqt.ecommerce.repositories.StaffRepository;
import com.bqt.ecommerce.services.FileService;
import com.bqt.ecommerce.services.StaffService;
import com.bqt.ecommerce.utils.SecurityUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl implements StaffService {

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Value("${spring.resources.static-locations}")
    private String path;

    @Autowired
    private FileService fileService;

    @Override
    public StaffResponse getStaff() {
        Long idAccount = SecurityUtils.getPrincipal().getId();
        Account account = this.accountRepository.findById(idAccount).orElseThrow( () -> new NotFoundException("Không tìm thấy tài khoản"));
        Staff staff = this.staffRepository.findById(account.getStaff().getId()).orElseThrow( () -> new NotFoundException("Không tìm thấy nhân viên"));
        StaffResponse staffResponse = new StaffResponse();
        staffResponse.setImage(staff.getImage());
        staffResponse.setBirthDay(staff.getBirthDay());
        staffResponse.setFirstName(staff.getFirstName());
        staffResponse.setLastName(staff.getLastName());
        staffResponse.setIdentification(staff.getIdentification());
        staffResponse.setPhoneNumber(staff.getPhoneNumber());
        RoleRequest roleRequest = new RoleRequest();
        roleRequest.setName(staff.getAccount().getRole().getName());
        staffResponse.setRole(roleRequest);
        return staffResponse;
    }

    @Override
    public void updateStaff(String fileName, StaffRequest infoStaff) {
        Long idAccount = SecurityUtils.getPrincipal().getId();
        Account account = this.accountRepository.findById(idAccount).orElseThrow(() -> new NotFoundException("Không phải tài khoản hiện tại"));
        Staff findStaff = this.staffRepository.findById(account.getStaff().getId()).orElseThrow(() -> new NotFoundException("Không tìm thấy nhân viên"));
        this.fileService.deleteImage(path, findStaff.getImage());
        this.modelMapper.map(infoStaff, findStaff);
        findStaff.setImage(fileName);
        findStaff.setId(account.getStaff().getId());
        this.staffRepository.save(findStaff);
    }
}
