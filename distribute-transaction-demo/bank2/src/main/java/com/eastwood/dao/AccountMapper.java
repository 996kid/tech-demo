package com.eastwood.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @InterfaceName com.eastwood.dao.AccountMapper
 * @Description com.eastwood.dao.AccountMapper
 * @Author 996kid
 * @Date 2020/5/25 17:56
 */
public interface AccountMapper {

    //更新账户金额
    @Update("update account_info set account_balance = account_balance + #{amount} where account_no = #{accountNo}")
    int updateAccountBalance(@Param("accountNo") String accountNo, @Param("amount") double amount);

}
