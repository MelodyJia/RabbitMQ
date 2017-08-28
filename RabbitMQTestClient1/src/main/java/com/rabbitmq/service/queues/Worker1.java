/**
 * TODO
 * 
 */
package com.rabbitmq.service.queues;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.GetResponse;

public class Worker1 {
	private static final String TASK_QUEUE_NAME = "task_queue1";

	public static void initWork1() throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.3.2");
		factory.setUsername("asdf");
		factory.setPassword("pwd123456");
		final Connection connection = factory.newConnection();
		final Channel channel = connection.createChannel();
		boolean noAck = true;
		//channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
		channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);
		System.out.println("Worker1 [*] Waiting for messages. To exit press CTRL+C");
		Thread.sleep(2000);
		//channel.basicQos(1);
		while (true) {
			GetResponse result = channel.basicGet(TASK_QUEUE_NAME, noAck);
			if(result!=null){
				System.out.println("Message : " + new String(result.getBody()));
				if(!noAck) {
					channel.basicAck(result.getEnvelope().getDeliveryTag(), false);
				}

			}else{
				break;
			}
		}

		/*channel.basicQos(1);
		//sql操作
		 final MyJdbc mj= new MyJdbc();
		 Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				String sql="select * from testUser where userName=?";
				mj.executeUpdate(1,sql,message);
				System.out.println("Worker1 [x] Received '" + message + "'");
				try {
					doWork();
				} finally {
					System.out.println("Worker1 [x] Done");
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
			}*/

	}

}
