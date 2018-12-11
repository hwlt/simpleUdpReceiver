package com.hwlantian.udp.beans.tbsd;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;


/**
 * Created by AirJ on 17/8/17.
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Status extends KnownDatas {

    public void setTimestamp(Map<String,Long> map) {

    }
}
