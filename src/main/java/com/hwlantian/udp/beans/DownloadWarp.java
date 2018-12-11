package com.hwlantian.udp.beans;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DownloadWarp {
    private List<Download> contents = new ArrayList<>();
}
