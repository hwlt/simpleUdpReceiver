package com.bluesky.hanvon.udp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class UDPApplication {
    public static void main(String[] args) {
        SpringApplication.run(UDPApplication.class, args);
    }

    @PostConstruct
    public void startService() throws InterruptedException {
        MessageHandler messageHandler = new MessageHandler();
        UDPServer udpServer = new UDPServer(messageHandler);
        udpServer.start();
    }
}
