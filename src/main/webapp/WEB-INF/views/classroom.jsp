<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/static/commons/commons.jsp" %>
    <script src="/static/js/classroom.js"></script>
    <script src="/static/plugin/jquery-easyui/base.js"></script>

    <title>教室</title>
</head>
<body style="width:100%;height:100%">
<div id="classroom_datagrid">
</div>
<div id="classroom_toolbar">
    <shiro:hasPermission name="classGrade:saveOrUpdate">
    <a class="easyui-linkbutton" data-cmd="add" data-options="plain:true,iconCls:'icon-add'">添加</a>
    <a class="easyui-linkbutton" data-cmd="edit" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="classGrade:changeState">
    <a class="easyui-linkbutton" id="changState_btn" data-cmd="changeState" data-options="plain:true,iconCls:'icon-stop'">停用</a>
    </shiro:hasPermission>
    <a class="easyui-linkbutton" data-cmd="load" data-options="plain:true,iconCls:'icon-reload'">刷新</a>

    <a id="show" class="easyui-linkbutton" data-cmd="show" data-options="plain:true,iconCls:'icon-see'">查看班级</a>
    <input id="search" class="easyui-searchbox"  data-options="prompt:'内容关键字查询'" />
</div>

<div id="classroom_buttons">
    <a class="easyui-linkbutton" data-cmd="saveOrUpdate" data-options="plain:true,iconCls:'icon-save'">保存</a>
    <a class="easyui-linkbutton" data-cmd="cancel" data-options="plain:true,iconCls:'icon-cancel'">取消</a>
</div>
<div id="show_dialog">
    <div id="show_datagrid" class="easyui-datagrid">

    </div>
</div>
<div id="classroom_dialog">
    <form action="#" id="classroom_form" method="post">
        <table align="center" style="margin-top: 20px">
            <tbody>
            <input type="hidden" name="id">
            <tr align="right">
                <td>教室名称:</td>
                <td><input name="name" type="text" class="easyui-textbox"/></td>
            </tr>
            <tr align="right">
                <td>教室地址:</td>
                <td><input name="address" type="text" class="easyui-textbox"/></td>
            </tr>
            <tr align="right">
                <td>座位数:</td>
                <td><input name="seatCount" type="text" class="easyui-numberbox"/></td>
            </tr>
            <tr align="right">
                <td>教室标语:</td>
                <td><input name="slogan" type="" class="easyui-textbox"/></td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>