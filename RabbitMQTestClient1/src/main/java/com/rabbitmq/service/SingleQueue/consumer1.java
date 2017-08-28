/**
 * TODO
 * 
 */
package com.rabbitmq.service.SingleQueue;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * ��Ϣ������
 * 
 * @author jialianqing
 * 
 */
public class consumer1 {

    private final static String QUEUE_NAME = "singlequeue";

    public static void initConsumer() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.3.2");
        factory.setUsername("asdf");
        factory.setPassword("pwd123456");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        try {
            Thread.sleep(2000); //
        } catch (InterruptedException _ignored) {
            Thread.currentThread().interrupt();
        }
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };
        channel.basicConsume("",consumer);

    }
}

