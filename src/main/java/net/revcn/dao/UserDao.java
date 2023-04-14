package net.revcn.dao;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.revcn.domain.User;

@Mapper
public interface UserDao extends BaseMapper<User> {
    
}
