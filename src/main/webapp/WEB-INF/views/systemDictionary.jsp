<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <%@include file="/static/commons/commons.jsp" %>

    <script src="/static/js/systemDictionary.js"></script>
    <title>字典目录明细</title>
</head>
<%--<table id="systemDictionary_datagrid" style="width:200px;height:250px"></table>
<hr>
<table id="systemDictionaryItem_datagrid" style="width:200px;height:250px"></table>--%>
    <div> <table style="width:100%;height:100%">
          <tr>   
              <td style="width:50%;">
                  <table id="systemDictionary_datagrid"></table>
              </td>
              <td style="width:50%;">
                  <table id="systemDictionaryItem_datagrid"></table>
              </td>
          </tr>
     </table>
</div>
<div id="systemDictionary_toolbar">
    <a class="easyui-linkbutton" data-cmd="add" data-options="plain:true,iconCls:'icon-add'">添加</a>
    <a class="easyui-linkbutton" data-cmd="edit" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    <a class="easyui-linkbutton" data-cmd="remove" data-options="plain:true,iconCls:'icon-delete'">删除</a>
    <a class="easyui-linkbutton" data-cmd="reload" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
</div>

 
<div id="systemDictionary_dialog">
    <form action="#" id="systemDictionary_form" method="post">
        <table type="width=50%" align="center"  style="margin-top:15px">
            <tbody>
            <input type="hidden" name="id">

            <tr>
                <td>字典目录编号:</td>
                <td><input name="sn" type="text" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td>字典目录名称:</td>
                <td><input type="text" class="easyui-textbox" name="name"/>
                </td>
            </tr>
            <tr>
                <td>字典目录简介:</td>
                <td><input type="text" class="easyui-textbox" name="intro"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>

<div id="systemDictionary_buttons">
    <a class="easyui-linkbutton" data-cmd="saveOrUpdate" data-options="plain:true,iconCls:'icon-save'">保存</a>
    <a class="easyui-linkbutton" data-cmd="cancel" data-options="plain:true,iconCls:'icon-cancel'">取消</a>
</div>



<div id="systemDictionaryItem_toolbar">
    <a class="easyui-linkbutton" data-cmd="itemAdd" data-options="plain:true,iconCls:'icon-add'">添加</a>
    <a class="easyui-linkbutton" data-cmd="itemEdit" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    <a class="easyui-linkbutton" data-cmd="remove" data-options="plain:true,iconCls:'icon-delete'">删除</a>
    <a class="easyui-linkbutton" data-cmd="reload" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
</div>

<div id="systemDictionaryItem_dialog">
    <form action="#" id="systemDictionaryItem_form" method="post">
        <table type="width=50%" align="center"  style="margin-top:15px">
            <tbody>
            <input type="hidden" name="id">

            <tr>
                <td>字典明细名称:</td>
                <td><input name="name" type="text" class="easyui-textbox"/></td>
            </tr>
            <tr>
                <td>字典明细简介:</td>
                <td><input name="intro" type="text" class="easyui-textbox"/></td>
            </tr>

            <tr>
                <td>字典目录:</td>
                <td><input name="systemDictionary.sn" class="easyui-combobox"
                           data-options="url:'/systemDictionary/selectAll.do',width:142,textField:'name',valueField:'sn',panelHeight:'auto'"/>
                </td>
            </tr>
            </tbody>
        </table>
    </form>
</div>
<div id="systemDictionaryItem_buttons">
    <a class="easyui-linkbutton" data-cmd="itemSaveOrUpdate" data-options="plain:true,iconCls:'icon-save'">保存</a>
    <a class="easyui-linkbutton" data-cmd="itemCancel" data-options="plain:true,iconCls:'icon-cancel'">取消</a>
</div>


</body>
</html>