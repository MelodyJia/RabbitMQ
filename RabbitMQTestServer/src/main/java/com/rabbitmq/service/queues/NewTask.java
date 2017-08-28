/**
 * TODO
 * 
 */
package com.rabbitmq.service.queues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;
import com.rabbitmq.service.MyJdbc;

/**
 * @author
 * 
 */
public class NewTask {

	private static final String TASK_QUEUE_NAME = "task_queue1";

	public static void initNewTask() throws  Exception {

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.3.2");
		factory.setUsername("asdf");
		factory.setPassword("pwd123456");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

		MyJdbc mj= new MyJdbc();
		for(int i = 0 ; i < 1000; i++){
			String message = "TestQueues! " + i;
			//sql操作
			String sql="insert into testUser(userName) values(?)";
			mj.executeUpdate(0,sql,message);

			channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
			System.out.println(" [x] Sent '" + message + "'");
		}
		channel.close();
		connection.close();
	}
}
