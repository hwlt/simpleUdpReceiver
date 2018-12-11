package com.hwlantian.udp.beans.tbsd.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class WeatherHistory {
    Integer from;
    Integer to;
    String type;
    String level;
    List<SimpleWeaHis> data;

    @Data
    public static class SimpleWeaHis{
        Object data;
        long timestamp;
    }
}
