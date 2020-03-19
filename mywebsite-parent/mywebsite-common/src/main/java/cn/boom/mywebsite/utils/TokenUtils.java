package cn.boom.mywebsite.utils;
/**
 *
 * 用于生成随机的四位验证码
 *
 */
public class TokenUtils {

    private static String token;

    public void setToken(String token) {
        this.token = token;
    }

    public static String getToken(){

        return String.valueOf((int)((Math.random()*9+1)*1000));

    }

    public static void main(String[] args) {

        System.out.println(TokenUtils.getToken());
    }
}
