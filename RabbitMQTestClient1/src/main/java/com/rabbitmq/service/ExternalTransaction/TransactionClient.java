/**
 * TODO
 * 
 */
package com.rabbitmq.service.ExternalTransaction;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @author jialianqing
 *
 */
public class TransactionClient {

    public static void initClient(String exchange, String queue) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.194.1.2");
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        String queueName="";
        if(exchange!=""&&exchange!=null) {
            channel.exchangeDeclare(exchange, "direct");
            queueName= channel.queueDeclare().getQueue();
            channel.queueBind(queueName, exchange,queue);
            System.out.println("TransactionClient exchange:"+exchange+", queueName:" + queueName);
        }
        else{
            channel.queueDeclare(queue, false, false, false, null);
            queueName=queue;
            System.out.println("TransactionClient exchange:"+exchange+", BindRoutingKey:" + queue);

        }
        System.out.println("ReceiveLogsDirect1 [*] Waiting for messages. To exit press CTRL+C");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                try {
                    Thread.sleep(1000); //
                } catch (InterruptedException _ignored) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
            }
        };
        channel.basicConsume(queueName, true, consumer);
    }
}