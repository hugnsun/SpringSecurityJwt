package com.TokenDemo.hugh.mapper;

import com.TokenDemo.hugh.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hughs
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
