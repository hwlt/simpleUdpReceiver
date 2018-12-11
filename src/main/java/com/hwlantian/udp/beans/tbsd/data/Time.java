package com.hwlantian.udp.beans.tbsd.data;

import com.hwlantian.udp.service.TimeUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Calendar;

/**
 * Created by Administrator on 2017/9/8.
 */
@Data
@AllArgsConstructor
public class Time {
    private Long timestamp;
    private Integer year;
    private Integer month;
    private Integer day;
    private Integer hour;
    private Integer minute;
    private Integer second;
    private Integer weekday;

    public Time(){
        Long time= TimeUtil.getTime();
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(time);
        this.year = c.get(Calendar.YEAR);
        this.month = c.get(Calendar.MONTH) + 1;
        this.day = c.get(Calendar.DAY_OF_MONTH);
        this.hour = c.get(Calendar.HOUR_OF_DAY);
        this.minute = c.get(Calendar.MINUTE);
        this.second = c.get(Calendar.SECOND);
        this.weekday = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (this.weekday == 0) {
            this.weekday = 7;
        }
        this.timestamp = time/1000;
    }

    public Time(Long timestamp) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(timestamp);
        this.timestamp = timestamp/1000;
        this.year = c.get(Calendar.YEAR);
        this.month = c.get(Calendar.MONTH) + 1;
        this.day = c.get(Calendar.DAY_OF_MONTH);
        this.hour = c.get(Calendar.HOUR_OF_DAY);
        this.minute = c.get(Calendar.MINUTE);
        this.second = c.get(Calendar.SECOND);
        this.weekday = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (this.weekday == 0) {
            this.weekday = 7;
        }
    }
}
