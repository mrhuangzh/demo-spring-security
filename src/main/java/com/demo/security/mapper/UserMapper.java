package com.demo.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.security.entity.po.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: huangzh
 * @Date: 2024/5/23 14:29
 **/
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
