package com.zhw.cloud.fileupload.controller;

import com.google.common.base.Charsets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static com.zhw.cloud.fileupload.common.StaticCommon.Url;

/**
 * @Author zhw
 * @since 2022/1/12
 */
@Controller
@RequestMapping(Url)
public class FileUploadController {

    @Value("${fileupload.path}")
    String fileuploadPath;

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    @ResponseBody
    public  String handleFileUpload(@RequestParam(value = "file",required = true)MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String fileName = file.getOriginalFilename();
        File fileToSave = new File((fileuploadPath+fileName));
        FileCopyUtils.copy(bytes,fileToSave);
        return fileToSave.getAbsolutePath();
    }
}
