package com.fd.mqttAsync;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.context.annotation.Configuration;

/**
 * Created by qasim on 1/1/16.
 */
@Configuration
public class MqttAsyncPublisher {

    private MqttAsyncClient mqttAsyncClient;

    public void mqttAsyncClientPublisher(String serverUri, String clientId, String topic, int qos, String msg) throws MqttException {
        mqttAsyncClient = new MqttAsyncClient(serverUri, clientId);
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(true);
        mqttAsyncClient.connect(mqttConnectOptions, null, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken iMqttToken) {
                System.out.println("connection successfull with client id : "+clientId+" , on server url : "+serverUri+" while publishing message");
                MqttMessage mqttMessage = new MqttMessage();
                mqttMessage.setPayload(msg.getBytes());
                mqttMessage.setRetained(true);
                mqttMessage.setQos(2);
                try {
                    mqttAsyncClient.publish(topic, mqttMessage, null, new ActionListener());
                } catch (MqttException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                System.out.println("connection fail with client id : "+clientId+" , on server url : "+serverUri+" while publishing message");
            }
        });
    }

public class ActionListener implements IMqttActionListener{
    @Override
    public void onSuccess(IMqttToken iMqttToken) {
        System.out.println("message published successfully");
        try {
            System.out.println("client with id "+mqttAsyncClient.getClientId()+" closed succesfully");
            mqttAsyncClient.close();
        } catch (MqttException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
        System.out.println("message could not published");
    }
}

}
