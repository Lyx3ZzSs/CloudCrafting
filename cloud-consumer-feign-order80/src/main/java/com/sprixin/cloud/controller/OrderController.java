package com.sprixin.controller;

import cn.hutool.core.date.DateUtil;
import com.sprixin.cloud.apis.PayFeignApi;
import com.sprixin.entities.PayDTO;
import com.sprixin.resp.ResultData;
import com.sprixin.resp.ReturnCodeEnum;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class OrderController {

//    private final String PaymentSrv_URL = "http://localhost:8001";//先写死，硬编码

   // private final String PaymentSrv_URL = "http://cloud-payment-service";//服务注册中心上的微服务名称
    @Resource
    private PayFeignApi payFeignApi;


    @PostMapping("/feign/pay/add")
    public ResultData add(@RequestBody PayDTO payDTO) {
        return payFeignApi.addPay(payDTO);
    }

    @GetMapping("/feign/pay/get/{id}")
    public ResultData get(@PathVariable("id") Integer id) {
        System.out.println("========支付微服务远程调用，按照id查询订单支付流水信息");
        ResultData resultData = null;
        try {
            System.out.println("========调用开始:" + DateUtil.now());
            resultData = payFeignApi.getPayInfo(id);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("========调用结束:" + DateUtil.now());
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(),e.getMessage());
        }
        return resultData;
    }

    @GetMapping("/feign/pay/mylb")
    public String mylb() {
        return payFeignApi.mylb();
    }

}
