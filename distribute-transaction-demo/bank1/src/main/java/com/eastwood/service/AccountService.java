package com.eastwood.service;

import com.eastwood.dao.AccountMapper;
import com.eastwood.feigh.Bank2Client;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName com.eastwood.service.AccountService
 * @Description com.eastwood.service.AccountService
 * @Author 996kid
 * @Date 2020/5/25 17:53
 */
@Service
@Slf4j
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private Bank2Client bank2Client;

    @GlobalTransactional
    public void decrease(String id,double amount) {
        log.info(">>>>>>>>>>>>> global XID: {}", RootContext.getXID());
        accountMapper.updateAccountBalance(id, amount);
        //调用bank2服务
        bank2Client.add("2", amount);
    }
}
