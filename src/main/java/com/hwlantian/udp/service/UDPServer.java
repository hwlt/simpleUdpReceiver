package com.hwlantian.udp.service;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramChannel;
import io.netty.channel.socket.nio.NioDatagramChannel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/9/9.
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UDPServer {
    @Setter
    private Integer port = 59999;
    private final ChannelInboundHandler messageHandler;
    private ChannelFuture channelFuture;
    public void start() {
        if (channelFuture!=null) {
            return;
        }
        new Thread(()->{
            EventLoopGroup group = new NioEventLoopGroup();
            try {
                Bootstrap bootstrap = new Bootstrap();
                bootstrap.group(group);
                bootstrap.channel(NioDatagramChannel.class);
                bootstrap.handler(new ChannelInitializer<DatagramChannel>() {
                    @Override
                    protected void initChannel(DatagramChannel channel) throws Exception {
                        channel.pipeline().addLast(messageHandler);
                    }
                });
                bootstrap.option(ChannelOption.SO_BROADCAST, true);
                bootstrap.option(ChannelOption.RCVBUF_ALLOCATOR,
                        new AdaptiveRecvByteBufAllocator(100 * 1024, 5 * 1024 * 1024,10 * 1024 * 1024));
//                bootstrap.option(ChannelOption.ALLOCATOR, )
                channelFuture = bootstrap.bind(port).sync();
                log.info("UDP server started: {}", port);
                channelFuture.channel().closeFuture().sync();
                log.info("UDP server stopped: {}", port);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                group.shutdownGracefully();
            }
        }).start();
    }

    public void stop() {
        channelFuture.channel().close();
        channelFuture = null;
    }
}
