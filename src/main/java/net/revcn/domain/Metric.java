package net.revcn.domain;

import java.time.Instant;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import net.revcn.model.MetricType;

@Component
public class Metric {

    @TableId(type=IdType.ASSIGN_ID)
    private Long metricId;

    private MetricType metricType;

    private Instant snapshotTime;

    private Double value;

    private Long deleted;

    public Metric() {
        this.deleted = 0L;
    }

    public Metric(MetricType metricType, Instant snapshotTime, Double value) {
        this();
        this.metricType = metricType;
        this.snapshotTime = snapshotTime;
        this.value = value;
    }

    public Long getMetricId() {
        return this.metricId;
    }

    public MetricType getMetricType() {
        return this.metricType;
    }

    public void setMetricType(MetricType metricType) {
        this.metricType = metricType;
    }

    public Instant getTimestamp() {
        return this.snapshotTime;
    }

    public void setTimestamp(Instant snapshotTime) {
        this.snapshotTime = snapshotTime;
    }

    public Double getValue() {
        return this.value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getDeleted() {
        return this.deleted;
    }

    public void setDeleted(Long deleted) {
        this.deleted = deleted;
    }
}
