package com.hwlantian.udp.beans.record;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hwlantian.udp.beans.tbsd.Command;
import com.hwlantian.udp.beans.tbsd.Requests;
import com.hwlantian.udp.beans.tbsd.Responses;
import com.hwlantian.udp.beans.tbsd.TBSD;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/8.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Return {
    String deviceId;
    Route route;
    Responses responses;
    Boolean needStatus;
    List<Command> commands;
    Map<String,TBSD> status;
    Map<String,Object> data;
    Integer line;

}
