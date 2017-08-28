/**
 * TODO
 * 
 */
package com.rabbitmq.service.topic;

/**
 * @author hushuang
 *
 */

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.service.MyJdbc;

public class TopicSend {

	private static final String EXCHANGE_NAME = "Exchange_topic";

	public static void initTopic(int para) {
		Connection connection = null;
		Channel channel = null;
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("192.168.3.2");
			factory.setUsername("asdf");
			factory.setPassword("pwd123456");
			MyJdbc mj= new MyJdbc();
			//sql操作x
			String sql="select * from testUser";
			mj.executeUpdate(1,sql);
			Thread.sleep(2000);
			connection = factory.newConnection();
			channel = connection.createChannel();
			String routingKeys = "*.orange.*";
				if (para == 1) {
					channel.exchangeDeclare(EXCHANGE_NAME, "topic");
					for(int i = 0 ; i < 1000; i++) {
						String message = "Send the message level:" + routingKeys;
						channel.basicPublish(EXCHANGE_NAME, routingKeys, null, message.getBytes());
						System.out.println(" [Exchange is not null] Sent '" + routingKeys + "':'" + message + "'");
					}
				} else {
					for (int i = 0; i < 1000; i++) {
						channel.queueDeclare(routingKeys, false, false, false, null);
						String message = "Send the message level:" + routingKeys;
						channel.basicPublish("", routingKeys, null, message.getBytes());
						System.out.println(" [Exchange is null] Sent '" + routingKeys + "':'" + message + "'");
					}
				}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (connection != null) {
				try {
					channel.close();
					connection.close();
				} catch (Exception ignore) {
				}
			}
		}
	}
}