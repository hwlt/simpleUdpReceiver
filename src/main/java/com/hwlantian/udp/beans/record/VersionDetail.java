package com.hwlantian.udp.beans.record;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class VersionDetail {
    public VersionDetail(){}

    public VersionDetail(String version) {
        List<String> spans = Arrays.asList(version.split(" ", 3));
        serial = spans.get(0);
        if (spans.get(1) != null) {
            List<String> numbers = Arrays.asList(spans.get(1).split("\\.", 3));
            major = Integer.valueOf(numbers.get(0));
            minor = Integer.valueOf(numbers.get(1));
            patch = Integer.valueOf(numbers.get(2));
        }
        info = spans.get(2);
    }
    private String serial;
    private Integer major;
    private Integer minor;
    private Integer patch;
    private String info;

    public String toString(){
        String toRet = serial;
        toRet += " " + major + "." + minor + "." + patch;
        if (info != null) {
            toRet += " " + info;
        }
        return toRet;
    }

    private Integer toBigNumber() {
        return major * 10000 + minor * 100 + patch;
    }

    public static Integer compare(VersionDetail a, VersionDetail b) {
        return a.toBigNumber() - b.toBigNumber();
    }
}
