package com.hwlantian.udp.beans;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hwlantian.udp.beans.record.HardwareVersion;
import com.hwlantian.udp.beans.record.Record;
import com.hwlantian.udp.beans.tbsd.Command;
import com.hwlantian.udp.beans.tbsd.Requests;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Airj on 2017/9/8.
 */
@Data
public class Upload {
    private String ip;
    @JsonProperty("devId")
    private String deviceId;
    private Map<String, Double> data = new HashMap<>();
    private Integer line;
    @JsonAnySetter
    public void putData(String key, Object value) {
        if (value instanceof Number) {
            data.put(key, ((Number) value).doubleValue());
        }
    }
    public Record toRecord() {
        Record record = new Record();
        record.setDeviceId(deviceId);
        record.setIp(ip);
        record.setData(data);
        record.setLine(line);
        return record;
    }

}
