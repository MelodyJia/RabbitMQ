/**
 * TODO
 * 
 */
package com.rabbitmq.service.Exception;

/**
 * @author hushuang
 *
 */
import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiveException {
	private static final String EXCHANGE_NAME = "Exchange_direct";
	private static final String[] routingKeys = new String[]{"info" ,"warning", "error"};
	public static void initException() throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		factory.setUsername("guest");
		factory.setPassword("guest");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		String	queueName = channel.queueDeclare().getQueue();
		for (String severity : routingKeys) {
			channel.queueBind(queueName, EXCHANGE_NAME, severity);
			System.out.println("ReceiveLogsDirect1 exchange:"+EXCHANGE_NAME+", queue:"+queueName+", BindRoutingKey:" + severity);
		}
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				if(envelope.getRoutingKey().equals("error")){
					throw new IllegalArgumentException("Wrong Parameter!");
				}
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
