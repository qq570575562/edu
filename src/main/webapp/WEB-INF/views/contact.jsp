<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/static/commons/commons.jsp" %>
    <script src="/static/js/contact.js"></script>
    <title>学校联系人</title>
</head>
<body style="width:100%;height:100%">
<div id="contact_datagrid">
</div>
<div id="contact_toolbar">
    <a class="easyui-linkbutton" data-cmd="add" data-options="plain:true,iconCls:'icon-add'">添加</a>
    <a class="easyui-linkbutton" data-cmd="edit" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    <a class="easyui-linkbutton" data-cmd="delete" data-options="plain:true,iconCls:'icon-delete'">删除</a>
    <a class="easyui-linkbutton" data-cmd="load" data-options="plain:true,iconCls:'icon-reload'">刷新</a>

    <input id="keyword" class="easyui-textbox"
           data-options="prompt:'姓名/邮箱'" name="keyword" style="width: 110px;">&nbsp;
    <input id="universityId" class="easyui-combobox"
              data-options="url:'/universitytrace/selectAll.do',valueField:'id',textField:'name',panelHeight:'auto',prompt:'学校'"
              name="universityId" style="width: 110px;">
    <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="searchForm">查询</a>
    <a class="easyui-linkbutton" iconCls="icon-exportXls" plain="true" data-cmd="exportXls">导出</a>
</div>

<div id="contact_buttons">
    <a class="easyui-linkbutton" data-cmd="save" data-options="plain:true,iconCls:'icon-save'">保存</a>
    <a class="easyui-linkbutton" data-cmd="cancel" data-options="plain:true,iconCls:'icon-cancel'">取消</a>
</div>
<div id="contact_dialog">
    <form id="contact_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top: 20px">
            <tr align="right">
                <td>姓名:</td>
                <td>
                    <input name="name" class="easyui-textbox"/>
                </td>
            </tr>
            <tr align="right">
                <td>职务:</td>
                <td>
                    <input name="duty" class="easyui-textbox"/>
                </td>
            </tr>
            <tr align="right">
                <td>院系:</td>
                <td>
                    <input name="facukty" class="easyui-textbox"/>
                </td>
            </tr>
            <tr align="right">
                <td>部门:</td>
                <td>
                    <input name="dept" class="easyui-textbox"/>
                </td>
            </tr>
            <tr align="right">
                <td>电话:</td>
                <td>
                    <input name="tel" class="easyui-textbox"/>
                </td>
            </tr>
            <tr align="right">
                <td>QQ:</td>
                <td>
                    <input name="qq" class="easyui-textbox"/>
                </td>
            </tr>
            <tr align="right">
                <td>邮箱:</td>
                <td>
                    <input name="email" class="easyui-textbox"/>
                </td>
            </tr>
            <tr align="right">
                <td>性别:</td>
                <td>
                    <select class="easyui-combobox" id="contact_gander" name="gander"
                            data-options="panelHeight:'auto',width:167 ">
                        <option value="1">男</option>
                        <option value="0">女</option>
                    </select>
                </td>
            </tr>
            <tr align="right">
                <td>生日:</td>
                <td>
                    <input name="birthday" class="easyui-datebox"/>
                </td>
            </tr>
            <tr align="right">
                <td>主联:</td>
                <td>
                    <select id="contact_main" class="easyui-combobox" name="main" data-options="panelHeight:'auto',width:167">
                        <option value="1">是</option>
                        <option value="0">否</option>
                    </select>
                </td>
            </tr>

            <tr align="right">
                <td>大学:</td>
                <td>
                    <input  type="text" class="easyui-combobox" name="university.id"
                            data-options="valueField:'id',textField:'name',panelHeight:'auto',width:167,
                           url:'/universitytrace/selectAll.do'" />
                </td>
            </tr>
            <tr align="right">
                <td colspan="4">内容:<input name="intro" type="textarea" style="width:165px;height:60px;"></td>
            </tr>
        </table>

    </form>
</div>

</body>
</html>