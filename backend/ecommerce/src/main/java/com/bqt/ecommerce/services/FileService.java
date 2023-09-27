package com.bqt.ecommerce.services;

import io.github.classgraph.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface FileService {
    String uploadImage(String path, MultipartFile file) throws IOException;

    InputStream getResource(String path, String fileName) throws FileNotFoundException;

    boolean deleteImage(String path,String fileName);
}
