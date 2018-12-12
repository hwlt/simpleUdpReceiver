package com.hwlantian.udp.beans.record;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hwlantian.udp.beans.tbsd.Command;
import com.hwlantian.udp.beans.tbsd.Requests;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by AirJ on 17/8/17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Record {
    private String ip;
    private String deviceId;
    private Long timestamp;
    private int duration;
    private HardwareVersion version = new HardwareVersion();
    private Map<String, Double> data;
    private Requests requests;
    private List<Command> commands;
    private Integer line;
    private List<String> routers;
}
