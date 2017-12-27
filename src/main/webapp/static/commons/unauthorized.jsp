<%--
  Created by IntelliJ IDEA.
  User: zsjlo
  Date: 2017/12/26
  Time: 19:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>没有权限操作</title>
     <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="favicon.ico"> <link href="/static/bootstrap/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="/static/bootstrap/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="/static/bootstrap/css/animate.css" rel="stylesheet">
    <link href="/static/bootstrap/css/style.css?v=4.1.0" rel="stylesheet">
  </head>
  <body class="gray-bg">


    <div class="middle-box text-center animated fadeInDown">
        <h1>操作失败</h1>
        <h3 class="font-bold">您没有操作的权限</h3>

        <div class="error-desc">
            可以试着联系管理员...
            <br/>您可以返回主页看看
            <br/><a href="index.do" class="btn btn-primary m-t">主页</a>
        </div>
    </div>

    <!-- 全局js -->
    <script src="/static/bootstrap/js/jquery.min.js?v=2.1.4"></script>
    <script src="/static/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>


</body>

</html>
