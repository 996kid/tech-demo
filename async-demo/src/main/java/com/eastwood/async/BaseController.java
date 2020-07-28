package com.eastwood.async;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.eastwood.async.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 996kid
 * @desription
 * @date 2019/12/31
 */
public class BaseController {
    @Autowired
    @TableId(type = IdType.ID_WORKER_STR)
    protected AsyncService asyncService;
}
