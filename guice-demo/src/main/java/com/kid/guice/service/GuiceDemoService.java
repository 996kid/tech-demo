package com.kid.guice.service;

import com.google.inject.Inject;
import com.kid.guice.db.DbEntrance;

/**
 * @author 996kid
 * @desription
 * @date 2020/1/3
 */
public class GuiceDemoService {

    private final DbEntrance dbEntrance;


    @Inject
    public GuiceDemoService(DbEntrance dbEntrance) {
        this.dbEntrance = dbEntrance;
    }

    public void test() {
        System.out.println(dbEntrance.select());
    }}
