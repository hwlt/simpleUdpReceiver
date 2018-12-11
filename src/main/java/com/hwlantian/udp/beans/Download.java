package com.hwlantian.udp.beans;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hwlantian.udp.beans.record.Return;
import com.hwlantian.udp.beans.record.Route;
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
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Download {

    private String deviceId;
    private Route route;
    private Responses responses;
    private List<Command> commands;
    private Map<String,TBSD> status; // not used
    private String error;
    private Integer errorCode;
    private Map<String,Object> data;
    private Integer line;
    private String sessionId;
    private String setSessionId;
    public static Download fromReturn(Return r) {
        Download download = new Download();
        download.setDeviceId(r.getDeviceId());
        download.setRoute(r.getRoute());
        download.setResponses(r.getResponses());
        download.setCommands(r.getCommands());
        download.setData(r.getData());
        if(r.getLine() != null){
            download.setLine(r.getLine());
        }
        if(r.getNeedStatus() !=null && r.getNeedStatus()){
            download.setStatus(r.getStatus());
        }
        return download;
    }
}
