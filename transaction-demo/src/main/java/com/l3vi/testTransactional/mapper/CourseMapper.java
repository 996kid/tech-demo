package com.l3vi.testTransactional.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author 996kid@gmail.com
 * @Description CourseMapper
 * @Date 2022/8/10 14:46
 */
public interface CourseMapper {

    @Insert("insert into course values (#{id}, #{name})")
    void insert(@Param("id") String id, @Param("name") String name);
}
