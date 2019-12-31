package com.eastwood.mybatis.transaction.mapper;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  sybase problem:
 *  SELECT INTO command not allowed within multi-statement transaction
 *  使用select into时不能开启事务
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class TestMapperTest {

    @Autowired
    private TestMapper testMapper;

    String sql1 = "select * from OM_STAFF";

    String sql = "select o.ORGID, o.NAME from OM_ORG o   inner join om_org_address_apply a on o.ORGID = a.ORG_CODE\n" +
            "  inner join OM_STAFF s on s.ORGID = o.ORGID  where s.STAFFID = '1001'  and a.IS_ADOPT = '0'  and o.NAME like '%%' group by o.ORGID union select o.ORGID, o.NAME from OM_ORG o\n" +
            "  inner join om_org_address_apply a on o.ORGID = a.ORG_CODE\n" +
            "  where o.PID = (select o.ORGID from OM_ORG o inner join OM_STAFF s on s.ORGID = o.ORGID  where s.STAFFID = '1001' )  and a.IS_ADOPT = '0' and o.NAME like '%%' union select o3.ORGID, o3.NAME from OM_ORG o3\n" +
            "  inner join om_org_address_apply a on o3.ORGID = a.ORG_CODE\n" +
            "  where o3.PID\n" +
            "  in ( select o.ORGID from OM_ORG o\n" +
            "  where o.PID = (select o.ORGID from OM_ORG o\n" +
            "  inner join OM_STAFF s on s.ORGID = o.ORGID  where s.STAFFID = '1001' ))  and a.IS_ADOPT = '0'and o3.NAME like '%%'";
    @Test
//    @Transactional
    public void test() {
        List<OrganizationEntity> organizationEntities = testMapper.call(sql,"10","1","");
        System.out.println(organizationEntities);
        log.info("end");
    }

    @Test
//    @Transactional
    public void test2() {
        Map map = new HashMap<>();
        map.put("sql", sql);
        map.put("pageSize", "10");
        map.put("pageNum", "1");
        map.put("recordCount", "");

        List<OrganizationEntity> list = testMapper.call1(map);
        System.out.println(list);
    }

    @Test
//    @Transactional
    public void test3() {
        List<OrganizationEntity> organizationEntities = testMapper.call2(sql1,"10","1","");
        System.out.println(organizationEntities);
    }
}