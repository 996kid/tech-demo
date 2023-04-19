package com.eastwood;

/**
 * @ClassName: Serializer
 * @Description: TODO
 * @Author: yyh
 * @Date: 2023/4/17 11:28
 */
public interface Serializer {

    <T> T deserialize(Class<T> clazz, byte[] bytes);

    <T> byte[] serialize(T object);
}
