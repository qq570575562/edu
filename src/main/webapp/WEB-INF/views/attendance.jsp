<%--
  Created by IntelliJ IDEA.
  User: zsjlo
  Date: 2017/12/17
  Time: 21:04
  To change this trolelate use File | Settings | File Trolelates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>考勤管理</title>
    <%@include file="/static/commons/commons.jsp" %>
    <script type="text/javascript" src="/static/js/attendance.js"></script>
</head>
<body>


<table id="attendance_datagrid"></table>

<div id="attendance_toolbar">
    <a class="easyui-linkbutton" data-options="plain:true" iconCls="icon-sign" data-cmd="signIn">签到</a>
    <a class="easyui-linkbutton" data-options="plain:true" iconCls="icon-sign_out" data-cmd="signOut">签退</a>

    <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
    <shiro:hasPermission name="attendance:resignIn">
    <a class="easyui-linkbutton" data-options="plain:true" iconCls="icon-resignIn" data-cmd="resignIn">补签</a>
    </shiro:hasPermission>


    <input id="username" class="easyui-textbox" style="width:120px" name="username" data-options="prompt:'员工姓名'">
    <input id="beginDate"
           name="beginDate"
           type="text"
           class="easyui-datebox"
           style="width: 125px"
           data-options="prompt:'开始时间'"> </input>
    ~<input id="endDate"
            name="endDate"
            type="text"
            class="easyui-datebox"
            style="width: 125px"
            data-options="prompt:'结束时间'"> </input>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-search',plain:true" data-cmd="searchForm">查询</a>
    <a class="easyui-linkbutton" iconCls="icon-exportXls" plain="true" data-cmd="exportXls">导出</a>
</div>

<div id="attendance_dialog">
    <form id="attendance_form" method="post">
        <input type="hidden" name="id">
        <table align="center">
            <tr>
                <td>员工名称:</td>
                <td>
                    <input class="easyui-combobox" type="text" name="employee.id"
                           data-options="url:'/employee/selectAll.do',valueField:'id',textField:'username',panelHeight:'auto',panelMaxHeight:200,"/>
                </td>
            </tr>
            <tr>
                <td>有效工作日:</td>
                <td>
                    <input class="easyui-datetimebox" type="text" name="signInDay"/>
                </td>
            </tr>
            <tr>
                <td>补签事由:</td>
                <td>
                    <input class="easyui-textbox" type="text" name="cause"/>
                </td>
            </tr>
            
        </table>
    </form>
</div>



<div id="dialog_btns">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-save',plain:true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" data-options="iconCls:'icon-cancel',plain:true" data-cmd="cancel">取消</a>
</div>
</body>
</html>
