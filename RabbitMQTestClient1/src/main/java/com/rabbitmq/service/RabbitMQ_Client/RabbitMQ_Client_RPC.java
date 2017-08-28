package com.rabbitmq.service.RabbitMQ_Client;


import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by tingyun on 2017/4/18.
 */
public class RabbitMQ_Client_RPC extends HttpServlet {

    private Connection connection;
    private Channel channel;
    private String requestQueueName = "rpc_queue";
    private String replyQueueName;
    private QueueingConsumer consumer;

    public RabbitMQ_Client_RPC() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.3.2");
        factory.setUsername("asdf");
        factory.setPassword("pwd123456");
        connection = factory.newConnection();
        channel = connection.createChannel();
        replyQueueName = channel.queueDeclare().getQueue();
        System.out.println("test:+++" + replyQueueName);
        consumer = new QueueingConsumer(channel);
        channel.basicConsume(replyQueueName, true, consumer);
    }

    public String call(String message) throws Exception {
        String response = null;
        String corrId = UUID.randomUUID().toString();

        BasicProperties props = new BasicProperties.Builder().correlationId(corrId).replyTo(replyQueueName).build();

        channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response = new String(delivery.getBody(), "UTF-8");
                break;
            }
        }

        return response;
    }

    public void close() throws Exception {
        connection.close();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response1) throws ServletException, IOException {
        RabbitMQ_Client_RPC fibonacciRpc = null;
        String response = null;
        try {
            fibonacciRpc = new RabbitMQ_Client_RPC();
            System.out.println("RPCClient [x] Requesting fib(30)");
            response = fibonacciRpc.call("30");
            System.out.println("RPCClient [.] Got '" + response + "'");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fibonacciRpc != null) {
                try {
                    fibonacciRpc.close();
                } catch (Exception ignore) {
                }
            }
        }
    }
}


