package com.eastwood.design.pattern.responsibilitychain.pipeline;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 996kid@gmail.com
 * @version 1.0
 * @description Me
 * @date 2023/9/1
 */

@Setter
@Getter
public class Me {

    private String status;

    public Me(String status) {
        this.status = status;
    }
}
