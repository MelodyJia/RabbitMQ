/**
 * TODO
 * 
 */
package com.rabbitmq.service.ExternalTransaction;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author jialianqing
 *
 */
public class TransactionServer {

    public static void initServer(String exchange,String queue) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.194.1.2");
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        for(int i=0;i<100;i++) {
            //使用Exchange
            if (exchange != "" && exchange != null) {
                channel.exchangeDeclare(exchange, "direct");
                String message = "From Java,the exchange is " + exchange;
                channel.basicPublish(exchange, queue, null, message.getBytes());
                System.out.println(" [Exchange is not null] Sent '" + message + "'");
                //不用Exchange
            } else {
                //channel.exchangeDeclare(null, "direct");
                String message = "From Java,the queue is  " + queue;
                channel.queueDeclare(queue, false, false, false, null);
                channel.basicPublish("", queue, null, message.getBytes());
                System.out.println(" [Exchange is null] Sent '" + message + "'");
            }
        }
        channel.close();
        connection.close();
    }
}