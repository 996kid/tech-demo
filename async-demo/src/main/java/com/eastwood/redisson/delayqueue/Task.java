package com.eastwood.redisson.delayqueue;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Task
 * @Description: TODO
 * @Author: yyh
 * @Date: 2022/10/25 16:30
 */
@Data
@AllArgsConstructor
public class Task implements Serializable {

    private String item;
}
