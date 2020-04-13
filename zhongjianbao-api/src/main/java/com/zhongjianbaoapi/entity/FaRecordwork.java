package com.zhongjianbaoapi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 记工记账
 * </p>
 *
 * @author HENRYC
 * @since 2020-04-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FaRecordwork implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 类型:1=点工,2=包工记工天,3=包工记账
     */
    private Integer type;

    /**
     * 对象人id
     */
    private Integer objUid;

    /**
     * 对象人类型:1=工人,2=班组长
     */
    private Integer objType;

    /**
     * 日期
     */
    private String date;

    /**
     * 上班标准(几小时算1个工)
     */
    private BigDecimal workStandard;

    /**
     * 加班标准(几个小时算1个工)
     */
    private BigDecimal overtimeStandard;

    /**
     * 每天工资标准或预估
     */
    private BigDecimal wageEstimate;

    /**
     * 加班1小时费用(系统根据加班标准自动计算)
     */
    private BigDecimal overtimePay;

    /**
     * 上班时长
     */
    private BigDecimal workingHours;

    /**
     * 加班时长
     */
    private BigDecimal longOvertime;

    /**
     * 点工工钱
     */
    private BigDecimal wages;

    /**
     * 所在项目
     */
    @TableField("projectId")
    private Integer projectId;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 创建时间
     */
    private Date createtime;

    /**
     * 修改时间
     */
    private Date updatetime;

    /**
     * 拥有者
     */
    @TableField("ownerId")
    private Integer ownerId;

    /**
     * 拥有者身份:1=工人,2=班组长
     */
    private Integer identity;

    /**
     * 包工类型:1=承包,2=分包
     */
    private Integer contractorType;

    /**
     * 删除时间
     */
    private Date deleteTime;

    /**
     * 班组长姓名
     */
    private String teamName;

    /**
     * 工资标准   表standard
     */
//    @TableField("standardid")
//    @TableField(exist = true)
    private Integer standardId;

    /**
     * 借支金额
     */
    private Float borrowMoney;

    /**
     * 借支种类 1=工资2=生活费3=补贴4=奖励5=其他
     */
    private Integer borrowType;

    /**
     * 项目名称
     */
    private String projectname;

    /**
     * 1
     */
    private String uName;

    /**
     * 1
     */
    private String oName;

    /**
     * 是否删除 1未  2已删除
     */
    private Integer delStatus;

}
