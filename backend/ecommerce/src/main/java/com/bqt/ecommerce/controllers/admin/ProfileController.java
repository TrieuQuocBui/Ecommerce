package com.bqt.ecommerce.controllers.admin;

import com.bqt.ecommerce.payloads.request.StaffRequest;
import com.bqt.ecommerce.payloads.request.UserRequest;
import com.bqt.ecommerce.payloads.response.MessResponse;
import com.bqt.ecommerce.payloads.response.StaffResponse;
import com.bqt.ecommerce.payloads.response.UserResponse;
import com.bqt.ecommerce.services.FileService;
import com.bqt.ecommerce.services.StaffService;
import com.bqt.ecommerce.services.UserService;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController("Profile-Admin")
@CrossOrigin("*")
@RequestMapping("/admin/")
public class ProfileController {

    @Value("${spring.resources.static-locations}")
    private String path;

    @Autowired
    private FileService fileService;

    @Autowired
    private StaffService staffService;

    @GetMapping("profile")
    public ResponseEntity<StaffResponse> showInfoStaff(){
        StaffResponse infoStaff = this.staffService.getStaff();
        return ResponseEntity.status(HttpStatus.OK).body(infoStaff);
    }

    @PutMapping("profile")
    public ResponseEntity<MessResponse> updateProfile(@RequestPart("image") MultipartFile file,@RequestPart StaffRequest infoStaff) throws IOException {
        String fileName = this.fileService.uploadImage(path, file);
        this.staffService.updateStaff(fileName,infoStaff);
        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("chỉnh sữa thành công"));
    }

}
