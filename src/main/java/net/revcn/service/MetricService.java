package net.revcn.service;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import jakarta.annotation.Resource;
import net.revcn.constant.KeyConstant;
import net.revcn.constant.UrlConstant;

@Service
public class MetricService {

    private String viewStateKey = "__VIEWSTATE";

    private String viewStateGeneratorKey = "__VIEWSTATEGENERATOR";

    private String eventValidationKey = "__EVENTVALIDATION";

    @Resource
    private RestTemplate restTemplate;

    public String getHouseUrl() {
        String locationUrl = UrlConstant.HOUSE_URL;
        var result = this.restTemplate.getForEntity(locationUrl, String.class);

        // Get the target location of POST request
        var location = result.getHeaders().getFirst(KeyConstant.LOCATION_KEY);
        var targetLocation = UrlConstant.HOUSE_HOST + location;

        // Get the payload
        var headers = new HttpHeaders();
        headers.add("Content-Type", "application/x-www-form-urlencoded");
        var bodyContent = result.getBody();
        var viewStateValue = this.getFirstResponseValue(viewStateKey, bodyContent);
        var viewStateGeneratorValue = this.getFirstResponseValue(viewStateGeneratorKey, bodyContent);
        var eventValidationValue = this.getFirstResponseValue(eventValidationKey, bodyContent);
        var payload = new LinkedMultiValueMap<String, Object>();
        var params = new HashMap<String, String>() {
            {
                put(viewStateGeneratorKey, viewStateGeneratorValue);
                put(viewStateKey, viewStateValue);
                put(eventValidationKey, eventValidationValue);
                put("ctl00$MainContent$txt_Pro", "双银国际金融城");
                put("ctl00$MainContent$ddl_qy", "RD003");
                put("ctl00$MainContent$bt_select", "查询");
                put("ctl00$MainContent$rb_HF_CODE", "-1");
                put("ctl00$MainContent$ddl_houseclass", "1");
            }
        };
        payload.add("data", new JSONObject(params));
        var httpEntity = new HttpEntity<>(payload, headers);
        var selectResult = restTemplate.postForObject(targetLocation, httpEntity, String.class);
        return selectResult;
    }

    private String getFirstResponseValue(String key, String str) {
        var flag = str.indexOf(key);
        var prefix = "value=\"";
        var suffix = "\"";
        var startIndex = str.indexOf(prefix, flag);
        if (startIndex == -1) {
            return null;
        }

        startIndex += prefix.length();
        var endIndex = str.indexOf(suffix, startIndex);
        if (endIndex == -1) {
            return null;
        }

        return str.substring(startIndex, endIndex);
    }
}
