package com.bluesky.hanvon.udp;

import lombok.Data;

import java.util.List;

@Data
public class UploadWarp{
    private String ip;
    private List<Upload> contents;
}
