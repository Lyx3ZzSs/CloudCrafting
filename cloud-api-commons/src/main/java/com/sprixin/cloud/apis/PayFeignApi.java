package com.sprixin.cloud.apis;

import com.sprixin.entities.PayDTO;
import com.sprixin.resp.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient("cloud-payment-service")
public interface PayFeignApi {

    /**
     * 新增一条支付相关流水记录
     * @param payDTO payDto
     * @return 结果
     */
    @PostMapping("/pay/add")
    public ResultData<Boolean> addPay(@RequestBody PayDTO payDTO);

    /**
     * 按照主键记录查询支付流水信息
     * @param id id
     * @return 结果
     */
    @GetMapping("/pay/get/{id}")
    public ResultData getPayInfo(@PathVariable("id") Integer id);

    /**
     * openfeign天然支持负载均衡演示
     * @return 结果
     */
    @GetMapping("/pay/get/info")
    public String mylb();

    /**
     * Resilience4j CircuitBreaker的例子
     * @param id id
     * @return 结果
     */
    @GetMapping("/pay/circuit/{id}")
    public String myCircuit(@PathVariable("id")Integer id);
}
