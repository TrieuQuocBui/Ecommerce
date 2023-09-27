package com.bqt.ecommerce.controllers.user;

import com.bqt.ecommerce.payloads.request.UserRequest;
import com.bqt.ecommerce.payloads.response.MessResponse;
import com.bqt.ecommerce.payloads.response.PaginationResponse;
import com.bqt.ecommerce.payloads.response.UserResponse;
import com.bqt.ecommerce.services.FileService;
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

@RestController
@CrossOrigin("*")
@RequestMapping("/")
public class ProfileController {

    @Value("${spring.resources.static-locations}")
    private String path;

    @Autowired
    private FileService fileService;

    @Autowired
    private UserService userService;

    @GetMapping("profile")
    public ResponseEntity<UserResponse> showInfoUser(){
        UserResponse infoUser = this.userService.getUser();
        return ResponseEntity.status(HttpStatus.OK).body(infoUser);
    }

    @PutMapping("profile/upload")
    public ResponseEntity<MessResponse> updateProfile(@RequestParam("image") MultipartFile file) throws IOException {
        String fileName = this.fileService.uploadImage(path,file);
        this.userService.updateUser(fileName,null);
        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse(fileName));
    }

    @PutMapping("profile")
    public ResponseEntity<MessResponse> updateProfile(@RequestBody UserRequest infoUser) throws IOException {
        this.userService.updateUser(null,infoUser);
        return ResponseEntity.status(HttpStatus.OK).body(new MessResponse("chỉnh sữa thành công"));
    }

    @GetMapping(value = "image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(
            @PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {

        InputStream resource = this.fileService.getResource(path,imageName);

        response.setContentType(MediaType.IMAGE_JPEG_VALUE);

        StreamUtils.copy(resource,response.getOutputStream());
    }
}
