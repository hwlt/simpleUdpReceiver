package com.hwlantian.udp.beans.tbsd;

import com.hwlantian.udp.beans.PackageKey;
import lombok.Data;

@Data
public class CommandData {
    @PackageKey(0x00)
    private String ssid;
    @PackageKey(0x01)
    private String password;
    @PackageKey(0x10)
    private boolean started;
    @PackageKey(0x20)
    private int power;
    @PackageKey(0x21)
    private int mode;
    @PackageKey(0x30)
    private boolean pairing;
    @PackageKey(0x40)
    private int fanSpeed;
    @PackageKey(0x41)
    private int fanSpeedPercent;
    @PackageKey(0x42)
    private int fanLevel;
    @PackageKey(0x4a)
    private int ledSwitch;
    @PackageKey(0x4b)
    private int ledMode;
    @PackageKey(0x4c)
    private String ledColor;
    @PackageKey(0x4d)
    private int ledColorStatus;
    @PackageKey(0x50)
    private int airLoop;
    @PackageKey(0x51)
    private int airHeat;
    @PackageKey(0x52)
    private int ionPower;
    @PackageKey(0x53)
    private int humidifier;
    @PackageKey(0x60)
    private int durTarget;
    @PackageKey(0x61)
    private Long pm2d5Duration;
    @PackageKey(0x62)
    private Long tempDuration;
    @PackageKey(0x63)
    private Long humDuration;
    @PackageKey(0x80)
    private int settings_00;
    @PackageKey(0x81)
    private int settings_01;
    @PackageKey(0x82)
    private int settings_02;
    @PackageKey(0x83)
    private int settings_03;
    @PackageKey(0x84)
    private int settings_04;
    @PackageKey(0x85)
    private int settings_05;
}
