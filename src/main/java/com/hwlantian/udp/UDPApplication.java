package com.hwlantian.udp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hwlantian.udp.service.UDPServer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.*;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class UDPApplication {


    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.IGNORE_UNDEFINED, true);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        return objectMapper;
    }

    public static void main(String[] args) {
        SpringApplication.run(UDPApplication.class, args);
    }

    @Autowired
    private
    UDPServer udpServer;

    private final static int PORT = 59999;

    @PostConstruct
    public void startService() {
        udpServer.setPort(PORT);
        udpServer.start();

        // fake upload data, raw data is
        // {"devId":"N2-ACE7D39FCA57DF11685B51CC906808CE","ver":"N2 1.0.1.0","slope":100,"pm2d5":7.980000,"pm10":39.200000,"ch2o":223,"co2":3920,"temp":21.770000,"hum":20.710000,"ssid":"BLUE_SKY","systemTick":15502}
        //
         TimerTask task = new TimerTask() {
            @Override
            public void run() {
                String message = "{\"devId\":\"N2-ACE7D39FCA57DF11685B51CC906808CE\",\"ver\":\"N2 1.0.1.0\",\"slope\":100,\"pm2d5\":7.980000,\"pm10\":39.200000,\"ch2o\":223,\"co2\":3920,\"temp\":21.770000,\"hum\":20.710000,\"ssid\":\"BLUE_SKY\",\"systemTick\":15502}";
                try {
                    UDPClient client = new UDPClient();
                    client.send(message);
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
//        new Timer().schedule(task, 5000);
    }
// manually start the server, instead of using spring IOT
//    public void startService() throws InterruptedException {
//        MessageHandler messageHandler = new MessageHandler();
//        UDPServer udpServer = new UDPServer(messageHandler);
//        udpServer.start();
//    }


    //UDPClient if of fake upload data for test ONLY
    @Slf4j
    @Data
    static class UDPClient {
        private DatagramSocket socket;

        private byte[] buf;

        public UDPClient() throws SocketException {
            socket = new DatagramSocket();
        }

        public void send(String msg) throws IOException {
            buf = msg.getBytes();
            DatagramPacket packet
                    = new DatagramPacket(buf, buf.length, InetAddress.getByName("localhost"), PORT);
            socket.send(packet);
            log.info("sending fake data: {}", msg);
        }

        public void close() {
            socket.close();
        }
    }
}
