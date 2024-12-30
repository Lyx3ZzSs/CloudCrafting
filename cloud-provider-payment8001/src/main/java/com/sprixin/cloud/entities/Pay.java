package com.sprixin.cloud.entities;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
* <p>
* 支付交易表
* </p>
*
* @author yuanxin.li
* @since 2024-12-28 03:53:44
*/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_pay")
@Schema(title = "支付交易表Entity")
public class Pay {


    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("pay_no")
    @Schema(title = "支付流水号")
    private String payNo;

    @TableField("order_no")
    @Schema(title = "订单流水号")
    private String orderNo;

    @TableField("user_id")
    @Schema(title = "用户账号ID")
    private Integer userId;

    @TableField("amount")
    private BigDecimal amount;

    @TableField("deleted")
    @TableLogic(value = "0",delval = "1")
    private Byte deleted;

    @TableField("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;

    @TableField("update_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

}