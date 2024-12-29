package com.sprixin.controller;

import com.sprixin.entities.PayDTO;
import com.sprixin.resp.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping
public class OrderController {

    private final String PaymentSrv_URL = "http://localhost:8001";//先写死，硬编码

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/pay/add")
    public ResultData add(PayDTO payDTO) {
        return restTemplate.postForObject(PaymentSrv_URL+"/pay/add",payDTO, ResultData.class);
    }

    @GetMapping("/consumer/pay/get/{id}")
    public ResultData get(@PathVariable("id") Integer id) {
        return restTemplate.getForObject(PaymentSrv_URL+ "/pay/get/" + id, ResultData.class);
    }


    @GetMapping("/consumer/pay/del/{id}")
    public ResultData del(@PathVariable("id") Integer id) {
        restTemplate.delete(PaymentSrv_URL+"/pay/del/" + id);
        return ResultData.success(null);
    }

    @GetMapping("/consumer/pay/update")
    public ResultData update(PayDTO payDTO) {
        restTemplate.put(PaymentSrv_URL+"/pay/update",payDTO);
        return ResultData.success(null);
    }
}
