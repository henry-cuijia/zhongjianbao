package com.zhongjianbaoapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhongjianbaoapi.config.token.TokenService;
import com.zhongjianbaoapi.dao.FaUserMapper;
import com.zhongjianbaoapi.entity.FaUser;
import com.zhongjianbaoapi.service.FaUserService;
import com.zhongjianbaoapi.utils.MD5Utils;
import com.zhongjianbaoapi.utils.R;
import com.zhongjianbaoapi.utils.RedisUtil;
import com.zhongjianbaoapi.utils.SmsUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author HENRYC
 * @since 2020-04-10
 */
@Service
public class FaUserServiceImpl extends ServiceImpl<FaUserMapper, FaUser> implements FaUserService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 注册 or 登陆
     * @param mobile
     * @param code
     * @return
     */
    @Override
    public R register(String mobile, String code) throws Exception {
        //String language =  commonService.getLanguage(equipment);
        if(StringUtils.isBlank(mobile)){
            return R.error("请输入手机号！");
        }
        if(StringUtils.isBlank(code)){
            return R.error("验证码不能为空！");
        }

        //判断用户是否存在
        FaUser accUser = new FaUser();
        accUser.setMobile(mobile);
        Map<String, String> map = new HashMap<>();
        map.put("mobile",mobile);
        // 创建QueryWrapper
        QueryWrapper<FaUser> queryWrapper = new QueryWrapper<>();
        //添加条件
        queryWrapper.eq("mobile",mobile);
        // 获取返回结果
        FaUser user = getOne(queryWrapper);
        System.out.println(user);
        System.out.println("1:" + queryWrapper.getSqlSegment());
        Object sms = "1111";
//        System.out.println( "sms:" +redisUtil.get("smscode_" + mobile));
//        Object sms = redisUtil.get("smscode_" + mobile);
        if(null != user){
            if(code.equals(sms.toString())){
                getToken(user);
                System.out.println("id:" + user.getId());
                FaUser user2 = getById(user.getId());
                System.out.println("2:" + queryWrapper.getSqlSegment());
                return R.ok(200,"登录成功！",user2);
            }
            return R.error(1401,"验证码不正确！");
        }
        //保存用户信息
        if(code.equals(sms.toString())){
            FaUser user3 = new FaUser();
            user3.setMobile(mobile);
            user3.setPassword(MD5Utils.encrypt("123456"));
            user3.setNickname(mobile);
            user3.setCreatetime(new Date().getTime());
            user3.setStatus("1");
            if(save(user3)){
                //添加条件
                queryWrapper.eq("mobile",mobile);
                // 获取返回结果
                System.out.println("3:" + queryWrapper.getSqlSegment());
                FaUser user4 = getOne(queryWrapper);
                System.out.println(user4.getMobile());
                getToken(user4);
                FaUser user5 = getById(user3.getId());
                System.out.println("4:" + queryWrapper.getSqlSegment());
                return R.ok(200,"注册并且登录成功！",user5);
            }
            return R.error(1401,"注册失败");
//		return R.error(1002,"注册失败");
		}else {
            return R.error(1401,"验证码不正确！");
        }
    }

    /**
     * 获取token
     * @param user
     * @return
     */
    public String getToken(FaUser user) {
        //封装token对象
        FaUser tuser = new FaUser();
        tuser.setId(user.getId());
        tuser.setMobile(user.getMobile());
        String userToken = tokenService.getToken(tuser);
        System.out.println("token:" + userToken);
        //保存用户token
        FaUser newuser = new FaUser();
        newuser.setId(user.getId());
        newuser.setToken(userToken);
        updateById(newuser);
        return userToken;
    }


    /**
     *发送验证码
     * @param mobile
     * @return
     */
    @Override
    public R setMessage(String mobile) {
//		String language =  commonService.getLanguage(equipment);
        if(StringUtils.isBlank(mobile)){
            return R.error("手机号不能为空");
        }

        String smscode = RandomStringUtils.randomNumeric(4);
        System.out.println("code:" + smscode);
        redisUtil.set("smscode_" + mobile, smscode, 5 * 60);
        String result = SmsUtils.SmsCode(mobile,"488666", smscode);
        if("".equals(result)){
            return R.error("发送失败");
        }
//		logger.info("验证码：" + smscode + "@@@@@@@@@@@@@返回信息：" + result);

        return R.ok("发送成功");
    }

}
