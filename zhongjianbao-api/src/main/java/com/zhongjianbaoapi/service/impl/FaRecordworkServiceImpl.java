package com.zhongjianbaoapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import com.zhongjianbaoapi.dao.FaRecordworkMapper;
import com.zhongjianbaoapi.entity.FaRecordwork;
import com.zhongjianbaoapi.service.FaRecordworkService;


import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 记工记账 服务实现类
 * </p>
 *
 * @author HENRYC
 * @since 2020-04-10
 */
@Service
public class FaRecordworkServiceImpl extends ServiceImpl<FaRecordworkMapper, FaRecordwork> implements FaRecordworkService {

    //根据id 查询
    @Override
    public FaRecordwork getById(Serializable id) {
        return super.getById(id);
    }

    // 根据条件查询
    @Override
    public FaRecordwork getOne(Wrapper<FaRecordwork> queryWrapper) {
        return super.getOne(queryWrapper);
    }

    // 根据id更新 实体类中必须传id
    @Override
    public boolean updateById(FaRecordwork entity) {
        if(entity.getDelStatus() == 2){
            return false;
        }
        entity.setUpdatetime(new Date());
        return super.updateById(entity);
    }

    // 根据id删除 物理删除
    @Override
    public boolean removeById(Serializable id) {
        QueryWrapper<FaRecordwork> queryWrapper = new QueryWrapper<>();
        //添加条件
        queryWrapper.eq("id",id);
        queryWrapper.eq("delStatus",2);
        if (null != getOne(queryWrapper)){
            return false;
        }
        return super.updateById(getById(id));
    }

    // 根据id删除 物理删除
    @Override
    public boolean removeUpdateFaRecordworkById(Serializable id) {
        QueryWrapper<FaRecordwork> queryWrapper = new QueryWrapper<>();
        //添加条件
        queryWrapper.eq("id",id);
        queryWrapper.eq("delStatus",2);
        if (null != getOne(queryWrapper)){
            return false;
        }
        getById(id).setDelStatus(2);
        return super.updateById(getById(id));
    }

    // 保存新数据
    @Override
    public boolean save(FaRecordwork entity) {
        entity.setCreatetime(new Date());
        entity.setUpdatetime(new Date());
        return super.save(entity);
    }
}
