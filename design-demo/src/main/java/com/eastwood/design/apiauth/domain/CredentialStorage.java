package com.eastwood.design.apiauth.domain;

/**
 * @ClassName: CredentialStorage
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/3/20 21:54
 */
public interface CredentialStorage {

    String getPasswordByAppId(String appid);
}
