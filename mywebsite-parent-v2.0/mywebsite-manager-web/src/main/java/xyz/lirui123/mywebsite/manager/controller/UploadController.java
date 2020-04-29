package xyz.lirui123.mywebsite.manager.controller;

import com.alibaba.druid.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.lirui123.mywebsite.response.ResponseResult;
import xyz.lirui123.mywebsite.utils.QiniuCloudUtil;

@RestController
public class UploadController {

    /**
     * 图片上传
     * @param file
     * @return
     */
    @PostMapping("uploadImg")
    public ResponseResult upload(@RequestParam("file") MultipartFile file) {

        System.out.println(file);

        String url = QiniuCloudUtil.uploadImg(file);

        if (StringUtils.isEmpty(url)) {
            return ResponseResult.build(500, "文件上传失败！");
        }

        return ResponseResult.ok(url);
    }
}
