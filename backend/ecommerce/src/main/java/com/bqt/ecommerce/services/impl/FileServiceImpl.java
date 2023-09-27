package com.bqt.ecommerce.services.impl;

import com.bqt.ecommerce.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {

        //File name: abc.png
        String name = file.getOriginalFilename();


        //Random name generate file
        String randomId = UUID.randomUUID().toString();
        //String fileName = randomId.concat(name.substring(name.lastIndexOf(".")));
        String fileName = randomId;


        //Full path
        String filePath = path + File.separator + fileName;

        // create folder if not created
        File f = new File(path);
        if( !f.exists()){
            f.mkdir();
        }

        //file copy
        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {

        String fullPath = path + File.separator + fileName;

        InputStream is = new FileInputStream(fullPath);

        return is;
    }

    @Override
    public boolean deleteImage(String path, String fileName) {
        String fullPath = path + fileName;
        System.out.println(fullPath);
        File file = new File(fullPath);
        return file.delete();
    }
}
