package com.yihuobao.yhb.mapper;

import com.yihuobao.yhb.entity.po.Merchant;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2025-07-17
 */
public interface MerchantMapper extends BaseMapper<Merchant> {
    @Select("SELECT * FROM user WHERE registrationEmail = #{registrationEmail}")
    Merchant selectByRegistrationEmail(String registrationEmail);
}
