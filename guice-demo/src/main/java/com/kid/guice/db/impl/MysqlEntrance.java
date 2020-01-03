package com.kid.guice.db.impl;

import com.kid.guice.db.DbEntrance;

/**
 * @author 996kid
 * @desription
 * @date 2020/1/3
 */
public class MysqlEntrance implements DbEntrance {
    @Override
    public String select() {
        return "hello-mysql";
    }

    @Override
    public void delete() {

    }
}
