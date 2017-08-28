/**
 * TODO
 * 
 */
package com.rabbitmq.service.routing;

/**
 * @author jialianqing
 *
 */
import com.rabbitmq.client.*;

import java.io.IOException;

public class ReceiveLogsDirect1 {
	private static String EXCHANGE_NAME = "Exchange_direct";
	private static final String queue = "queue1";
	private static final String routingKeys = "info";
	public static void initRouting1(int para) throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.3.2");
		factory.setUsername("asdf");
		factory.setPassword("pwd123456");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		String queueName="";
		if(para==0){
				channel.queueDeclare(queue, false, false, false, null);
				System.out.println("ReceiveLogsDirect1 BindRoutingKey:" + queue);
		}else {
			channel.exchangeDeclare(EXCHANGE_NAME, "direct");
			queueName = channel.queueDeclare().getQueue();
				channel.queueBind(queueName, EXCHANGE_NAME, routingKeys);
				System.out.println("ReceiveLogsDirect1 exchange:"+EXCHANGE_NAME+", queue:"+queueName+", BindRoutingKey:" + routingKeys);
		}
		try {
			Thread.sleep(1000); //
		} catch (InterruptedException _ignored) {
			Thread.currentThread().interrupt();
		}
		System.out.println("ReceiveLogsDirect1 [*] Waiting for messages. To exit press CTRL+C");
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
/*				System.out.println("headers1:"+properties.getHeaders().get("aaa"));
				System.out.println("headers2:"+properties.getHeaders().get("bbb"));*/
				System.out.println(" [x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
			}
		};
		if(para==0){
				channel.basicConsume(queue, true, consumer);
		}else{
			channel.basicConsume(queueName, true, consumer);
		}

	}
}
