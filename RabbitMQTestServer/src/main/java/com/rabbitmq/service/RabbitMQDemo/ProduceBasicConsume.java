package com.rabbitmq.service.RabbitMQDemo;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.util.concurrent.TimeoutException;

/**
 * Created by jialianqing on 2016/12/11 0011.
 */
public class ProduceBasicConsume {

    private static final String EXCHANGE_NAME = "dirlogsone";

    public  static  void initProduce (String message) throws java.io.IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection =factory.newConnection();
        Channel channel = connection.createChannel();

        //String queueName = "test.queue";

        String routingKey = "testRouting";
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");
        //channel.queueBind(queueName, EXCHANGE_NAME, routingKey);

        channel.basicPublish(EXCHANGE_NAME,routingKey, MessageProperties.TEXT_PLAIN,message.getBytes("UTF-8"));

        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();

    }



/*    private static String getMessage(String[] strings){
        if (strings.length < 1)
            return "info: Hello World!";
        return joinStrings(strings, " ");
    }

    private static String joinStrings(String[] strings, String delimiter) {
        int length = strings.length;
        if (length == 0) return "";
        StringBuilder words = new StringBuilder(strings[0]);
        for (int i = 1; i < length; i++) {
            words.append(delimiter).append(strings[i]);
        }
        return words.toString();
    }*/
}
