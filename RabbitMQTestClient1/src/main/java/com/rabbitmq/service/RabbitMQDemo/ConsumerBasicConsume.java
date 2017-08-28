package com.rabbitmq.service.RabbitMQDemo;

import com.rabbitmq.client.*;

import java.util.concurrent.TimeoutException;

/**
 * Created by jialianqing on 2016/12/11 0011.
 */
public class ConsumerBasicConsume {

    private  static  final String EXCHANGE_NAME = "dirlogsone";

    public static  void initConsumerBasicConsume()
            throws java.io.IOException,
            java.lang.InterruptedException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

/*
        channel.exchangeDeclare(EXCHANGE_NAME,"fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName,EXCHANGE_NAME,"");
*/
        String queue = channel.queueDeclare().getQueue();
        String routingKey = "testRouting";
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        channel.queueBind(queue, EXCHANGE_NAME, routingKey);

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(queue,true,consumer);
        int counter = 0;
        boolean noAck = false;
        while (true) {
            QueueingConsumer.Delivery delivery=null;
             delivery = consumer.nextDelivery();
            if (delivery != null) {
                String message = new String(delivery.getBody());
                System.out.println("----------- receive message " + (++counter)
                        + "-----------");
                System.out.println(" [x] Received '" + message + "'");

                System.out.println("receive success.\n\n");

                // channel.basicCancel(queueingConsumer.getConsumerTag());
                // System.out.println("cancel consumer success.");
                break;
            }
        }
    }
}
