package com.hwlantian.udp.service;

import com.hwlantian.udp.beans.record.Record;
import com.hwlantian.udp.beans.record.Return;
import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Getter
public class DemoDatabase {
    private Map<String, Record> datamap = new HashMap<>();
    private List<Record> records = new ArrayList<>();
    private Record last = null;
    public Return save(Record record) {
        datamap.put(record.getDeviceId(), record);
        records.add(record);
        last = record;
        Return aReturn = new Return();
        aReturn.setDeviceId(record.getDeviceId());
        return aReturn;
    }
}
