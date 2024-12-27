package com.sprixin.cloud.controller;

import com.sprixin.cloud.dto.PayDTO;
import com.sprixin.cloud.entity.Pay;
import com.sprixin.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/add")
    @Operation(summary = "新增",description = "新增支付流水方法，JSON串做参数")
    public Boolean addPay(@RequestBody Pay pay) {
        return payService.save(pay);
    }

    @DeleteMapping("/del/{id}")
    @Operation(summary = "删除",description = "删除支付流水方法")
    public Boolean deletePay(@PathVariable("id") Integer id) {
        return payService.removeById(id);
    }

    @PutMapping("/update")
    @Operation(summary = "修改",description = "修改支付流水方法")
    public Boolean updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);
        return payService.updateById(pay);
    }

    @GetMapping("/get/{id}")
    @Operation(summary = "按照ID查流水",description = "查询支付流水方法")
    public Pay getById(@PathVariable("id") Integer id) {
        return payService.getById(id);
    }

    @GetMapping("/get/all")
    @Operation(summary = "查询所有流水",description = "查询所有支付流水方法")
    public List<Pay> getAll() {
        return payService.list();
    }
}
