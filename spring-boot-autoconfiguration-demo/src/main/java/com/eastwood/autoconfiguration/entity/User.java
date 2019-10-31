package com.eastwood.autoconfiguration.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author 996kid
 * @desription
 * @date 2019/10/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

    private String name;

    private int age;
}
