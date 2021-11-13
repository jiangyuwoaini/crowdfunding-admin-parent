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
                <li class="active">新增</li>
            </ol>
            <div class="panel panel-default">
                <div class="panel-heading">表单数据<div style="float:right;cursor:pointer;" data-toggle="modal" data-target="#myModal"><i class="glyphicon glyphicon-question-sign"></i></div></div>
                <div class="panel-body">
                    <form action="/admin/add.html" method="post" id="addForm" role="form">
                        <div class="form-group">
                            <label for="loginAcct">登陆账号<span style="color:red">*</span></label>
                            <input type="text" id="loginAcct" name="loginAcct" class="form-control" placeholder="请输入登陆账号">
                        </div>
                        <div class="form-group">
                            <label for="password">登陆密码<span style="color:red">*</span></label>
                            <input type="password" id="password"  name="password" class="form-control"  placeholder="请输入登陆密码">
                        </div>
                        <div class="form-group">
                            <label for="realName">用户昵称<span style="color:red">*</span></label>
                            <input type="text" id="realName" name="realName" class="form-control" placeholder="请输入用户名称">
                        </div>
                        <div class="form-group">
                            <label for="email">邮箱地址<span style="color:red">*</span></label>
                            <input type="email" id="email" name="email" class="form-control"  placeholder="请输入邮箱地址">
                            <p class="help-block label label-warning">请输入合法的邮箱地址, 格式为： xxxx@xxxx.com</p>
                        </div>
                        <button type="button" id="toAdd" class="btn btn-success"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                        <button type="reset" class="btn btn-danger"><i class="glyphicon glyphicon-refresh"></i> 重置</button>
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

    $("#toAdd").on("click",function(){
        let loginAcct = $("#loginAcct").val();
        let password = $("#password").val();
        let realName = $("#realName").val();
        let email = $("#email").val();
        let reg = /^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;//邮箱校验
        if(loginAcct.val == "" || password == "" || realName == "" || email == "") {
            layer.msg("必填项不能为空！");
            return false;
        }
        if(!reg.test(email)) {
            layer.msg("邮箱不符合要求！");
            return false;
        }
        $.ajax({
            url : "/admin/check.json?loginAcct="+loginAcct,
            type: 'POST',
            contentType: 'application/json;charset=utf-8',//数据类型必须有
            async: true,//异步
            datatype : 'json',
            success : function(data) {
                if(data.code == "20000") {
                    $("#addForm").submit();
                    return;
                }else {
                    layer.msg(data.message);
                    return false;
                }
            }
        })
    });
</script>
</body>
</html>
