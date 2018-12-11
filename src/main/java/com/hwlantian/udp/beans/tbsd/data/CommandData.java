package com.hwlantian.udp.beans.tbsd.data;

import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.Data;

/**
 * Created by Administrator on 2017/9/8.
 */
@Data
public class CommandData {
    String ssid;
    String password;
    Integer fanSpeed;
    Integer fanSpeedPercent;
    Integer power;
    Integer mode;
    Double slope;

    Integer ledSwitch;
    Integer fanLevel;
    Integer screen;
    Integer automatic;
    Integer theme;
    Integer language;
    Integer autoShutdown;
    Integer duration;

    FirmwareSegment firmware;

    @JsonSetter("ledSwitch")
    public void setledSwitch(Integer ledSwitch){
        this.ledSwitch = ledSwitch;
    }

}