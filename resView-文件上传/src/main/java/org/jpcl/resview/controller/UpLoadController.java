package org.jpcl.resview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Administrator
 */
@Controller
public class UpLoadController {

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        return "upload file";
    }

    @CrossOrigin
    @PostMapping("/pull")
    @ResponseBody
    public String pull(@RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        // 多文件上传则 可以从
        // List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");
        // 其它处理相同
        if (file.isEmpty()) {
            return "上传失败，请选择文件";
        }

        String path = UpLoadController.class.getResource("/").getPath() + "upload/";
        File tmp = new File(path);
        if (!tmp.exists()) {
            tmp.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        File dest = new File(path + fileName);
        file.transferTo(dest);
        return "上传成功";
    }


    public static void main(String[] args) throws FileNotFoundException {
        String classPath = ResourceUtils.getURL("classpath:").getPath();
        System.out.println(classPath);
    }
}
