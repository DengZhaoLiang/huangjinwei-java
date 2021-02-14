package com.huangjinwei.service.supplier;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author huangjinwei
 * 2021-01-31
 */
@Service
@Slf4j
public class UploadServiceImpl implements UploadService {


    @Override
    public String upload(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("文件不能为空");
        }
        String fileName = file.getOriginalFilename();
        String suffix = file.getOriginalFilename().substring(fileName.lastIndexOf("."));
        String filePath = System.getProperty("user.dir") + "/image/";
        String realName = UUID.randomUUID().toString().replaceAll("-", "") + suffix;
        File dest = new File(filePath + realName);
        File temp = dest.getParentFile();
        while (!temp.exists()) {
            boolean mkdirs = temp.mkdirs();
            temp = temp.getParentFile();
        }
        try {
            file.transferTo(dest);
            log.info("上传文件成功：{}", dest.getPath());
            return "http://localhost:9527/image/" + realName;
        } catch (IOException e) {
            log.error("上传文件失败:", e);
        }
        throw new RuntimeException("上传失败");
    }
}
