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
    private Requests requests;
    private List<Command> commands;
    private List<String> routers;
    private Integer line;

    public Record toRecord() {
        Record record = new Record();
        record.setDeviceId(deviceId);
        record.setVersion(version);
        if (record.getVersion().getProtocol() == null) {
            record.getVersion().setProtocol("udp 2.0");
        }
        record.setIp(ip);
        record.setData(data);
        record.setRequests(requests);
        record.setLine(line);
        record.setRouters(routers);
        record.setCommands(commands);
        return record;
    }

}
