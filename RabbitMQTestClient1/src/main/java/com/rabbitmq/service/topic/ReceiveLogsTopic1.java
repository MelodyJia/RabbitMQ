/**
 * TODO
 * 
 */
package com.rabbitmq.service.topic;

/**
 * @author jialianqing
 *
 */

import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiveLogsTopic1 {

	private static final String EXCHANGE_NAME = "Exchange_topic";
	 
	public static void initReceiveLogsTopic1(int para) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.3.2");
		factory.setUsername("asdf");
		factory.setPassword("pwd123456");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		String[] routingKeys = new String[]{"*.orange.*"};
		String queueName="";
		if(para==0){
			for (String severity : routingKeys) {
				channel.queueDeclare(severity, false, false, false, null);
				System.out.println("ReceiveLogsTopic1 BindRoutingKey:" + severity);
			}
		}else{
			channel.exchangeDeclare(EXCHANGE_NAME, "topic");
			queueName = channel.queueDeclare().getQueue();
			for (String severity : routingKeys) {
				channel.queueBind(queueName, EXCHANGE_NAME, severity);
				System.out.println("ReceiveLogsTopic1 exchange:"+EXCHANGE_NAME+", queue:"+queueName+", BindRoutingKey:" + severity);
			}
		}

		System.out.println("ReceiveLogsTopic1 [*] Waiting for messages. To exit press CTRL+C");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println("ReceiveLogsTopic1 [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
			}
		};
		if(para==0){
			for (String severity : routingKeys) {
				channel.basicConsume(severity, true, consumer);
			}

		}else{
			channel.basicConsume(queueName, true, consumer);
		}
	}
}