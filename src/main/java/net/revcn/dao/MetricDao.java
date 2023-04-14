package net.revcn.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.revcn.domain.Metric;

@Mapper
@Component
public interface MetricDao extends BaseMapper<Metric> {
}
