package com.eastwood.async;

import com.eastwood.async.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 996kid
 * @desription
 * @date 2019/12/31
 */
public class BaseController {
    @Autowired
    protected AsyncService asyncService;
}
