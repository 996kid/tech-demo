package com.eastwood.design.idgeneratorcase;

/**
 * @ClassName: IdGenerator
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/13 11:37
 */
public interface IdGenerator {

    String generate() throws IdGenerationFailureException;
}
