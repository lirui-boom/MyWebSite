package xyz.lirui123.mywebsite.protal.controller;

import com.alibaba.druid.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import xyz.lirui123.mywebsite.response.ResponseResult;
import xyz.lirui123.mywebsite.utils.QiniuCloudUtil;

@RestController
public class ImageOnlineController {


    @PostMapping("uploadImg")
    public ResponseResult upload(@RequestParam("file") MultipartFile file) {

        String url = QiniuCloudUtil.uploadImg(file);

        if (StringUtils.isEmpty(url)) {
            return ResponseResult.build(500, "文件上传失败！");
        }

        return ResponseResult.ok(url);
    }

}
