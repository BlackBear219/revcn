package net.revcn.service;


import java.time.Instant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.revcn.dao.MetricDao;

import net.revcn.domain.Metric;
import net.revcn.model.MetricType;

@Service
public class MetricService {

    @Autowired
    private MetricDao metricDao;

    public int saveMetric(MetricType metricType, Instant timestamp, Double value) {
        var metric = new Metric(metricType, timestamp, value);
        return this.metricDao.insert(metric);
    }
}
