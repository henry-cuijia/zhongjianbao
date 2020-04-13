package com.zhongjianbaoapi.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhongjianbaoapi.entity.FaRecordwork;
import com.zhongjianbaoapi.entity.FaUser;
import com.zhongjianbaoapi.service.FaRecordworkService;
import com.zhongjianbaoapi.utils.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

/**
 * <p>
 * 记工记账 前端控制器
 * </p>
 *
 * @author HENRYC
 * @since 2020-04-10
 */
@RestController
@RequestMapping("/API/recordwork")
@Api(value = "记工记账", tags = {"记工记账"})
public class FaRecordworkController {

    @Autowired
    private FaRecordworkService faRecordworkService;

    //根据id 查询
    @ApiOperation(value = "根据id查询信息")
    @GetMapping("/get")
    public R getFaRecordworkById(@RequestParam Integer id) {
        try{
            return R.ok(200,"查询成功",faRecordworkService.getById(id));
        }catch (Exception e) {
            e.printStackTrace();
            return R.error(1001, "查询失败！");
        }
    }

    // 根据条件查询
    @ApiOperation(value = "根据条件查询信息")
    @GetMapping("/getObj")
    public R getObj(@RequestParam String mobile) {
        try{
            QueryWrapper<FaRecordwork> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("mobile",mobile);
            return R.ok(200,"查询成功",faRecordworkService.getOne(queryWrapper));
        }catch (Exception e) {
            e.printStackTrace();
            return R.error(1001, "查询失败！");
        }
    }

    // 根据id更新 实体类中必须传id
    @ApiOperation(value = "根据id更新 实体类中必须传id")
    @PostMapping("/update")
    public R updateFaRecordworkById(@RequestBody FaRecordwork entity) {
        try{
            return R.ok(200,"更新成功",faRecordworkService.updateById(entity));
        }catch (Exception e) {
            e.printStackTrace();
            return R.error(1001, "更新失败！");
        }
    }

    // 根据id删除 物理删除
    @ApiOperation(value = "根据id物理删除信息")
    @PostMapping("/del")
    public R removeFaRecordworkById(@RequestParam Integer id) {
        try{
            return R.ok(200,"删除成功",faRecordworkService.removeById(id));
        }catch (Exception e) {
            e.printStackTrace();
            return R.error(1001, "删除失败！");
        }
    }

    // 根据id删除 逻辑删除
    @ApiOperation(value = "根据id逻辑删除信息")
    @PostMapping("/delupdate")
    public R removeUpdateFaRecordworkById(@RequestParam Integer id) {
        try{

            FaRecordwork faRecordwork = faRecordworkService.getById(id);
            faRecordwork.setDelStatus(2);
            return R.ok(200,"删除成功",faRecordworkService.updateById(faRecordwork));
        }catch (Exception e) {
            e.printStackTrace();
            return R.error(1001, "删除失败！");
        }
    }

    // 保存新数据
    @ApiOperation(value = "添加新信息")
    @PostMapping("/add")
    public R saveObj(@RequestBody FaRecordwork entity) {
        try{
            return R.ok(200,"添加新信息成功",faRecordworkService.save(entity));
        }catch (Exception e) {
            e.printStackTrace();
            return R.error(1001, "添加新信息失败！");
        }
    }


}

