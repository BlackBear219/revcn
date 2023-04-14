package net.revcn.dao;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.revcn.domain.User;

@Mapper
public interface UserDao extends BaseMapper<User> {
    
}
