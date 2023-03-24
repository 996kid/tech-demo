package com.eastwood.design.apiauth.domain;

/**
 * @ClassName: ApiAuthenticator
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/3/21 11:46
 */
public interface ApiAuthenticator {

    void auth(String url);

    void auth(ApiRequest apiRequest);
}
