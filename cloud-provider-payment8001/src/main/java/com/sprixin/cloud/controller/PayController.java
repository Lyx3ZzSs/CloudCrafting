package com.sprixin.cloud.controller;

import com.sprixin.cloud.dto.PayDTO;
import com.sprixin.cloud.entity.Pay;
import com.sprixin.cloud.service.PayService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

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
@Slf4j
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping("/add")
    public Boolean addPay(@RequestBody Pay pay) {
        return payService.save(pay);
    }

    @DeleteMapping("/del/{id}")
    public Boolean deletePay(@PathVariable("id") Integer id) {
        return payService.removeById(id);
    }

    @PutMapping("/update")
    public Boolean updatePay(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO,pay);
        return payService.updateById(pay);
    }

    @GetMapping("/get/{id}")
    public Pay getById(@PathVariable("id") Integer id) {
        return payService.getById(id);
    }
}
