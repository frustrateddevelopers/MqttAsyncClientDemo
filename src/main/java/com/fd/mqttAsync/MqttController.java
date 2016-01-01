package com.fd.mqttAsync;

import com.fd.mqttAsync.MqttAsyncPublisher;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by qasim on 1/1/16.
 */
@RestController
@RequestMapping("/api/v1")
public class MqttController {

    @RequestMapping("/data1")
    public String getData1() throws MqttException {
        MqttAsyncPublisher.mqttAsyncClientPublisher("tcp://localhost:1883", java.util.UUID.randomUUID().toString(),"test/1", 2, "this is sos!");
        return "success";
    }

    @RequestMapping("/data2")
    public String getData2() throws MqttException {
        MqttAsyncPublisher.mqttAsyncClientPublisher("tcp://localhost:1883", java.util.UUID.randomUUID().toString(),"test1/1/test2", 2, "this is driver location!");
        return "success";
    }
}
