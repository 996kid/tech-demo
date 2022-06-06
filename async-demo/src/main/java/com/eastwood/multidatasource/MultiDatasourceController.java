package com.eastwood.multidatasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 996kid@gmail.com
 * @Description MultiDatasourceController
 * @Date 2022/5/26 15:01
 */
@RestController
public class MultiDatasourceController {

    @Autowired
    private SimpleDao simpleDao;

    @GetMapping("/getInfo/{id}")
    public List<Item> getInfo(@PathVariable String id) {
        List<Item> list = simpleDao.getItems();
        return list;
    }
}
