package net.revcn.domain;

import java.util.Date;
import org.springframework.stereotype.Component;
import net.revcn.model.MetricType;

@Component
public class Metric {

    private Long metricId;

    private MetricType metricType;

    private Date timestamp;

    private Double value;

    private Long deleted;


    public Long getMetricId() {
        return this.metricId;
    }

    public MetricType getMetricType() {
        return this.metricType;
    }

    public void setMetricType(MetricType metricType) {
        this.metricType = metricType;
    }

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
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
