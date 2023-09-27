package com.bqt.ecommerce.controllers.admin;

import com.bqt.ecommerce.payloads.request.ActiveAccountRequest;
import com.bqt.ecommerce.payloads.response.MessResponse;
import com.bqt.ecommerce.payloads.response.PaginationResponse;
import com.bqt.ecommerce.services.AccountService;
import com.bqt.ecommerce.services.UserService;
import com.bqt.ecommerce.utils.PaginationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/admin/")
@PreAuthorize("hasAuthority('ADMIN')")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("account/users")
    public ResponseEntity<PaginationResponse> showListUser(@RequestParam(name = "page", defaultValue = PaginationUtils.DEFAULT_PAGE) int page, @RequestParam(name = "limit", defaultValue = PaginationUtils.DEFAULT_LIMIT) int limit){
        PaginationResponse paginationResponse = this.accountService.getListUser(page,limit);
        return ResponseEntity.status(HttpStatus.OK).body(paginationResponse);
    }

    @GetMapping("account/staffs")
    public ResponseEntity<PaginationResponse> showListStaff(@RequestParam(name = "page", defaultValue = PaginationUtils.DEFAULT_PAGE) int page, @RequestParam(name = "limit", defaultValue = PaginationUtils.DEFAULT_LIMIT) int limit){
        PaginationResponse paginationResponse = this.accountService.getListStaff(page,limit);
        return ResponseEntity.status(HttpStatus.OK).body(paginationResponse);
    }

    @DeleteMapping("account/user/{idAccount}")
    public ResponseEntity<MessResponse> removeUser(@PathVariable("idAccount") Long idUser){
        this.accountService.inactiveAccount(idUser);
        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("Tắt hoạt động thành công"));
    }

    @PutMapping("account/user/{idAccount}")
    public ResponseEntity<MessResponse> updateUser(@PathVariable("idAccount") Long idUser,@RequestBody ActiveAccountRequest activeAccountRequest){
        this.accountService.activeAccount(idUser,activeAccountRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("Chỉnh sửa hoạt động thành công"));
    }
}
