package com.rabbitmq.service.RabbitMQDemo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

import java.util.concurrent.TimeoutException;

/**
 * Created by jialianqing on 2016/12/11 0011.
 */
public class ConsumerBasicGet {

    public static void initConsumerBasicGet()
            throws java.io.IOException,
            InterruptedException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("hello-world-queue", false, false, false, null);
        GetResponse result = channel.basicGet("hello-world-queue", true);
        if (result != null)
        {
            System.out.println("Message : " + new String(result.getBody()));
        }
    }
}
