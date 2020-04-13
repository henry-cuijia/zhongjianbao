package com.zhongjianbaoapi.entity;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author HENRYC
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FaUser implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 组别ID
     */
    private Integer groupId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码盐
     */
    private String salt;

    /**
     * 电子邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 等级
     */
    private Boolean level;

    /**
     * 性别
     */
    private Boolean gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 格言
     */
    private String bio;

    /**
     * 余额
     */
    private BigDecimal money;

    /**
     * 积分
     */
    private Integer score;

    /**
     * 连续登录天数
     */
    private Integer successions;

    /**
     * 最大连续登录天数
     */
    private Integer maxsuccessions;

    /**
     * 上次登录时间
     */
    private Integer prevtime;

    /**
     * 登录时间
     */
    private Integer logintime;

    /**
     * 登录IP
     */
    private String loginip;

    /**
     * 失败次数
     */
    private Boolean loginfailure;

    /**
     * 加入IP
     */
    private String joinip;

    /**
     * 加入时间
     */
    private Integer jointime;

    /**
     * 创建时间
     */
    private long createtime;

    /**
     * 更新时间
     */
    private long updatetime;

    /**
     * Token
     */
    private String token;

    /**
     * 状态 默认1
     */
    private String status;

    /**
     * 验证
     */
    private String verification;

    /**
     * 姓名
     */
    private String name;

    /**
     * 身份证号
     */
    private String idCard;

    /**
     * 家乡-省
     */
    private String areaProvince;

    /**
     * 家乡-市
     */
    private String areaCity;

    /**
     * 家乡-县
     */
    private String areaCounty;

    /**
     * 民族
     */
    private String minzu;

    /**
     * 工龄
     */
    private Integer gongling;

    /**
     * 突击队:1=是
     */
    private Integer tjd;

    /**
     * 品牌班组
     */
    private Integer ppbz;

    /**
     * 优质工人
     */
    private Integer yzgr;

    /**
     * 电话数
     */
    private Integer telCount;

    /**
     * 突击队失效日期
     */
    private DateTime tjdLoseDate;

    /**
     * 品牌班组失效日期
     */
    private DateTime ppbzLoseDate;

    /**
     * 优质工人失效日期
     */
    private DateTime yzgrLoseDate;

    /**
     * 工程类别
     */
    private String gclb;

    /**
     * 工种
     */
    private String gongzhong;

    /**
     * 队伍人数
     */
    private Integer dwrs;

    /**
     * 规模:1=个人,2=队伍,3=公司
     */
    private Integer scale;

    /**
     * 期望工作地-省
     */
    private String area2Province;

    /**
     * 期望工作地-市
     */
    private String area2City;

    /**
     * 工作状态:1=未开工正在找工作,2=已开工也在找工作,3=暂时不需要找工作
     */
    private Integer status2;

    /**
     * 计薪方式:1=日薪,2=月薪
     */
    private Integer salaryMethod;

    /**
     * 薪资单位
     */
    private String salaryUnit;

    /**
     * 最小薪资
     */
    private Integer salaryMin;

    /**
     * 最大薪资
     */
    private Integer salaryMax;

    /**
     * 首次登陆 默认1 不是首次为2
     */
    private Integer firstLogin;

    /**
     * 企业统一社会信用代码
     */
    private String creditCode;

    /**
     * 企业名称_中文
     */
    private String enterpriseName;

    /**
     * 企业法人名称
     */
    private String legalPerson;

    /**
     * 是否企业实名1=否2=是
     */
    private Integer isReal;


}
