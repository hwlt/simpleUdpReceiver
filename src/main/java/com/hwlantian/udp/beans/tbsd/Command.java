package com.hwlantian.udp.beans.tbsd;

import com.hwlantian.udp.beans.PackageKey;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Map;

/**
 * Created by Administrator on 2017/9/8.
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Command extends TBSD<Map<String,Object>> {

    @PackageKey(value = 0,ignore = true)
    public static final Integer MAXLINE = 0xFFFF;
    public static Integer circleLine(Integer line) {
        return line % (MAXLINE + 1);
    }

    public static Integer circleCompare(Integer a, Integer b) {
        int t = MAXLINE + 1;
        int c = Math.floorMod(a - b, t);
        if (c > t / 2) {
            return c - t;
        } else {
            return c;
        }
    }
    @PackageKey(1)
    Integer line;

}