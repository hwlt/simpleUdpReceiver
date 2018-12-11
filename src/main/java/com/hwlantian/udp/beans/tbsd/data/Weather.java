package com.hwlantian.udp.beans.tbsd.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by AirJ on 17/8/17.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    Map<String, Object> data = new HashMap<>();
    private Long timestamp;
}
