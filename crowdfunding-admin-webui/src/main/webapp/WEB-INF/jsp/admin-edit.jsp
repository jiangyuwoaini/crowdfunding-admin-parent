<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="include-head.jsp" %>
<style>
    .banner{
        width: 150px;
        height: 25px;
        background: #fbf7e1;
        /* position: absolute; */
        margin:0 auto;
        border:none;
        border-radius: 10px;
    }
</style>
<body>
<%@include file="include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="include-sidebar.jsp" %>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <ol class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">数据列表</a></li>
                <li class="active">修改</li>
            </ol>
            <div class="panel panel-default">

                <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <form action="/admin/edit.do" id="updateForm" method="post" role="form">
                        <input type="hidden" name="id" value="${admin.id}">
                        <div class="form-group">
                            <label for="loginAcct">登陆账号<span style="color:red">*</span></label>
                            <input type="text" readonly="readonly" class="form-control" name="loginAcct" id="loginAcct" value="${admin.loginAcct}">
                        </div>
                        <div class="form-group">
                            <label for="password">登陆密码<span style="color:red">*</span></label>
                            <input type="text" class="form-control" name="password" id="password" value="${admin.password}">
                        </div>
                        <div class="form-group">
                            <label for="realName">用户名称<span style="color:red">*</span></label>
                            <input type="text" class="form-control" name="realName" id="realName" value="${admin.realName}">
                        </div>
                        <div class="form-group">
                            <label for="email">邮箱地址<span style="color:red">*</span></label>
                            <input type="email" class="form-control" name="email" id="email" value="${admin.email}">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button type="button" id="toUpdate" class="btn btn-success"><i class="glyphicon glyphicon-edit"></i> 修改</button>
                        <button type="button" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="jquery/jquery-2.1.1.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>
<script src="script/docs.min.js"></script>
<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function(){
            if ( $(this).find("ul") ) {
                $(this).toggleClass("tree-closed");
                if ( $(this).hasClass("tree-closed") ) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
    });
    $("tbody .btn-success").click(function(){
        window.location.href = "assignRole.html";
    });
    $("tbody .btn-primary").click(function(){
        window.location.href = "edit.html";
    });

    $("#toUpdate").on("click",function(){
        let loginAcct = $("#loginAcct").val();
        let password = $("#password").val();
        let realName = $("#realName").val();
        let email = $("#email").val();
        let adminId = $("#adminId").val();

        let reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;//邮箱校验
        if(loginAcct.val == "" || password == "" || realName == "" || email == "") {
            layer.msg("必填项不能为空！");
            return false;
        }
        if(!reg.test(email)) {
            layer.msg("邮箱不符合要求！");
            return false;
        }
        $("#updateForm").submit();
    });
</script>
</body>
</html>
