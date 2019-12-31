package com.eastwood.mybatis.transaction.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;
import java.util.Map;

@Mapper
public interface TestMapper {

    @Select("exec Pagination #{sql,mode=IN,jdbcType=VARCHAR},"
            + "#{pageSize,mode=IN,jdbcType=VARCHAR}," +
            "#{pageNum,mode=IN,jdbcType=VARCHAR}," +
            "#{recordCount,mode=OUT,jdbcType=VARCHAR}")
    @Options(statementType= StatementType.CALLABLE)
    List<OrganizationEntity> call(@Param("sql")String sql,
                                  @Param("pageSize")String pageSize,
                                  @Param("pageNum") String pageNum,
                                  @Param("recordCount") String recordCount);

    List<OrganizationEntity> call1(Map param);

    @Select("call Pagination (#{sql,mode=IN,jdbcType=VARCHAR},"
            + "#{pageSize,mode=IN,jdbcType=VARCHAR}," +
            "#{pageNum,mode=IN,jdbcType=VARCHAR}" +
            ")")
    @Options(statementType= StatementType.CALLABLE)
    List<OrganizationEntity> call2(@Param("sql")String sql,
                                  @Param("pageSize")String pageSize,
                                  @Param("pageNum") String pageNum,
                                  @Param("recordCount") String recordCount);


}
