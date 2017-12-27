<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/static/commons/commons.jsp" %>
    <script src="/static/js/examination.js"></script>
    <title>考试管理</title>
</head>
<body>
<div id="exa_datagrid">
</div>
<div id="toolbar">
    <shiro:hasPermission name="examination:saveOrUpdate">
    <a class="easyui-linkbutton" data-cmd="add" data-options="plain:true,iconCls:'icon-add'">添加</a>
    <a class="easyui-linkbutton" data-cmd="edit" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="examination:delete">
    <a class="easyui-linkbutton" data-cmd="remove" data-options="plain:true,iconCls:'icon-delete'">删除</a>
    </shiro:hasPermission>
    <a class="easyui-linkbutton" id="stateId" data-cmd="changState" data-options="plain:true,iconCls:'icon-examination'">审核</a>
    <a class="easyui-linkbutton" data-cmd="reload" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    </a><input id="potentialStudentId" name="potentialStudent.id" class="easyui-combobox"
           data-options="width:100,url:'/potentialstudent/selectAll.do',textField:'name',valueField:'id',panelHeight:'auto',prompt:'潜在学员'"/>
    <a class="easyui-linkbutton" data-cmd="searchForm" data-options="plain:true,iconCls:'icon-search'">搜索</a>
    <shiro:hasPermission name="examination:exportXls">
    <a class="easyui-linkbutton" data-cmd="exportXls" data-options="plain:true,iconCls:'icon-exportXls'">导出</a>
    </shiro:hasPermission>
</div>
<div id="exa_buttons">
    <a class="easyui-linkbutton" data-cmd="saveOrUpdate" data-options="plain:true,iconCls:'icon-save'">保存</a>
    <a class="easyui-linkbutton" data-cmd="cancel" data-options="plain:true,iconCls:'icon-cancel'">取消</a>
</div>
    <div id="exa_dialog">
        <form action="#" id="exa_form" method="post">
            <table align='center' style="margin-top:20px">
                <tbody>
                <input type="hidden" name="id">
                 <tr align='right'>
                    <td>姓名: </td>
                    <td><input name="name" type="text" class="easyui-textbox" style="width:110px"/></td>
                </tr>
                <tr align='right'>
                    <td>QQ: </td>
                    <td><input name="qq" type="text" class="easyui-textbox" style="width:110px"/></td>
                </tr>
                <tr align='right'>
                    <td>电话: </td>
                    <td><input name="tel" type="text" class="easyui-textbox" style="width:110px"/></td>
                </tr>
                <tr>
                    <td>考试时间: </td>
                    <td><input name="examtime" type="text" class="easyui-datebox"  data-options="width:110"/></td>
                </tr>
                <tr align='right'>
                    <td>考试结果: </td>
                    <td>
                        <select name="result" id="com1" class="easyui-combobox"  data-options="panelHeight:'auto',width:110">
                            <option value="0">未通过</option>
                            <option value="1">通过</option>
                        </select>
                    </td>
                </tr>
                <tr align='right'>
                    <td>营销人员: </td>
                    <td><input name="saleman.id" class="easyui-combobox"
                               data-options="width:110,url:'/employee/selectByRoleSn.do?sn=SALE',textField:'realname',valueField:'id',panelHeight:'auto'"/></td>
                </tr>
                <tr align='right'>
                    <td>审核人员: </td>
                    <td><input name="handler.id" class="easyui-combobox"
                               data-options="width:110,url:'/employee/selectByRoleSn.do?sn=EXAU',textField:'realname',valueField:'id',panelHeight:'auto'"/></td>
                </tr>
                <tr align='right'>
                    <td>意向班级: </td>
                    <td><input name="classId.id" class="easyui-combobox"
                               data-options="width:110,url:'/classGrade/selectAll.do',textField:'className',valueField:'id',panelHeight:'auto'"/></td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
</body>
</html>