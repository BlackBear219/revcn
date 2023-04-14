package net.revcn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import net.revcn.service.MetricService;

@SpringBootTest
class RevcnApplicationTests {

	@Autowired
	private MetricService metricService;

	@Test
	void contextLoads() {
		var result = this.metricService.getHouseUrl();
		System.out.println(result);
	}

}
