package cn.boom.mywebsite.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * <p>
 * BCryptPasswordEncoder加密工具类
 * Spring Security 提供的 BCryptPasswordEncoder 加密算法进行加密
 * </p>
 */
public class BCryptUtil {
    /**
     *  对字符串加密
     * @param str 加密对象字符串
     * @return 已加密结果字符串
     */
    public static String encode(String str) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.encode(str);
    }

    /**
     * 验证密码是否和已加密对象字符串匹配
     * @param passWord 密码字符串
     * @param encodePassWord 已加密对象字符串
     * @return
     */
    public static boolean isMatch(String passWord, String encodePassWord) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(passWord, encodePassWord);
    }
}
