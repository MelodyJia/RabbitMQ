package com.rabbitmq.controller;

import com.rabbitmq.service.Exception.ReceiveException;
import com.rabbitmq.service.ExternalTransaction.TransactionClient;
import com.rabbitmq.service.RabbitMQDemo.ConsumerBasicConsume;
import com.rabbitmq.service.RabbitMQDemo.ConsumerBasicGet;
import com.rabbitmq.service.SingleQueue.consumer;
import com.rabbitmq.service.SingleQueue.consumer1;
import com.rabbitmq.service.publish.ReceiveLogs1;
import com.rabbitmq.service.queues.Worker1;
import com.rabbitmq.service.routing.ReceiveLogsDirect1;
import com.rabbitmq.service.rpc.RPCClient;
import com.rabbitmq.service.topic.ReceiveLogsTopic1;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by jialianqing on 2016/10/18 0018.
 */
@Controller
public class MainController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {

        return "index";
    }

    @RequestMapping(value = "/initConsumerBasicGet", method = RequestMethod.GET)
    public String initConsumerBasicGet() throws Exception {

        //RPCClientBasicGet pd = new RPCClientBasicGet();
        ConsumerBasicGet pd = new ConsumerBasicGet();
        pd.initConsumerBasicGet();
        return "successBasicGet";
    }
    @RequestMapping(value = "/initConsumerBasicConsume", method = RequestMethod.GET)
    public String initConsumerBasicConsume() throws Exception {

        // pd = new RPCClientBasicConsume();
        ConsumerBasicConsume pd = new ConsumerBasicConsume();
        pd.initConsumerBasicConsume();
        return "successConsume";
    }
    @RequestMapping(value = "/initPublish1", method = RequestMethod.GET)
    public String initConsumerPublish1() throws Exception {
        ReceiveLogs1 pd = new ReceiveLogs1();
        pd.initReceiveLog1();
        return "successPublish1";
    }
    @RequestMapping(value = "/initException", method = RequestMethod.GET)
    public String initException() throws Exception {
        ReceiveException pd = new ReceiveException();
        pd.initException();
        return "successException";
    }
    @RequestMapping(value = "/initQueue1", method = RequestMethod.GET)
    public String initQueue1() throws Exception {

        // pd = new RPCClientBasicConsume();
        Worker1 pd = new Worker1();
        pd.initWork1();
        return "successQueue1";
    }


    @RequestMapping(value = "/initTopic1", method = RequestMethod.GET)
    public String initTopic1() throws Exception {

        // pd = new RPCClientBasicConsume();
        ReceiveLogsTopic1 pd = new ReceiveLogsTopic1();
        pd.initReceiveLogsTopic1(1);
        return "successTopic1";
    }
    @RequestMapping(value = "/initTopic11", method = RequestMethod.GET)
    public String initTopic11() throws Exception {

        // pd = new RPCClientBasicConsume();
        ReceiveLogsTopic1 pd = new ReceiveLogsTopic1();
        pd.initReceiveLogsTopic1(0);
        return "successTopic1";
    }

    @RequestMapping(value = "/initSingleQueue", method = RequestMethod.GET)
    public String initSingleQueue() throws Exception {

        // pd = new RPCClientBasicConsume();
        consumer pd = new consumer();
        pd.initConsumer();
        return "successSingleQueue";
    }
    @RequestMapping(value = "/initSingleQueue1", method = RequestMethod.GET)
    public String initSingleQueue1() throws Exception {

        // pd = new RPCClientBasicConsume();
        consumer1 pd = new consumer1();
        pd.initConsumer();
        return "successSingleQueue";
    }
    @RequestMapping(value = "/initRouting1", method = RequestMethod.GET)
    public String initRouting1() throws Exception {
        ReceiveLogsDirect1 pd = new ReceiveLogsDirect1();
        pd.initRouting1(1);
        return "successRouting1";
    }
    @RequestMapping(value = "/initRouting11", method = RequestMethod.GET)
    public String initRouting11() throws Exception {
        ReceiveLogsDirect1 pd = new ReceiveLogsDirect1();
        pd.initRouting1(0);
        return "successRouting1";
    }
    @RequestMapping(value = "/initRPC", method = RequestMethod.GET)
    public String initRPC() throws Exception {
        RPCClient pd = new RPCClient();
        pd.initPrc();
        return "successRPC";
    }

    @RequestMapping(value = "/toJava", method = RequestMethod.GET)
    public String toJava() throws Exception {
        TransactionClient ts = new TransactionClient();
        ts.initClient("","Q-Java");
        return "successTransaction1";
    }
    @RequestMapping(value = "/toJava1", method = RequestMethod.GET)
    public String toJava1() throws Exception {
        TransactionClient ts = new TransactionClient();
        ts.initClient("E-Java","R-Java");
        return "successTransaction1";
    }
}

