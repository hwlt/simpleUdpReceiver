package com.hwlantian.udp.beans.record;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hwlantian.udp.beans.PackageKey;
import lombok.Data;

/**
 * Created by AirJ on 17/8/17.
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Route {
    @PackageKey(0)
    String protocol;
    @PackageKey(1)
    String address;
    @PackageKey(2)
    Integer port;
}
