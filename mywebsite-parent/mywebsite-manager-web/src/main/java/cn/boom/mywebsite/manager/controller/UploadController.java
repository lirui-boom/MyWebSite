package cn.boom.mywebsite.manager.controller;

import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.utils.FastDFSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

    @Value("${FILE_SERVER_URL}")
    private String FILE_SERVER_URL;

    /**
     * 图片上传
     * @param file
     * @return
     */
    @RequestMapping("/uploadImg")
    @ResponseBody
    public MyWebSiteResult uploadImg(MultipartFile file) {

        String filename = file.getOriginalFilename();
        int lastIndexOf = filename.lastIndexOf('.');
        String pic_type = filename.substring(lastIndexOf + 1);

        try {

            FastDFSClient client = new FastDFSClient("classpath:properties/fdfs_client.conf");
            String path = client.uploadFile(file.getBytes(), pic_type);
            String url = FILE_SERVER_URL + path;

            return MyWebSiteResult.ok(url);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return MyWebSiteResult.build(500,"上传图片发生异常");
    }
}
