package com.eastwood.multidatasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * @author 996kid@gmail.com
 * @Description SimpleDao
 * @Date 2022/5/26 15:04
 */
@Component
public class SimpleDao extends JdbcDaoSupport {

    public SimpleDao (DataSource dataSource) {
        this.setDataSource(dataSource);
    }

    public List<Item> getItems() {
        String query = "select name, price from item";
        return getJdbcTemplate().query(query, new RowMapper<Item>() {
            public Item mapRow(ResultSet rs, int row) throws SQLException {
                String name = rs.getString(1);
                double price = rs.getDouble(2);
                return new Item(name, price);
            }
        });
    }

}
