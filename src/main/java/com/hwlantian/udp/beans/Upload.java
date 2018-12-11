package com.hwlantian.udp.beans;

import com.hwlantian.udp.beans.record.HardwareVersion;
import com.hwlantian.udp.beans.record.Record;
import com.hwlantian.udp.beans.tbsd.Command;
import com.hwlantian.udp.beans.tbsd.Requests;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by Airj on 2017/9/8.
 */
@Data
public class Upload {

    private String ip;
    private String deviceId;
    private HardwareVersion version;

    private Map<String, Double> data;
//    private Map<String,TBSD> requests;
    private Requests requests;
    private List<Command> commands;
    private List<String> routers;
    private Integer line;
    private String sessionId;

    public Record toRecord() {
        Record record = new Record();
        record.setDeviceId(deviceId);
        record.setVersion(version);
        if (record.getVersion().getProtocol() == null) {
            record.getVersion().setProtocol("udp 2.0");
        }
        record.setIp(ip);
        record.setData(data);
//        if (requests != null && !requests.isEmpty()) {
//            Requests requestsByte = new Requests();
//            if (requests.get("time") != null) {
//                requestsByte.setTime(new TimeElement());
//            }
//            TBSD<Object> air = requests.get("weather");
//            if (air != null&& air.getData() !=null && air.getData()instanceof LinkedHashMap) {
//                LinkedHashMap airData = (LinkedHashMap)air.getData();
//                WeatherElement weatherElement = new WeatherElement();
//                weatherElement.setDataTypes((int)airData.get("type"));
//                requestsByte.setWeather(weatherElement);
//            }
//            TBSD<Object> forecast = requests.get("forecast");
//            if(forecast !=null) {
//                WeatherForecastElement weatherForecastElement = new WeatherForecastElement();
//                weatherForecastElement.setDataTypes(3977216);
//                requestsByte.setWeatherForecast(weatherForecastElement);
//            }
//            TBSD<Object> weatherHistory = requests.get("weatherHistory");
//            if(weatherHistory !=null && weatherHistory.getData() !=null && weatherHistory.getData()instanceof LinkedHashMap) {
//                LinkedHashMap historyData = (LinkedHashMap) weatherHistory.getData();
//                WeatherHistoryElement weatherHistoryElement = new WeatherHistoryElement();
//                weatherHistoryElement.setLevel((String) historyData.get("level"));
//                if (historyData.get("type").equals("temperature")) {
//                    weatherHistoryElement.setDataTypes(256);
//                }
//                else {
//                    weatherHistoryElement.setDataTypes(1);
//                }
//                weatherHistoryElement.setStartIncludeOffset((int) historyData.get("from"));
//                weatherHistoryElement.setEndExcludeOffset((int) historyData.get("to"));
//                requestsByte.setWeatherHistory(weatherHistoryElement);
//            }
//            record.setRequests(requestsByte);
//        }
        record.setRequests(requests);
        record.setLine(line);
        record.setRouters(routers);
        record.setCommands(commands);
        return record;
    }

}
