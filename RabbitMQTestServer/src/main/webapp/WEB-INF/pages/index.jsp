<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
 <title>首页</title>
</head>
<body>
<%--<a href="/initProduceBasicGet">RabbitMQ生产者_BasicGet</a><br>
<a href="/initProduceConsume">RabbitMQ生产者_BasicConsume</a><br>--%>
<h1>Java内部测试</h1>
<a href="<%=contextPath%>/initSingleQueue">RabbitMQ生产者_SingleQueue</a><br>
<a href="<%=contextPath%>/initProduceQueue">RabbitMQ生产者_Queue</a><br>
<a href="<%=contextPath%>/initProducePublish">RabbitMQ生产者_Publish</a><br>
<a href="<%=contextPath%>/initRouting">RabbitMQ生产者_Routing(Exchange不为空)</a><br>
<a href="<%=contextPath%>/initRouting1">RabbitMQ生产者_Routing(Exchange为空)</a><br>
<a href="<%=contextPath%>/initProduceTopic">RabbitMQ生产者_Topic(Exchange不为空)</a><br>
<a href="<%=contextPath%>/initProduceTopic1">RabbitMQ生产者_Topic(Exchange为空)</a><br>
<a href="<%=contextPath%>/initProduceRPC">RabbitMQ生产者_RPC</a><br>
<h1>多语言跨应用</h1>
<a href="<%=contextPath%>/toDotnet">toDotnet(不用Exchange)</a><br>
<a href="<%=contextPath%>/toDotnet1">toDotnet(使用Exchange)</a><br>
<a href="<%=contextPath%>/toPython">toPython(不用Exchange)</a><br>
<a href="<%=contextPath%>/toPython1">toPython(使用Exchange)</a><br>
<a href="<%=contextPath%>/toRuby">toRuby(不用Exchange)</a><br>
<a href="<%=contextPath%>/toRuby1">toRuby(使用Exchange)</a><br>
<a href="<%=contextPath%>/toNodejs">toNodejs(不用Exchange)</a><br>
<a href="<%=contextPath%>/toNodejs1">toNodejs(使用Exchange)</a><br>
</body>
</html>