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
import com.rabbitmq.service.MyJdbc;

public class EmitLog {

    private static final String EXCHANGE_NAME = "Exechange_pub";

    public static void initEmitLog() throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.3.2");
        factory.setUsername("asdf");
        factory.setPassword("pwd123456");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        MyJdbc mj= new MyJdbc();
        //sql操作x
        String sql="select * from testUser";
        mj.executeUpdate(1,sql);
        Thread.sleep(2000);
		for(int i = 0 ; i < 1000; i++){
			String message = "Testfanout! " + i;
			 channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
		     System.out.println(" [x] Sent '" + message + "'");
		}
        channel.close();
        connection.close();
    }
}
