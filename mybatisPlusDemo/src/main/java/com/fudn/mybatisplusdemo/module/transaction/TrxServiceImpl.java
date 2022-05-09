package com.fudn.mybatisplusdemo.module.transaction;

import com.fudn.mybatisplusdemo.module.base.pojo.entity.MpUserPojo;
import com.fudn.mybatisplusdemo.module.base.service.MpUserService;
import com.fudn.mybatisplusdemo.module.transaction.entity.TUser;
import com.fudn.mybatisplusdemo.module.transaction.service.ITUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * @author fdn
 * @since 2022-03-25 15:23
 */
@Service
@Slf4j
public class TrxServiceImpl implements TrxService {

    @Autowired
    MpUserService mpUserService;

    @Autowired
    ITUserService userService;

    @Autowired
    TrxService trxService;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public void test() {
//        trxService.saveOrUpdate();
        try {
            trxService.saveOrUpdate();
        } catch (Exception e) {
            log.error("事务回滚", e);
            log.error(e.getMessage());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public void saveOrUpdate() {

        List<MpUserPojo> mpList = mpUserService.list();
        List<TUser> userList = userService.list();

        log.info("初始数据，mpList：{}", mpList);
        log.info("初始数据，userList：{}", userList);

        for (int i = 0; i < 2; i++) {
            mpUserService.changeUser(mpList.get(i));
            userService.changeUser(userList.get(i));
        }
        log.info("更新完成");
    }
}
