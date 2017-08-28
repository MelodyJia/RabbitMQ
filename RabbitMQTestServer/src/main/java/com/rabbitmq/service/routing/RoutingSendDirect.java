/**
 * TODO
 * 
 */
package com.rabbitmq.service.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.AMQP.BasicProperties.Builder;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author jialianqing
 *
 */
public class RoutingSendDirect {

    private static final String EXCHANGE_NAME = "Exchange_direct";
 // ·�ɹؼ���
 	private static final String routingKeys = "info";
    private static final String queue = "queue1";
    public static void initRouting(int para) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.3.2");
        factory.setUsername("asdf");
        factory.setPassword("pwd123456");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
   /*     MyJdbc mj= new MyJdbc();
        //sql操作x
        String sql="select * from testUser";
        mj.executeUpdate(1,sql);*/
        Thread.sleep(2000);
            Map<String,Object> headers =  new Hashtable<String, Object>();
            headers.put("aaa", "header1");
            headers.put("bbb", "header2");
            Builder properties = new Builder();
            properties.headers(headers);

            if (para == 1) {
                channel.exchangeDeclare(EXCHANGE_NAME, "direct");
                for(int i=0;i<3;i++) {
                    String message = "Send the message level:" + routingKeys;
                    channel.basicPublish(EXCHANGE_NAME, routingKeys, properties.build(),message.getBytes());
                    System.out.println(" [Exchange is not null] Sent '" + routingKeys + "':'" + message + "'");
                }
            } else {
                channel.queueDeclare(queue, false, false, false, null);
                for (int i = 0; i < 3; i++) {
                    String message = "Send the message level:" + queue;
                    channel.basicPublish("", queue, null, message.getBytes());
                    System.out.println(" [Exchange is null] Sent '" + queue + "':'" + message + "'");
                }
            }
        channel.close();
        connection.close();
    }
}