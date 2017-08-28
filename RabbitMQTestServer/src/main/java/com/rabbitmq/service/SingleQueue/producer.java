/**
 * TODO
 * 
 */
package com.rabbitmq.service.SingleQueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @author jialianqing
 *
 */
public class producer {

  private final static String QUEUE_NAME = "singlequeue";

  public static void initProducer() throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("192.168.3.2");
    factory.setUsername("asdf");
    factory.setPassword("pwd123456");
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();
    //sql操作x
/*    MyJdbc mj= new MyJdbc();
    String sql="select * from testUser";
    mj.executeUpdate(1,sql);*/
    Thread.sleep(2000);
      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
    for(int i=0;i<1000;i++) {
      String message = "OneProducerOneConsumer"+i;
      channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
      System.out.println("P [x] Sent '" + message + "'");
    }
    channel.close();
    connection.close();
  }
}
