package com.hwlantian.udp.controller;

import com.hwlantian.udp.beans.record.Record;
import com.hwlantian.udp.service.DemoDatabase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DataController {
    private final DemoDatabase database;

    @GetMapping("/last")
    Record last() {
        return Optional.ofNullable(database.getLast()).orElseThrow(ResourceNotFoundException::new);
    }

    @GetMapping("/all")
    Wrap all() {
        return new Wrap(database.getRecords());
    }

    @GetMapping("/one/{deviceId}")
    Record one(@PathVariable String deviceId) {
        return Optional.ofNullable(database.getDatamap().get(deviceId)).orElseThrow(ResourceNotFoundException::new);
    }

    @AllArgsConstructor
    @Getter
    static class Wrap{
        List<Record> contents;
    }
}
