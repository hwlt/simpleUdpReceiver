package com.hwlantian.udp.beans;

// this is for bin format. ignore it in json case
public @interface PackageKey {
    int value();
    boolean ignore() default false;
}
