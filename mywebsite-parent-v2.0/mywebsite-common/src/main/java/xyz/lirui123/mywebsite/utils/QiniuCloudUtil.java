package xyz.lirui123.mywebsite.utils;

import com.alibaba.fastjson.JSONObject;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * @description 七牛云工具
 */
public class QiniuCloudUtil {

    // 设置需要操作的账号的AK和SK
    private static final String ACCESS_KEY = "GU66CiZtEYYr1axKPf_PKQx_zexRHodRIiMZB6PZ";
    private static final String SECRET_KEY = "dZ59-fglPk9DPU8WELXdcGWJrvAgBCgWmys5inzr";

    //域名
    private static final String QINIU_IMAGE_DNS = "http://q9h5a75zh.bkt.clouddn.com/";

    // 要上传的空间名
    private static final String BUCKET_NAME = "my-web-site";

    // 密钥配置
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    // 构造一个带指定Zone对象的配置类,不同的七云牛存储区域调用不同的zone
    private static final Configuration cfg = new Configuration(Zone.zone0());
    private static final UploadManager uploadManager = new UploadManager(cfg);

    // 简单上传，使用默认策略，只需要设置上传的空间名就可以了
    private static String getUpToken() {
        return auth.uploadToken(BUCKET_NAME);
    }

    public static String uploadImg(MultipartFile file){

        try {
            int dotPos = file.getOriginalFilename().lastIndexOf(".");
            if (dotPos < 0) {
                return null;
            }
            String fileExt = file.getOriginalFilename().substring(dotPos + 1).toLowerCase();
            // 判断是否是合法的文件后缀
            if (!FileUtil.isFileAllowed(fileExt)) {
                return null;
            }

            String fileName = UUID.randomUUID().toString().replaceAll("-", "") + "." + fileExt;
            // 调用put方法上传
            Response res = uploadManager.put(file.getBytes(), fileName, getUpToken());
            // 打印返回的信息
            if (res.isOK() && res.isJson()) {
                // 返回这张存储照片的地址
                return QINIU_IMAGE_DNS + JSONObject.parseObject(res.bodyString()).get("key");
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}