package com.rabbitmq.controller;

import com.rabbitmq.service.ExternalTransaction.TransactionServer;
import com.rabbitmq.service.RabbitMQDemo.ProduceBasicConsume;
import com.rabbitmq.service.RabbitMQDemo.ProduceBasicGet;
import com.rabbitmq.service.SingleQueue.producer;
import com.rabbitmq.service.publish.EmitLog;
import com.rabbitmq.service.queues.NewTask;
import com.rabbitmq.service.routing.RoutingSendDirect;
import com.rabbitmq.service.rpc.RPCServer;
import com.rabbitmq.service.topic.TopicSend;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by jialianqing on 2016/10/18 0018.
 */
@Controller
public class MainController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {

        return "index";
    }
    @RequestMapping(value = "/initProduceBasicGet", method = RequestMethod.GET)
    public String initProduceBasicGet() throws IOException, TimeoutException {

        //RPCServer pd = new RPCServer();
        ProduceBasicGet pd =new ProduceBasicGet();
        pd.initProduce();
        return "successBasicGet";
    }
    @RequestMapping(value = "/initProduceConsume", method = RequestMethod.GET)
    public String initProduceConsume() throws IOException, TimeoutException {

        //RPCServer pd = new RPCServer();
        ProduceBasicConsume pd =new ProduceBasicConsume();
        pd.initProduce("testRabbitmq");
        return "successConsume";
    }
    @RequestMapping(value = "/initProducePublish", method = RequestMethod.GET)
    public String initProducePublish() throws Exception {

        //RPCServer pd = new RPCServer();
        EmitLog pd =new EmitLog();
        pd.initEmitLog();
        return "successPublish";
    }
    @RequestMapping(value = "/initProduceQueue", method = RequestMethod.GET)
    public String initProduceQueue() throws Exception {

        //RPCServer pd = new RPCServer();
        //RPCServer pd = new RPCServer();
        NewTask pd =new NewTask();
        pd.initNewTask();
        return "successQueue";
    }
    @RequestMapping(value = "/initProduceTopic", method = RequestMethod.GET)
    public String initProduceTopic() throws Exception {

        //RPCServer pd = new RPCServer();
        TopicSend pd =new TopicSend();
        pd.initTopic(1);
        return "successTopic";
    }
    @RequestMapping(value = "/initProduceTopic1", method = RequestMethod.GET)
    public String initProduceTopic1() throws Exception {

        //RPCServer pd = new RPCServer();
        TopicSend pd =new TopicSend();
        pd.initTopic(0);
        return "successTopic";
    }
    @RequestMapping(value = "/initSingleQueue", method = RequestMethod.GET)
    public String initSingleQueue() throws Exception {
         producer pd=new producer();
        pd.initProducer();
        return "successSingleQueue";
    }

    @RequestMapping(value = "/initRouting", method = RequestMethod.GET)
    public String initRouting() throws Exception {
        RoutingSendDirect pd = new RoutingSendDirect();
        pd.initRouting(1);
        return "successRouting";
    }
    @RequestMapping(value = "/initRouting1", method = RequestMethod.GET)
    public String initRouting1() throws Exception {
        RoutingSendDirect pd = new RoutingSendDirect();
        pd.initRouting(0);
        return "successRouting";
    }
    @RequestMapping(value = "/initProduceRPC", method = RequestMethod.GET)
    public String initinitProduceRPC() throws Exception {
        RPCServer pd = new RPCServer();
        pd.initRPC();
        return "successRPC";
    }

    @RequestMapping(value = "/toDotnet", method = RequestMethod.GET)
    public String toDotnet() throws Exception {
        TransactionServer ts = new TransactionServer();
        ts.initServer("","Q-DotNet");
        return "successTransaction";
    }
    @RequestMapping(value = "/toDotnet1", method = RequestMethod.GET)
    public String toDotnet1() throws Exception {
        TransactionServer ts = new TransactionServer();
        ts.initServer("E-DotNet","Q-DotNet");
        return "successTransaction";
    }
    @RequestMapping(value = "/toPython", method = RequestMethod.GET)
    public String toPython() throws Exception {
        TransactionServer ts = new TransactionServer();
        ts.initServer("","Q-Python");
        return "successTransaction";
    }
    @RequestMapping(value = "/toPython1", method = RequestMethod.GET)
    public String toPython1() throws Exception {
        TransactionServer ts = new TransactionServer();
        ts.initServer("E-Python","R-Python");
        return "successTransaction";
    }
    @RequestMapping(value = "/toRuby", method = RequestMethod.GET)
    public String toRuby() throws Exception {
        TransactionServer ts = new TransactionServer();
        ts.initServer("","Q-Ruby");
        return "successTransaction";
    }
    @RequestMapping(value = "/toRuby1", method = RequestMethod.GET)
    public String toRuby1() throws Exception {
        TransactionServer ts = new TransactionServer();
        ts.initServer("E-Ruby","R-Ruby");
        return "successTransaction";
    }
    @RequestMapping(value = "/toNodejs", method = RequestMethod.GET)
    public String toNodejs() throws Exception {
        TransactionServer ts = new TransactionServer();
        ts.initServer("","Q-Nodejs");
        return "successTransaction";
    }
    @RequestMapping(value = "/toNodejs1", method = RequestMethod.GET)
    public String toNodejs1() throws Exception {
        TransactionServer ts = new TransactionServer();
        ts.initServer("E-Nodejs","R-Nodejs");
        return "successTransaction";
    }
}

