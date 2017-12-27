<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <%@include file="/static/commons/commons.jsp" %>
    <script src="/static/js/department.js"></script>


    <title>部门</title>
</head>
<body style="width:100%;height:100%">
<div id="dept_datagrid">
</div>
<div id="dept_toolbar">
    <shiro:hasPermission name="department:saveOrUpdate">
    <a class="easyui-linkbutton" data-cmd="add" data-options="plain:true,iconCls:'icon-add'">添加</a>
    <a class="easyui-linkbutton" data-cmd="edit" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="department:changeState">
    <a class="easyui-linkbutton" data-cmd="scrap" data-options="plain:true,iconCls:'icon-delete'">报废</a>
    </shiro:hasPermission>
    
    <a class="easyui-linkbutton" data-cmd="load" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
</div>

<div id="dept_buttons">
    <a class="easyui-linkbutton" data-cmd="save" data-options="plain:true,iconCls:'icon-save'">保存</a>
    <a class="easyui-linkbutton" data-cmd="cancel" data-options="plain:true,iconCls:'icon-cancel'">取消</a>
</div>
<div id="dept_dialog">
    <form action="#" id="dept_form" method="post">
        <table align='center' style="margin-top:20px">
            <tbody>
            <input type="hidden" name="id">
            <tr>
                <td>部门编号:</td>
                <td><input name="sn" type="text" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td>部门名称:</td>
                <td><input name="name" type="text" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td>部门经理:</td>
                <td><input class="easyui-combobox" name="manager.id"
                           data-options="url:'/employee/selectAll.do',textField:'realname',valueField:'id',panelHeight:'auto'"/>
                </td>
            </tr>

            <tr>
                <td>上级部门:</td>
                <td><input name="parent.id" class="easyui-combobox"
                           data-options="url:'/department/selectAll.do',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
</body>
</html>