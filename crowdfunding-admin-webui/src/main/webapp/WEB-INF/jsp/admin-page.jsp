<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-CN">
<%@include file="include-head.jsp" %>
<body>
<%@include file="include-nav.jsp" %>
<div class="container-fluid">
    <div class="row">
        <%@include file="include-sidebar.jsp" %>

        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form action="/admin/listPage.html" method="post" class="form-inline" id="admin-form" role="form" style="float:left;">
                        <input type="hidden" name="currentPage" id="currentPage" value="${listResultEntity.page.currentPage}">
                        <input type="hidden" name="remainingPage" id="remainingPage" value="${listResultEntity.page.remainingPage}">
                        <input type="hidden" name="pageCount" id="pageCount" value="${listResultEntity.page.pageCount}">
                        ${listResultEntity.page.remainingPage}
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
                    <button type="button" class="btn btn-primary" style="float:right;" onclick="window.location.href='add.html'"><i class="glyphicon glyphicon-plus"></i> 新增</button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered" >
                            <thead>
                            <tr >
                                <th width="30">#</th>
                                <th width="30"><input type="checkbox"></th>
                                <th>账号</th>
                                <th>名称</th>
                                <th>邮箱地址</th>
                                <th width="100">操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${listResultEntity.data }" var="data" varStatus="id">
                            <tr>
                                <td>${id.count}</td>
                                <td><input type="checkbox"></td>
                                <td>${data.loginAcct}</td>
                                <td>${data.realName}</td>
                                <td>${data.email}</td>
                                <td>
                                    <button type="button" class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>
                                    <button type="button" class="btn btn-primary btn-xs"><i class=" glyphicon glyphicon-pencil"></i></button>
                                    <button type="button" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>
                                </td>
                            </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr >
                                <td colspan="6" align="center">
                                    <ul class="pagination">
                                        <li><a>总数据: ${listResultEntity.page.size}</a></li>
                                        <li><a>当前页数: ${listResultEntity.page.currentPage}</a></li>
                                        <li><a>&nbsp;每页显示:
                                            <select name="select_count" onchange="listPage(this.value,false)">
                                                <option value ="10" <c:if test="${listResultEntity.page.pageCount == 10 }">selected</c:if>>10</option>
                                                <option value ="20" <c:if test="${listResultEntity.page.pageCount == 20 }">selected</c:if>>20</option>
                                                <option value="50" <c:if test="${listResultEntity.page.pageCount == 50 }">selected</c:if>>50</option>
                                                <option value="100" <c:if test="${listResultEntity.page.pageCount == 100 }">selected</c:if>>100</option>
                                            </select>
                                            条
                                        </a>
                                        </li>
                                        <li><a href="javascript:void(0);" onclick="listPage(-1,true)">上一页</a></li>
                                        <li>
                                            <a>
                                                页数:<input style="width: 50px;height: 20px;" type="text" id="toPage" />
                                            </a>
                                        </li>
                                        <li>
                                            <a href="javascript:void(0);" onclick="listPage('',true)">跳至</a>
                                        </li>
                                        <li><a href="javascript:void(0);" onclick="listPage(1,true)">下一页</a></li>
                                        <li><a>剩余页数: ${listResultEntity.page.remainingPage}</a></li>
                                    </ul>
                                </td>
                            </tr>
                            </tfoot>
                        </table>
                    </div>
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

    /**
     *
     * @param value 数量
     * @param flag ture则是页数 false则是每页的总数量
     */
    function listPage(value,flag){
        //1,判断value是否数字
        let toPage = $("#toPage");
        let currentPage = $("#currentPage");
        let pageCount = $("#pageCount");
        let remainingPage = $("#remainingPage");
        if(value =="") { //如果value值为空 则获取跳转页
            if (isNaN(parseInt(toPage.val()))){ //判断输入的是否是有效字符 并且不能大于剩余页数
                layer.msg("页数不是整数！")
                return false;
            }else if(parseInt(toPage.val()) > parseInt(remainingPage.val()) + parseInt(currentPage.val())){
                layer.msg("不能大于剩余页数！")
                return false;
            }
            currentPage.val(toPage.val());
        }else if(flag){
            if(value == -1 && parseInt(currentPage.val()) == 1){
                layer.msg("已到最低页数！");
                return false;
            }
            if(value == 1 && parseInt(remainingPage.val()) ==0){
                layer.msg("已到最高页数！");
                return false;
            }
            currentPage.val(parseInt(currentPage.val()) + parseInt(value));
        }else{
            //如果修改的是每页数量 那就重置页数,从第一页1开始
            currentPage.val(1);
            pageCount.val(value);
        }
        $("#admin-form").submit();
    }
</script>
</body>
</html>
