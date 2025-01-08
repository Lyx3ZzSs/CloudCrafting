package com.sprixin.cloud.controller;

import com.sprixin.cloud.entities.Pay;
import com.sprixin.cloud.resp.ResultData;
import com.sprixin.cloud.service.PayService;
import com.sprixin.entities.PayDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 支付交易表 前端控制器
 * </p>
 *
 * @author yuanxin.li
 * @since 2024-12-27 07:42:19
 */
@RestController
@RequestMapping("/pay")
@Tag(name = "支付微服务模块",description = "支付CRUD")
@Slf4j
public class PayController {

    @Resource
    private PayService payService;

    @Value("${server.port}")
    private String port;

    @PostMapping("/add")
    @Operation(summary = "新增",description = "新增支付流水方法，JSON串做参数")
    public ResultData<Boolean> addPay(@RequestBody Pay pay) {
        return ResultData.success(payService.save(pay));
    }

    @DeleteMapping("/del/{id}")
    @Operation(summary = "删除",description = "删除支付流水方法")
    public ResultData<Boolean> deletePay(@PathVariable("id") Integer id) {
        return ResultData.success(payService.removeById(id));
    }

    @PutMapping("/update")
    @Operation(summary = "修改",description = "修改支付流水方法")
    public ResultData<Boolean> updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);
        return ResultData.success(payService.updateById(pay));
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "按照ID查流水",description = "查询支付流水方法")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) {
        //服务提供方业务处理，为了测试feign的超时时间控制
        try {
            TimeUnit.SECONDS.sleep(62);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping("/get/all")
    @Operation(summary = "查询所有流水",description = "查询所有支付流水方法")
    public ResultData<List<Pay>> getAll() {
        return ResultData.success(payService.list());
    }

    @GetMapping("/get/info")
    private String getInfoByConsul(@Value("${sprixin.info}") String sprixinInfo ) {
         return "sprixinInfo:" + sprixinInfo + "\t" + "port:" + port;
    }
}
