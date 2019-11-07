package com.eastwood.stream.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 996kid
 * @desription
 * @date 2019/11/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String username;

    private String password;

    private int age;
}
