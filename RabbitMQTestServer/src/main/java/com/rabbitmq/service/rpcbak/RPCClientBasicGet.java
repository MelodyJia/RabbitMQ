/**
 * TODO
 * 
 */
package com.rabbitmq.service.rpcbak;

/**
 * @author jialianqing
 *
 */

import com.rabbitmq.client.*;
import com.rabbitmq.client.AMQP.BasicProperties;

import java.util.UUID;

public class RPCClientBasicGet {

	private Connection connection;
	private Channel channel;
	private String requestQueueName = "rpc_queue";
	private String replyQueueName;

	public RPCClientBasicGet() throws Exception {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		connection = factory.newConnection();
		channel = connection.createChannel();

		replyQueueName = channel.queueDeclare().getQueue();
	}

	public String call(String message) throws Exception {
		String response = null;
		String corrId = UUID.randomUUID().toString();

		BasicProperties props = new BasicProperties.Builder().correlationId(corrId).replyTo(replyQueueName).build();

		channel.basicPublish("", requestQueueName, props, message.getBytes("UTF-8"));
		boolean noAck = true;
		// 获取到消息的计数器
		int counter = 0;
		while (true) {
			GetResponse response1 = channel.basicGet(replyQueueName, noAck);
			if (response1 != null) {
				System.out.println("----------- receive message " + (++counter) + "-----------");
				System.out.println("Message : " + new String(response1.getBody()));
				response=new String(response1.getBody());
				Envelope envelope = response1.getEnvelope();
				long deliveryTag = envelope.getDeliveryTag();
				System.out.println("receive success.\n\n");
				if (!noAck) // 发送回复
				{
					channel.basicAck(deliveryTag, false);
				}
				break;
			}
		}
		return  response;
	}



	public void close() throws Exception {
		connection.close();
	}

	public static void main(String[] argv) {
		RPCClientBasicGet fibonacciRpc = null;
		String response = null;
		try {
			fibonacciRpc = new RPCClientBasicGet();

			System.out.println("RPCClient [x] Requesting fib(2)");
			response = fibonacciRpc.call("2");
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
