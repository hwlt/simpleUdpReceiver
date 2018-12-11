package com.hwlantian.udp.beans.tbsd.data;

import com.hwlantian.udp.beans.PackageKey;
import lombok.Data;

import java.util.List;

@Data
public class FirmwareSegment {
  @PackageKey(0x00)
  String version;
  @PackageKey(0x01)
  Long start;
  @PackageKey(0x02)
  Long lines;
  @PackageKey(0x03)
  Long total;
  @PackageKey(0x10)
  List data;
}