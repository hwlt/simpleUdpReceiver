package com.hwlantian.udp.beans.tbsd.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Upgrading {
    String version;
    Long total;
    Long linePerSegment;
    Long nextLine;
    Integer commandLine;
}
