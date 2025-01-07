package com.sprixin.controller;

import com.sprixin.apis.PayFeignApi;
import com.sprixin.entities.PayDTO;
import com.sprixin.resp.ResultData;
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
        return payFeignApi.getPayInfo(id);
    }

    @GetMapping("/feign/pay/mylb")
    public String mylb() {
        return payFeignApi.mylb();
    }

}
