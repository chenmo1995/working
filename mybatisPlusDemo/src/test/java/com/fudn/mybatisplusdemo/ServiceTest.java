package com.fudn.mybatisplusdemo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fudn.mybatisplusdemo.module.base.pojo.entity.MpUserPojo;
import com.fudn.mybatisplusdemo.module.base.service.MpUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 *  1.Service层更加易用，对Mapper做了一层封装
 *
 *  2.Service层的命名方式和Mapper不同
 *  select --> get
 *  insert --> save
 *  delete --> remove
 *  update --> update
 *
 *  3.支持了批量操作，而且可以指定一次操作多少个，比如 saveBatch(Collection<T> entityList, int batchSize);
 *
 *  4.对于增删改，返回值统一为boolean，而不是int（修改行数）
 *
 *  5.getOne(Wrapper, boolean)与BaseMapper的selectOne(Wrapper)不同，如果传false不会抛异常，有多个值则list.get(0)
 *    T getOne(Wrapper<T> queryWrapper, boolean throwEx);
 *
 *  6.Service层链式调用更顺手
 * @author fdn
 * @since 2021-08-26 17:04
 */
@SpringBootTest
public class ServiceTest {
    @Autowired
    private MpUserService userService;

    @Test
    public void testService11(){
        // 条件查询 方式1 lambdaQuery，用DO的字段
        List<MpUserPojo> queryList1 = userService.lambdaQuery().eq(MpUserPojo::getName, "修改后的值")
                .select(MpUserPojo::getName, MpUserPojo::getAge)
                .list();
        for (MpUserPojo mpUserPojo : queryList1) {
            System.out.println("*****"+mpUserPojo);
        }
    }

    @Test
    public void testServiceSave() {
        // ---------- 增 -----------
        // 插入
        MpUserPojo userPojo = new MpUserPojo();
        userPojo.setName("test Service111");
        userPojo.setAge(18);
        userService.save(userPojo);

        userPojo.setId(null);

        // 批量插入，即使不传入batchSize，默认也是1000条。比如实际由1w条，内部会按每次1000条批量插入
        boolean save = userService.saveBatch(Collections.singletonList(userPojo), 1000);
    }

    /**
     * 特别注意，虽然Wrapper的条件设置为null不影响，但Wrapper本身不设置任何条件还是会触发全表更新
     */
    @Test
    public void testServiceUpdate() {
        // ---------- 改 LambdaWrapper和普通Wrapper的唯一区别是 一个用POJO的字段，一个用数据库的字段表示条件-----------
        // 根据id更新
        MpUserPojo updateUser = new MpUserPojo();
        updateUser.setId(1L);
        updateUser.setName("bravo2020");
        userService.updateById(updateUser);

        // 条件更新 方式1 lambdaUpdate，用DO的字段
        boolean update1 = userService.lambdaUpdate().eq(MpUserPojo::getName, "bravo").set(MpUserPojo::getAge, 23).update();

        // 条件更新 方式2 普通update，用数据库字段
        boolean update2 = userService.update().eq("name", "bravo").set("age", 18).update();

        // 条件更新 方式3 传入LambdaUpdateWrapper，用DO的字段
        boolean update3 = userService.update(new LambdaUpdateWrapper<MpUserPojo>().eq(MpUserPojo::getName, "bravo").set(MpUserPojo::getAge, 18));

        // 条件更新 方式4 传入UpdateWrapper，用数据库字段
        boolean update4 = userService.update(new UpdateWrapper<MpUserPojo>().eq("name", "bravo").set("age", 18));

        // 条件更新 方式5 传入Entity表示更新的字段，QueryWrapper表示条件
        boolean bravo5 = userService.update(updateUser, new QueryWrapper<MpUserPojo>().lambda().eq(MpUserPojo::getName, "bravo"));

        // 批量更新。如果要根据条件批量更新还是自己写吧，注意SQL。
        boolean update = userService.updateBatchById(Collections.singletonList(updateUser), 1000);
    }

    @Test
    public void testServiceGet() {
        // ---------- 查 -----------
        // 条件查询 方式1 lambdaQuery，用DO的字段
        List<MpUserPojo> queryList1 = userService.lambdaQuery().eq(MpUserPojo::getName, "bravo")
                .select(MpUserPojo::getName, MpUserPojo::getAge)
                .list();

        // 条件查询 方式2 普通Query，用数据库字段
        List<MpUserPojo> queryList2 = userService.query().ge("age", 19).select("name", "age").list();

        // 条件查询 方式3 传入LambdaQueryWrapper，用DO的字段
        List<MpUserPojo> queryList3 = userService.list(new LambdaQueryWrapper<MpUserPojo>().eq(MpUserPojo::getName, "bravo"));

        // 条件查询 方式4 传入QueryWrapper，用数据库字段
        List<MpUserPojo> queryList4 = userService.list(new QueryWrapper<MpUserPojo>().eq("name", "bravo"));

        // getOne条件查询，有个重载方法 getOne(Wrapper<T> queryWrapper, boolean throwEx);
        MpUserPojo getOne = userService.getOne(Wrappers.<MpUserPojo>lambdaQuery().eq(MpUserPojo::getName, "bravo"));

        // 批量查询
        List<MpUserPojo> listBatch = userService.listByIds(Arrays.asList(1L, 2L, 3L));

        // 分页
        Page<MpUserPojo> page = new Page<>();
        page.setPages(1);
        page.setSize(2);
        Page<MpUserPojo> pageList = userService.page(page, new QueryWrapper<MpUserPojo>().lambda().eq(MpUserPojo::getName, "bravo"));

        // count
        int count = userService.count(new LambdaQueryWrapper<MpUserPojo>().eq(MpUserPojo::getName, "bravo"));
    }

    /**
     * 特别注意，虽然Wrapper的条件设置为null不影响，但Wrapper本身不设置任何条件还是会触发全表删除
     */
    @Test
    public void testServiceRemove() {
        // ---------- 删 和通用Mapper不同的是，@TableLogic对批量删除也是起作用的 -----------
        // 根据id删除
        userService.removeById(1L);
        // 条件删除，特别注意，Wrapper不设置任何条件还是会触发全表删除
        userService.remove(Wrappers.<MpUserPojo>lambdaQuery());
        // 根据ids批量删除 UPDATE mp_user SET deleted=1 WHERE id IN ( 1 , 2 , 3 ) AND deleted=0;
        boolean remove = userService.removeByIds(Arrays.asList(1L, 2L, 3L));
    }


}
