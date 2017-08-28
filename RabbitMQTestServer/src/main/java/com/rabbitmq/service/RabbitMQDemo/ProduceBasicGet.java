package com.rabbitmq.service.RabbitMQDemo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.concurrent.TimeoutException;

/**
 * Created by jialianqing on 2016/12/11 0011.
 */
public class ProduceBasicGet {
    public  static  void initProduce () throws java.io.IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection =factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("hello-world-queue", false, false, false, null);
        String message = "TestBasicGet";
        channel.basicPublish("", "hello-world-queue", null, message.getBytes("UTF-8"));
        System.out.println("sent:"+message);
        channel.close();
        connection.close();
    }
}
