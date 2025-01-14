package com.sprixin.cloud.controller;

import com.sprixin.cloud.apis.PayFeignApi;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderCircuitController {
    @Resource
    private PayFeignApi payFeignApi;

    @GetMapping("/feign/pay/circuit/{id}")
    @CircuitBreaker(name = "cloud-payment-service",fallbackMethod = "myCircuitFallback")
    public String myCircuitBreaker(@PathVariable("id")Integer id) {
        return payFeignApi.myCircuit(id);
    }

    //myCircuitFallback就是服务降级后的兜底方法
    public String myCircuitFallback(Integer id,Throwable t) {
        return "myCircuitFallback,系统繁忙，请稍后再试(*^▽^*)";
    }

    @GetMapping("/feign/pay/bulkhead/{id}")
    @Bulkhead(name = "cloud-payment-service",fallbackMethod = "myBulkheadFallback",type = Bulkhead.Type.THREADPOOL)
    public String myBulkhead(@PathVariable("id")Integer id) {
        return payFeignApi.myBulkhead(id);
    }

    public String myBulkheadFallback(Throwable t) {
        return "myBulkheadFallback,隔板超出最大数量限制，系统繁忙，请稍后再试(*^▽^*)";
    }
}
