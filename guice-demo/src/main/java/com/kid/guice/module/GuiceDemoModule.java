package com.kid.guice.module;

import com.google.inject.AbstractModule;
import com.kid.guice.db.DbEntrance;
import com.kid.guice.db.impl.MysqlEntrance;

/**
 * @author 996kid
 * @desription
 * @date 2020/1/3
 */
public class GuiceDemoModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DbEntrance.class).to(MysqlEntrance.class);
    }
}
