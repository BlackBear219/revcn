package net.revcn.schedule;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;

import net.revcn.client.HouseClient;
import net.revcn.model.MetricType;
import net.revcn.service.MetricService;

@Component
public class HouseSchedule {

    @Autowired
    private MetricService metricService;

    @Autowired
    private HouseClient houseClient;
    
    private LinkedMultiValueMap<String, String> basicValues = new LinkedMultiValueMap<String, String>() {
        {
            add("ctl00$MainContent$txt_Pro", "双银国际金融城");
            add("ctl00$MainContent$ddl_qy", "RD003");
            add("ctl00$MainContent$bt_select", "查询");
            add("ctl00$MainContent$ddl_houseclass", "1");
        }
    };

    @Scheduled(cron = "0 00 00 ? * *")
    public void checkHouseCount() {
        // Get the total house count
        var houseTotleCount = this.houseClient.getHouseCount(this.basicValues);
		if (houseTotleCount != null) {
			this.metricService.saveMetric(MetricType.HOUSE_TOTAL, Instant.MIN, houseTotleCount.doubleValue());
		}

        // Get the small house count
        var smallHouseValues = new LinkedMultiValueMap<String, String>() {
            {
                add("ctl00$MainContent$txt_Area1", "102");
                add("ctl00$MainContent$txt_Area2", "103");
            }
        };

		smallHouseValues.addAll(this.basicValues);
		var smallHouseCount = this.houseClient.getHouseCount(smallHouseValues);
		System.out.println(smallHouseCount);
		if (smallHouseCount != null) {
			this.metricService.saveMetric(MetricType.HOUSE_SMALL, Instant.now(), smallHouseCount.doubleValue());
		}
    }
}
