package com.zhongjianbaoapi.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhongjianbaoapi.entity.FaRecordwork;
import com.zhongjianbaoapi.entity.FaUser;

import java.io.Serializable;

/**
 * <p>
 * 记工记账 服务类
 * </p>
 *
 * @author HENRYC
 * @since 2020-04-10
 */
public interface FaRecordworkService extends IService<FaRecordwork> {

    // 根据id删除 物理删除
    boolean removeUpdateFaRecordworkById(Serializable id);
}
