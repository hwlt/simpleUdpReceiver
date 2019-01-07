package com.hwlantian.udp.service;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hwlantian.udp.beans.Download;
import com.hwlantian.udp.beans.DownloadWarp;
import com.hwlantian.udp.beans.Upload;
import com.hwlantian.udp.beans.UploadWarp;
import com.hwlantian.udp.beans.record.Record;
import com.hwlantian.udp.beans.record.Return;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import io.netty.channel.socket.DatagramPacket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MessageHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private final ObjectMapper objectMapper;
    private final DemoDatabase database;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        String uploadMessage = msg.content().toString(CharsetUtil.UTF_8);
        String hostAddress = msg.sender().getAddress().getHostAddress();
        log.trace("upload message: {}, from ip: {}", uploadMessage, hostAddress);
        try {
            long now = TimeUtil.getTime();
            Upload upload = objectMapper.readValue(uploadMessage, Upload.class);
            Record record = upload.toRecord();
            record.setIp(hostAddress);
            record.setTimestamp(now);
            // this log is just for demo.
            log.info("device id is: {}, data is{}, ip is{}.", record.getDeviceId(), record.getData(), record.getIp());
            // save data to db or cache, or send internal message to other service for that.
            // and need to get Return for that record to tell device that the data has been received.
            // if device request data from server, or send command to server. or server has some command need to send
            // to device. add them to the Return. Beware that the server-to-device command can be sent later. so may need to
            // cache the ip address and the port if you need that feature.

            // now I just use DemoDatabase for data store stuff. Beware that the upload may be very frequency if got
            // too many devices. So better use message rather than save it right now.
            Return aReturn = database.save(record);
            // IMPORTANT!!!  need to add a '\n' at the end of the download packet.
            String downloadMessage = objectMapper.writeValueAsString(Download.fromReturn(aReturn)) + "\n";
            DatagramPacket responsePacket = new DatagramPacket(Unpooled.copiedBuffer(downloadMessage, Charset.forName("UTF-8")), msg.sender());
            ctx.write(responsePacket);
            ctx.flush();
        } catch (JsonParseException | JsonMappingException ex) {
            log.error("parse error!");
            String downloadMessage = objectMapper.writeValueAsString(Collections.singletonMap("error", "parse error")) + "\n";
            DatagramPacket responsePacket = new DatagramPacket(Unpooled.copiedBuffer(downloadMessage, Charset.forName("UTF-8")), msg.sender());
            ctx.write(responsePacket);
            ctx.flush();
        }

    }
}
