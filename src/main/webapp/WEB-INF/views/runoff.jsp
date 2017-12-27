<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/static/commons/commons.jsp" %>
    <script src="/static/js/runoff.js"></script>
    <title>学员流失</title>
</head>
<body style="height: 100%;width: 100%">
    <div id="rf_datagrid">
    </div>
<div id="toolbar">
    <a class="easyui-linkbutton" data-cmd="add" data-options="plain:true,iconCls:'icon-add'">增加</a>
    <a class="easyui-linkbutton" data-cmd="update" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    <a class="easyui-linkbutton" data-cmd="delete" data-options="plain:true,iconCls:'icon-delete'">删除</a>
    <a class="easyui-linkbutton" data-cmd="search" data-options="plain:true,iconCls:'icon-search'">查看</a>
    <a class="easyui-linkbutton" data-cmd="reload" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
</div>
    <div id="rf_buttons">
        <a class="easyui-linkbutton" data-cmd="save" data-options="plain:true,iconCls:'icon-save'">保存</a>
        <a class="easyui-linkbutton" data-cmd="cancel" data-options="plain:true,iconCls:'icon-cancel'">关闭</a>
    </div>
    <div id="rf_dialog" >
        <form  id="rf_form" method="post">
            <table align="center" style="margin-top: 20px">
                <input type="hidden" name="fsid">
                <tr align="right">
                    <td>姓名:</td>
                    <td><input name="name" type="text" class="easyui-textbox"  style="width: 110px;"/></td>
                </tr>
                <tr align="right">
                    <td>电话</td>
                    <td><input name="tel" type="text" class="easyui-textbox"  style="width: 110px;"/></td>
                </tr>
                <tr align="right">
                    <td>流失时间</td>
                    <td><input name="livedate" type="text" class="easyui-datebox"  style="width: 110px;"/></td>
                </tr>
                <tr>
                    <td>流失金额</td>
                    <td><input name="backMoney" type="text" class="easyui-textbox"  style="width: 110px;"/></td>
                </tr>
                <tr align="right">
                    <td>是否退款</td>
                    <td>
                        <select name="state" id="com1" class="easyui-combobox"  data-options="panelHeight:'auto',width:110">
                            <option value="0">未退款</option>
                            <option value="1">已退款</option>
                        </select>
                    </td>
                </tr>
                <tr align="right">
                    <td>审核状态</td>
                    <td>
                        <select name="audit" id="com2" class="easyui-combobox"  data-options="panelHeight:'auto',width:110">
                            <option value="0">待审核</option>
                            <option value="1">已审核</option>
                        </select>
                    </td>
                </tr>
                <tr align="right">
                    <td>营销人员</td>
                    <td><input name="sale.id" class="easyui-combobox"
                               data-options="width:110,url:'/employee/selectByRoleSn.do?sn=SALE',textField:'realname',valueField:'id',panelHeight:'auto'"/></td>
                </tr>
                <tr align="right">
                    <td>流失阶段</td>
                    <td>
                        <select name="classId" id="com3" class="easyui-combobox"  data-options="panelHeight:'auto',width:110">
                            <option value="0">大神班</option>
                            <option value="1">基础班</option>
                        </select>
                    </td>

                </tr>
                <tr align="right">
                    <td>流失原因</td>
                    <td><input name="cause" type="text" class="easyui-textbox"   style="width: 110px;"/></td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
</body>
</html>
