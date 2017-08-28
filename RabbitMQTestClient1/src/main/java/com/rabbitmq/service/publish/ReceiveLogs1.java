/**
 * TODO
 * 
 */
package com.rabbitmq.service.publish;

/**
 * @author hushuang
 *
 */

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

public class ReceiveLogs1 {
	private static final String EXCHANGE_NAME = "Exechange_pub";

	public static void initReceiveLog1() throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.3.2");
		factory.setUsername("asdf");
		factory.setPassword("pwd123456");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
		String queueName = channel.queueDeclare().getQueue();
		channel.queueBind(queueName, EXCHANGE_NAME, "");

		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
		Thread.sleep(2000);

		//前台消费
		QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queueName, true, consumer);
		String message = new String(consumer.nextDelivery().getBody());

		System.out.println(" [x] Received '" + message + "'");



		//循环消费1
		/*QueueingConsumer consumer = new QueueingConsumer(channel);
		channel.basicConsume(queueName,true,consumer);
		while (true){
			Thread.sleep(500);
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println("received message ["+message+"] from "+queueName);
		}*/


		//循环消费2
/*		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(" [x] Received '" + message + "'");
			}
		};
		channel.basicConsume(queueName, true, consumer);*/
	}
	}

