package com.eastwood.design.apiauth.domain;

import java.util.Date;

/**
 * @ClassName: AuthToken
 * @Description: 1.  把URL、AppID、密码、时间戳拼接为一个字符串；
 *               2.  对字符串通过加密算法加密生成token；
 *               3.  将token、AppID、时间戳拼接到URL中，形成新的URL；
 *               4.  解析URL，得到token、AppID、时间戳等信息；
 *               5.  从存储中取出AppID和对应的密码；
 *               6.  根据时间戳判断token是否过期失效；
 *               7.  验证两个token是否匹配；
 * @Author: yyh
 * @Date: 2023/3/20 17:28
 */
public class AuthToken {

    private String token;

    private Date createTime;

    private Long expiredTime = 120l;

    public AuthToken() {

    }

    public AuthToken(String token, Long timestamp) {

    }

    public static AuthToken generate(String originalUrl, String appId, String password, long timestamp) {
        return new AuthToken();
    }


    public String createToken(String str) {
        return null;
    }

    public String getToken() {
        return this.token;
    }

    public boolean isExpired() {
        return true;
    }

    public boolean match(AuthToken authToken) {
        return true;
    }
}
