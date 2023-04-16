package net.revcn.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import jakarta.annotation.Resource;
import net.revcn.constant.KeyConstant;
import net.revcn.constant.UrlConstant;

@Component
public class HouseClient {

    private LinkedMultiValueMap<String, String> BasicValues = new LinkedMultiValueMap<>() {
        {
            add(
                "__VIEWSTATEGENERATOR",
                "4A995636"
            );
            add(
                "__VIEWSTATE",
                "/wEPDwUKMTIyNzA0ODg3OA9kFgJmD2QWAgIDD2QWAgIDD2QWBAIBDxAPFgYeDURhdGFUZXh0RmllbGQFB1JEX05BTUUeDkRhdGFWYWx1ZUZpZWxkBQdSRF9DT0RFHgtfIURhdGFCb3VuZGdkEBUGDOW3peS4muWbreWMugnlkLTkuK3ljLoJ55u45Z+O5Yy6CemrmOaWsOWMugnlp5Hoi4/ljLoJ5ZC05rGf5Yy6FQYFUkQwMDIFUkQwMDMFUkQwMDQFUkQwMDUFUkQwMDEFUkQwMDgUKwMGZ2dnZ2dnZGQCCg88KwARAwAPFgIeFlBhZ2VHcmlkVmlldzFNYXhSZWNvcmQC6AdkARAWABYAFgAMFCsAAGQYAQUfY3RsMDAkTWFpbkNvbnRlbnQkUGFnZUdyaWRWaWV3MQ9nZOzua4M1UKjEPrNrLjS6bGoAYgpjvMDbP3pjShroKkhv"
            );
            add(
                "__EVENTVALIDATION",
                "/wEdABUw7u4oWYNNA/hskPOTKyXLcaWap+vodpcfJeWCQMb05M4VqTsYgOfsOlUEYrae4TYXW3nnTZvfLJuJ/u1PBVBSt/Nk3qkzVt4LDvwX7cgTvYkzTrqoUXgX6b+M1+257BjiaVX7cykmOFdg82YZu5IQMBf49FkTYEncAueWObXIq7cL5eRoAUKa+tyOPRsEflZhNfTcFtQ/jJWSfVz1xbGeVES/tLbibOmxRliqKZ9Av3XwaHsfo1OzGqVWo4Bficqt+1JTGeZBIT00ywTRJldKK5TzTxr7qFCDynYLGZnNMfqGMDGeMYbiKTDJUCRj2tM/oGKi7P7atsa5aP0Vrd1vGtQCH61R1Y4V6Nctazplc0IITE8wDizpQ9putf/8wQ3GqI45ikaYA87mLWvQM3CAWHUVMBw/qtB1eub5Igma8zZ5Ykbu6BMbwzKZdzjwkbii5eXsIV8yU88PfW9Ls/4Dn976cOu+OoChsY1d5tFi8A=="
            );
        }
    };

    private HttpHeaders headers;

    @Resource
    private RestTemplate restTemplate;

    public HouseClient(RestTemplate restTemplate) {
        this.headers = new HttpHeaders();
        this.headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
    }

    public Integer getHouseCount(LinkedMultiValueMap<String, String> appendValues) {
        var payload = new LinkedMultiValueMap<String, String>();
        payload.addAll(this.BasicValues);
        payload.addAll(appendValues);
        var targetUrl = this.getTargetUrl();
        var httpEntity = new HttpEntity<>(payload, this.headers);
        var selectResult = restTemplate.postForObject(targetUrl, httpEntity, String.class);
        return this.getCount(selectResult);
    }

    private String getTargetUrl() {
        var result = this.restTemplate.getForEntity(UrlConstant.HOUSE_URL, String.class);
        var location = result.getHeaders().getFirst(KeyConstant.LOCATION_KEY);
        return UrlConstant.HOUSE_HOST + location;
    }

    private Integer getCount(String selectResult) {
        String prefix = "<td align=\"left\">共&nbsp";
        int startIndex = selectResult.indexOf(prefix);
        if (startIndex == -1) {
            return null;
        }

        startIndex += prefix.length();
        String suffix = "&nbsp条&nbsp";
        int endIndex = selectResult.indexOf(suffix, startIndex);
        if (endIndex == -1) {
            return null;
        }

        return Integer.parseInt(selectResult.substring(startIndex, endIndex));
    }
}
