package com.eastwood.design.idgeneratorcase;

/**
 * @ClassName: IdGenerationFailureException
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/13 11:36
 */
public class IdGenerationFailureException extends Exception {

    public IdGenerationFailureException(String message, Throwable cause) {
        super(message, cause);
    }
}
