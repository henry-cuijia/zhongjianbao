package com.zhongjianbaoapi.service.impl;

//import com.zhongjianbaoapi.dao.OrCgrUserDao;
//import com.zhongjianbaoapi.dao.OrOrderDao;
//import com.zhongjianbaoapi.dao.OrTimeSettingDao;
//import com.zhongjianbaoapi.entity.OrCgrUser;
//import com.zhongjianbaoapi.entity.OrOrder;
//import com.zhongjianbaoapi.entity.OrTimeSetting;
import com.zhongjianbaoapi.service.TaskService;
import com.zhongjianbaoapi.utils.JpushClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TaskServiceImpl implements TaskService {

//    @Autowired
//    private OrOrderDao orOrderDao;
//
//    @Autowired
//    private OrTimeSettingDao orTimeSettingDao;
//
//    @Autowired
//    private OrCgrUserDao orCgrUserDao;
//
//    //日志
//    private static Logger logger = LoggerFactory.getLogger(OrCgrUserServiceImpl.class);
//
    // 确认收货
    @Override
    public void updateStatus() {
//        logger.info(""+new Date().getTime());
//        //查询状态为送达/完成的未收货订单
//        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//      List<OrTimeSetting>  Timeslist = orTimeSettingDao.findAll();
//      Double time = Timeslist.get(0).getUsertime();
//      Long Millisecond = new Double(time*60*60*1000).longValue();
//        List<OrOrder> list = orOrderDao.findOrder();
//        if (list.size()>0){
//            for (OrOrder orOrder:list){
//              Long time1 =  orOrder.getSdTime().getTime()+Millisecond;
//                if(new Date().getTime()>time1){
//                    // 更新状态
//                    OrOrder orOrder2 = orOrderDao.selectByOrderId(orOrder.getOrderId());
//                    OrOrder orOrder1 = new OrOrder();
//                    orOrder1.setOrderId(orOrder.getOrderId());
//                    orOrder1.setIsHarvest(1);
//                    orOrder1.setShTime(new Date());
//                    if(orOrder2.getIsPay()==1 && orOrder2.getIsComment() ==1){
//                        orOrder1.setStatus("5");
//                        orOrder1.setWcTime(new Date());
//                    }
//                  int result =   orOrderDao.update(orOrder1);
//                    // 查询下单人设备号 orOrder.getOrderId()
//                    OrCgrUser orCgrUser = orCgrUserDao.findByOrderId(orOrder.getOrderId());
//                    // 推送
//                    Map<String,String> map = new HashMap<String,String>();
//                    map.put("extra", "zdsh");
//         /*   String state = JpushClientUtil.sendToRegistrationId("equipment" + loginEquip ,"下线通知","下线通知",
//                    "您的账号于"+ DateUtils.format(new Date(),DateUtils.DATE_TIME_PATTERN ) + "在另一台设备登录," +
//                            "若非本人操作，请及时修改密码", map);*/
//
//                    String state =  JpushClientUtil.sendToRegistrationIdMsg("equipment"+orCgrUser.getEquipment(),"自动收货","自动收货","自动收货" ,map);
//                    logger.info("result=="+result+"====================收获成功");
//                }
//            }
//        }
    }
//
//
    @Override
    public void updateSD() {
//        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        // 查询带配送的订单
//        Map<String, Object> paramMap = new ConcurrentHashMap<>();
//        paramMap.put("status",1);
//        List<OrOrder> list =   orOrderDao.find(paramMap);
//        if (list.size()>0){
//            for (OrOrder orOrder:list){
//                Long time1 =  orOrder.getPsTime().getTime()+60*60*1000;     // 推迟1小时的时间
//                if(new Date().getTime()>time1){
//                  /*  // 更新状态
//                    OrOrder orOrder1 = new OrOrder();
//                    orOrder1.setOrderId();
//                    orOrder.set
//                    orOrderDao.update(orOrder1);*/
//                    logger.info("result=="+"====================收获成功");
//                }
//            }
//        }
//
    }
//
//
    // 骑手接单 未装货
    @Override
    public void Zgoods() {
//       // SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        // 查询带配送的订单
//        Map<String, Object> paramMap = new ConcurrentHashMap<>();
//        paramMap.put("status",0);
//        paramMap.put("earlyWarning",0);
//        List<OrOrder> list =   orOrderDao.find(paramMap);
//        List<OrTimeSetting>  Timeslist = orTimeSettingDao.findAll();
//        Double time = Timeslist.get(0).getQsyjtime();
//        Long Millisecond = new Double(time*60*60*1000).longValue();
//        if (list.size()>0){
//            for (OrOrder orOrder:list){
//                Long time1 =  orOrder.getJdTime().getTime()+Millisecond;     // 推迟1小时的时间
//                if(new Date().getTime()>time1){
//                    // 更新状态
//                    OrOrder orOrder1 = new OrOrder();
//                    orOrder1.setOrderId(orOrder.getOrderId());
//                    orOrder1.setEarlyWarning("1");
//                    orOrderDao.update(orOrder1);
//                    logger.info("接单未装货预警=====================");
//                }
//            }
//        }
//
    }
//
//
    // 骑手X小时未接单
    @Override
    public void JOrder() {
//      //  SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        // 查询带配送的订单
//        Map<String, Object> paramMap = new ConcurrentHashMap<>();
//        paramMap.put("status",-1);
//        paramMap.put("JDWarning",0);
//        List<OrOrder> list =   orOrderDao.find(paramMap);
//        List<OrTimeSetting>  Timeslist = orTimeSettingDao.findAll();
//        Double time = Timeslist.get(0).getQsyjtime();
//        Long Millisecond = new Double(time*60*60*1000).longValue();
//        if (list.size()>0){
//            for (OrOrder orOrder:list){
//                if(orOrder.getOperatingTime() != null){
//                    Long time1 =  orOrder.getOperatingTime().getTime()+Millisecond;     // 推迟1小时的时间
//
//                    if(new Date().getTime()>time1){
//                        // 更新状态
//                        OrOrder orOrder1 = new OrOrder();
//                        orOrder1.setOrderId(orOrder.getOrderId());
//                        orOrder1.setJDWarning("1");
//                        orOrderDao.update(orOrder1);
//                        logger.info("未接单预警=========================");
//                    }
//                }
//
//            }
//        }
    }
//
    // 管理员X时未分配骑手
    @Override
    public void FPOrder() {
//        Map<String, Object> paramMap = new ConcurrentHashMap<>();
//        paramMap.put("status",-2);
//        paramMap.put("FPWarning",0);
//        List<OrOrder> list =   orOrderDao.find(paramMap);
//        List<OrTimeSetting>  Timeslist = orTimeSettingDao.findAll();
//        Double time = Timeslist.get(0).getGlywfqsyjtime();
//        Long Millisecond = new Double(time*60*60*1000).longValue();
//        if (list.size()>0){
//            for (OrOrder orOrder:list){
//                Long time1 =  orOrder.getCreateTime().getTime()+Millisecond;     // 推迟1小时的时间
//                if(new Date().getTime()>time1){
//                    // 更新状态
//                    OrOrder orOrder1 = new OrOrder();
//                    orOrder1.setOrderId(orOrder.getOrderId());
//                    orOrder1.setFPWarning("1");
//                    orOrderDao.update(orOrder1);
//                    logger.info("未分配单预警========================");
//                }
//            }
//        }
    }
}
