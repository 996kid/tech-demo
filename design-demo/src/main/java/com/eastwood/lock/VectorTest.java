package com.eastwood.lock;

import java.util.Vector;

/**
 * @author yyh
 * @Description VectorTest
 * @Date 2020/8/14 13:58
 */
public class VectorTest {

    public static void main(String[] args) {

    }

    void addIfNotExist(Vector v,
                       Object o){
        if(!v.contains(o)) {
            v.add(o);
        }
    }
}
