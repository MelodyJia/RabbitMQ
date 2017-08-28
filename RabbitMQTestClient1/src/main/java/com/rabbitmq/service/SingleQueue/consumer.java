/**
 * TODO
 * 
 */
package com.rabbitmq.service.SingleQueue;

import com.rabbitmq.client.*;
import com.rabbitmq.service.MyJdbc;

/**
 * ��Ϣ������
 * 
 * @author jialianqing
 * 
 */
public class consumer {

    private final static String QUEUE_NAME = "singlequeue";

    public static void initConsumer() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.3.2");
        factory.setUsername("asdf");
        factory.setPassword("pwd123456");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        MyJdbc mj= new MyJdbc();
        GetResponse result = channel.basicGet(QUEUE_NAME, true);
        Thread.sleep(2000);
        if (result != null) {

            Envelope envelope = result.getEnvelope();
            long deliveryTag = envelope.getDeliveryTag();
            System.out.println("Message : " + new String(result.getBody()));
            String sql="select * from testUser where userName=?";
            mj.executeUpdate(1,sql,result.getBody());
            channel.basicAck(deliveryTag, false);
        }

    }
}

