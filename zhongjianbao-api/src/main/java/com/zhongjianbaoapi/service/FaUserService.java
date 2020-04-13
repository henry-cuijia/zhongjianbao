package com.zhongjianbaoapi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhongjianbaoapi.entity.FaUser;
import com.zhongjianbaoapi.utils.R;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author HENRYC
 * @since 2020-04-10
 */
public interface FaUserService extends IService<FaUser> {

    R register(String mobile, String code) throws Exception;

    R setMessage(String mobile);

}
