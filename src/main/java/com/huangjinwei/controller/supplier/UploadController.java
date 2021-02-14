package com.huangjinwei.controller.supplier;

import com.huangjinwei.service.supplier.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author huangjinwei
 * 2021-01-31
 */
@RestController
@RequestMapping("/api/supplier/upload")
public class UploadController {

    @Autowired
    private UploadService mUploadService;

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file) {
        return mUploadService.upload(file);
    }
}
