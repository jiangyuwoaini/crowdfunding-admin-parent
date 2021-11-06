<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <base href="http://${pageContext.request.serverName }:${pageContext.request.serverPort }${pageContext.request.contextPath }/" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/login.css">
    <script src="${pageContext.request.contextPath}/static/jquery/jquery-2.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/bootstrap/js/bootstrap.min.js"></script>
    <style>
        #msg {
            color: #ff0000;
            font-size: 16px;
        }
    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="/admin/to/login.html" style="font-size:32px;">羲筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">
    <form action="admin/login.html" method="post" id="adminLogin" class="form-signin" role="form">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 管理员登录</h2>
        <p id="msg">${MESSAGE}</p>
        <div class="form-group has-success has-feedback">
            <input type="text" id="loginAcct" name="loginAcct" class="form-control" id="inputSuccess4" placeholder="请输入登录账号" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="password" id="password" name="password" class="form-control" id="inputSuccess4" placeholder="请输入登录密码" style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <input onclick="adminLogin()" class="btn btn-lg btn-success btn-block" value="登录"/>
    </form>
</div>
<script type="text/javascript">
    function adminLogin() {
        let loginAcct = $("#loginAcct").val()
        let password = $("#password").val()
        if(loginAcct == "" || password == "") {
            $("#msg").text("账户名或者密码不能为空！");
            return false;
        }
        $("#adminLogin").submit();
    }
</script>
</body>
</html>
