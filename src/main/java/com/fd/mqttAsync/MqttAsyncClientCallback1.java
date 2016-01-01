package com.fd.mqttAsync;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * Created by qasim on 1/1/16.
 */
@Configuration
public class MqttAsyncClientCallback1 implements MqttCallback {

    private MqttAsyncClient mqttAsyncClient;

    @PostConstruct
    public void setCallback() throws MqttException {
        mqttAsyncClient = MqttAsyncSubscriber.mqttAsyncClientSubscription("tcp://localhost:1883", java.util.UUID.randomUUID().toString(), Constant.FD_TEST1, 2);
        mqttAsyncClient.setCallback(this);
    }

    @Override
    public void connectionLost(Throwable throwable) {

    }

    @Override
    public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
        System.out.println("this is your sos");
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

    }
}

