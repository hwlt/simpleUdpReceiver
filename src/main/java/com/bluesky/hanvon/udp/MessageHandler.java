package com.bluesky.hanvon.udp;


import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

import io.netty.channel.socket.DatagramPacket;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;

@Slf4j
public class MessageHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        String uploadMessage = msg.content().toString(CharsetUtil.UTF_8);
        String hostAddress = msg.sender().getAddress().getHostAddress();
        log.info("upload message: {}, from ip: {}", uploadMessage, hostAddress);
        String downloadMessage = "{\"response\":\"ok\"}\n";
        DatagramPacket responsePacket = new DatagramPacket(Unpooled.copiedBuffer(downloadMessage, Charset.forName("UTF-8")), msg.sender());
        ctx.write(responsePacket);
        ctx.flush();
    }
}
