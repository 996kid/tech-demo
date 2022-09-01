package com.l3vi.testTransactional.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author 996kid@gmail.com
 * @Description UserMapper
 * @Date 2022/8/10 14:44
 */
public interface UserMapper {

    @Insert("insert into user values (#{id}, #{name})")
    void insert(@Param("id") String id, @Param("name") String name);
}
