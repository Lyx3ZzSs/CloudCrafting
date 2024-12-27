package com.sprixin.cloud.convert;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

/**
 * 类型转换
 * @author yuanxin.li
 */
public class MysqlTypeConvertCustom extends MySqlTypeConvert implements ITypeConvert {
    @Override
    public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
        String fieldTypeLowerCase = fieldType.toLowerCase();
        if (fieldTypeLowerCase.equals("tinyint(1)")) {
            return DbColumnType.BOOLEAN;
        }
        return super.processTypeConvert(config, fieldType);
    }
}
