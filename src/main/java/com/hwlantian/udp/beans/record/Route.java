package com.hwlantian.udp.beans.record;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by AirJ on 17/8/17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Route {
    String protocol;
    String address;
    Integer port;
}
