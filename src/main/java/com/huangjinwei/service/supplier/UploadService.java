package com.huangjinwei.service.supplier;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author huangjinwei
 * 2021-01-31
 */
public interface UploadService {

    /**
     * 上传文件返回文件路径
     */
    String upload(MultipartFile file);
}
