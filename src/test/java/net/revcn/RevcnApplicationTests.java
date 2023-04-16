package net.revcn;

import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.LinkedMultiValueMap;

import net.revcn.client.HouseClient;
import net.revcn.model.MetricType;
import net.revcn.schedule.HouseSchedule;
import net.revcn.service.MetricService;

@SpringBootTest
@EnableScheduling
class RevcnApplicationTests {

	@Autowired
	private HouseSchedule houseSchedule;

	@Test
	void TestGetTotalHouse() {
		this.houseSchedule.checkHouseCount();
	}

}
