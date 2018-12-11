package com.hwlantian.udp.beans;

import lombok.Data;

import java.util.List;

@Data
public class UploadWarp{
    private String ip;
    private List<Upload> contents;
}
