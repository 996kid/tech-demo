package com.eastwood.design.apiauth.domain;

import lombok.Getter;

/**
 * @ClassName: ApiRequest
 * @Description: 1.  把URL、AppID、密码、时间戳拼接为一个字符串；
 *               2.  对字符串通过加密算法加密生成token；
 *               3.  将token、AppID、时间戳拼接到URL中，形成新的URL；
 *               4.  解析URL，得到token、AppID、时间戳等信息；
 *               5.  从存储中取出AppID和对应的密码；
 *               6.  根据时间戳判断token是否过期失效；
 *               7.  验证两个token是否匹配；
 * @Author: yyh
 * @Date: 2023/3/20 17:49
 */
@Getter
public class ApiRequest {

    private String originalUrl;

    private String appid;

    private Long timestamp;

    private String token;

    public ApiRequest(String baseUrl, String appid, String timestamp, String token) {

    }

    public static ApiRequest buildFromUrl(String url) {
        return new ApiRequest("baseUrl", "appid", "timestamp", "token");
    }

    //拼接URL、AppID、密码、时间戳
    public String createUrl(String baseUrl, String appid, String timestamp, String secret) {
        return null;
    }


}
