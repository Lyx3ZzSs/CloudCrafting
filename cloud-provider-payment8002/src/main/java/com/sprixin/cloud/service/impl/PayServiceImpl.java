package com.sprixin.cloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sprixin.cloud.entities.Pay;
import com.sprixin.cloud.mapper.PayMapper;
import com.sprixin.cloud.service.PayService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 支付交易表 服务实现类
 * </p>
 *
 * @author yuanxin.li
 * @since 2024-12-27 07:42:19
 */
@Service
public class PayServiceImpl extends ServiceImpl<PayMapper, Pay> implements PayService {

}
