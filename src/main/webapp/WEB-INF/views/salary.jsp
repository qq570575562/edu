<%--
  Created by IntelliJ IDEA.
  User: zsjlo
  Date: 2017/12/17
  Time: 21:04
  To change this trolelate use File | Settings | File Trolelates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
  <head>
    <title>薪资管理</title>
     <%@include file="/static/commons/commons.jsp" %>
    <script type="text/javascript" src="/static/js/salary.js"></script>

  </head>
  <body style="width:100%;height:100%;">
  <table id="salary_datagrid"></table>
<div id="salary_toolbar">
     <shiro:hasPermission name="salary:saveOrUpdate">
    <a class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
     </shiro:hasPermission>
    <shiro:hasPermission name="salary:delete">
    <a class="easyui-linkbutton" iconCls="icon-delete" plain="true" data-cmd="remove">删除</a>
    </shiro:hasPermission>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    <shiro:hasPermission name="salary:reloadAll">
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="loadAll">加载</a>
    </shiro:hasPermission>
   

    <input id="keyword" name="keyword" class="easyui-textbox" style="width: 110px;" data-options="prompt:'员工姓名'">
    <input id="beginDate" name="beginDate" class="easyui-datebox" style="width: 120px;" data-options="prompt:'开始时间'">~
    <input id="endDate" name="endDate" class="easyui-datebox" style="width: 120px;" data-options="prompt:'结束时间'">
    <a class="easyui-linkbutton"
       iconCls="icon-search"
       plain="true"
       data-options="prompt:'员工名称:' "
       data-cmd="search">查询</a>
    <a class="easyui-linkbutton" iconCls="icon-calc" plain="true" data-cmd="adjust">核算</a>
    <shiro:hasPermission name="salary:payOff">
    <a class="easyui-linkbutton" iconCls="icon-calculation" plain="true" data-cmd="payOff">结算</a>
    </shiro:hasPermission>
    <a class="easyui-linkbutton" iconCls="icon-exportXls" plain="true" data-cmd="exportXls">导出</a>
    <a class="easyui-linkbutton" iconCls="icon-importXls" plain="true" data-cmd="importXls">导入
</div>

<div id="salary_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>


<div id="salary_import">
    <form id="import_form" action="#" method="post" enctype="multipart/form-data">
        <a id="download" class="easyui-linkbutton" iconCls="icon-excelModle" plain="true" data-cmd="download">模板下载</a><br>
        工资表上传:<input id="salaryes" name="file" class="easyui-filebox" style="width:300px"
                     data-options="accept:'application/vnd.ms-excel',multiple:false,buttonText:'上传工资表'"><br>
        <a class="easyui-linkbutton" iconCls="icon-ok" plain="true" data-cmd="upload">提交</a>
    </form>
</div>

<div id="salary_dialog">
    <form id="salary_form" method="post">
        <input type="hidden" name="id">
        <h3 id="realname_id" style="color: gray;" align="center"></h3>
        <table align="center" style="margin-top: 20px">
            <tr align="right">
                <td>员工编号:</td>
                <td><input class="easyui-textbox" name="employee.id" readonly="readonly"/></td>
            </tr>
            <tr align="right">
                <td>员工工资:</td>
                <td><input id="salary_id" class="easyui-textbox" name="salary"/></td>
            </tr>
            <tr align="right">
                <td>月&emsp;&emsp;份:</td>
                <td><input class="easyui-textbox" name="month" readonly="readonly"/></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
