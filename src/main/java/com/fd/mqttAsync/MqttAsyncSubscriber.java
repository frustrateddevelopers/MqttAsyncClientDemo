package com.fd.mqttAsync;

import org.eclipse.paho.client.mqttv3.*;

/**
 * Created by qasim on 1/1/16.
 */
public abstract class MqttAsyncSubscriber {

    public static MqttAsyncClient mqttAsyncClientSubscription(String serverUri, String clientId , String topic, int qos) throws MqttException {
        MqttAsyncClient mqttAsyncClient = new MqttAsyncClient(serverUri, clientId);
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setCleanSession(true);
        mqttAsyncClient.connect(mqttConnectOptions, null, new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken iMqttToken) {
                System.out.println("connection successfull with client id : "+clientId+" , on server url : "+serverUri+" , qos level :"+qos);
                try {
                    mqttAsyncClient.subscribe(topic, qos);
                } catch (MqttException e) {
                    System.out.println("Error : "+e.getMessage());
                }
            }

            @Override
            public void onFailure(IMqttToken iMqttToken, Throwable throwable) {
                System.out.println("connection fail with client id : "+clientId+" , on server url : "+serverUri+" , qos level :"+qos);
            }
        });
        return mqttAsyncClient;
    }

}
