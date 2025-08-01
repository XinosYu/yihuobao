package com.yihuobao.yhb.mapper;

import com.yihuobao.yhb.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin
 * @since 2025-07-17
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    void insertUser(User user);
    User selectByPhonenumberAndPassword(String phonenumber, String password);
    @Select("SELECT * FROM user WHERE phoneNumber = #{phoneNumber}")
    User selectByPhoneNumber(String phoneNumber);
}
