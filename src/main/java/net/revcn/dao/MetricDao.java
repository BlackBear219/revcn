package net.revcn.dao;

import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import net.revcn.domain.Metric;

@Mapper
public interface MetricDao extends BaseMapper<Metric> {
}
