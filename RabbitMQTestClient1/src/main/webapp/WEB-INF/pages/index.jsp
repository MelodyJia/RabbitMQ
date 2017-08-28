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

    <!-- 新 Bootstrap 核心 CSS 文件 -->
 <link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<%--<a href="/initConsumerBasicGet">RabbitMQ消费者_BasicGet</a><br>
<a href="/initConsumerBasicConsume">RabbitMQ消费者_BasicConsume</a><br>--%>
<h1>Java内部测试</h1>
<a href="<%=contextPath%>/initSingleQueue">RabbitMQ消费者_SingleQueue(basicGet前台消费)</a><br>
<a href="<%=contextPath%>/initSingleQueue1">RabbitMQ消费者_SingleQueue(后台循环消费)</a><br>
<a href="<%=contextPath%>/initQueue1">RabbitMQ消费者_Queue1</a><br>
<a href="<%=contextPath%>/initPublish1">RabbitMQ消费者_Publish1</a><br>
<a href="<%=contextPath%>/initRouting1">RabbitMQ消费者_Routing1(Exchange不为空)</a><br>
<a href="<%=contextPath%>/initRouting11">RabbitMQ消费者_Routing1(Exchange为空)</a><br>
<a href="<%=contextPath%>/initTopic1">RabbitMQ消费者_Topic1(Exchange不为空)</a><br>
<a href="<%=contextPath%>/initTopic11">RabbitMQ消费者_Topic1(Exchange为空)</a><br>
<a href="<%=contextPath%>/rabbitmq">RabbitMQ消费者_RPC</a><br>
<a href="<%=contextPath%>/initException">RabbitMQ消费者_异常</a><br>
<h1>多语言跨应用</h1>
<a href="<%=contextPath%>/toJava">fromOtherLanguage(不用Exchange)</a><br>
<a href="<%=contextPath%>/toJava1">fromOtherLanguage(使用Exchange)</a><br>
</body>
</html>