package com.hwlantian.udp.beans.record;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hwlantian.udp.beans.tbsd.Command;
import com.hwlantian.udp.beans.tbsd.Requests;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AirJ on 17/8/17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Record {
    private String deviceId;
    private String ip;
    private Long timestamp;
    private HardwareVersion version;
    private Map<String, Double> data;
    private Requests requests;
    private List<Command> commands = new ArrayList<>();
    private Integer line = 0;
    private List<String> routers = new ArrayList<>();
}
