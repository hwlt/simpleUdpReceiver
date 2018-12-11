package com.hwlantian.udp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hwlantian.udp.service.MessageHandler;
import com.hwlantian.udp.service.UDPServer;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import sun.net.util.IPAddressUtil;

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
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
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
        // {"contents":[{"deviceId":"N2-1e4687cc490bf9ecc0e62b879feaa2b0","version":{"hardware":"N2 1.0.0 release","firmware":"N2 2.0.0 international","protocol":"udp 2.0"},"commands":[{"line":0,"data":{"imei":"383633353836303335373633323837"}}],"requests":{"time":{},"weather":{"data":{"type":127}},"forecast":{"data":{"from":-1,"to":4}},"weatherHistory":{"data":{"from":30,"to":0,"type":"pm2d5","level":"day"}}},"data":{"pm2d5":297.07,"co2":2634,"co2w":0,"pm1d0":185.26,"pm10":331.8,"pc0d3":38801,"pc1d0":2298,"pc2d5":139,"pc5":26,"pc10":12,"pal":24360,"temp":26.11,"hum":42.11,"rssi":-66,"battery":100}}]}
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                String message = "{\"contents\":[{\"deviceId\":\"N2-1e4687cc490bf9ecc0e62b879feaa2b0\",\"version\":{\"hardware\":\"N2 1.0.0 release\",\"firmware\":\"N2 2.0.0 international\",\"protocol\":\"udp 2.0\"},\"commands\":[{\"line\":0,\"data\":{\"imei\":\"383633353836303335373633323837\"}}],\"requests\":{\"time\":{},\"weather\":{\"data\":{\"type\":127}},\"forecast\":{\"data\":{\"from\":-1,\"to\":4}},\"weatherHistory\":{\"data\":{\"from\":30,\"to\":0,\"type\":\"pm2d5\",\"level\":\"day\"}}},\"data\":{\"pm2d5\":297.07,\"co2\":2634,\"co2w\":0,\"pm1d0\":185.26,\"pm10\":331.8,\"pc0d3\":38801,\"pc1d0\":2298,\"pc2d5\":139,\"pc5\":26,\"pc10\":12,\"pal\":24360,\"temp\":26.11,\"hum\":42.11,\"rssi\":-66,\"battery\":100}}]}";
                try {
                    UDPClient client = new UDPClient();
                    client.send(message);
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        new Timer().schedule(task, 5000);
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
