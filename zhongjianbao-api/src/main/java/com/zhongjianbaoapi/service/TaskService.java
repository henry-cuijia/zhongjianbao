package com.zhongjianbaoapi.service;

import org.springframework.stereotype.Service;

@Service
public interface TaskService {

    // X小时确认收货
    public void updateStatus();

    // X小时送达
    public void updateSD();


    // X小时接单未装货
    public void Zgoods();


    // 骑手X小时未接单

    public void JOrder();


    // 管理员X时未分配骑手
    public void FPOrder();
}
