<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>角色管理</title>
     <%@include file="/static/commons/commons.jsp" %>
    <script type="text/javascript" src="/static/js/role.js"></script>

  </head>
  <body style="width:100%;height:100%;">
  <%--数据表格--%>
  <div id="role_datagrid"></div>

  <div id="mytoolbar" style="padding: 5px 0;">
      <shiro:hasPermission name="role:saveOrUpdate">
		<a class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" data-cmd="add">新增</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true" data-cmd="edit">编辑</a>
        <a class="easyui-linkbutton" data-options="iconCls:'icon-delete',plain:true" data-cmd="remove">删除</a>
          </shiro:hasPermission>
		<a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
  </div>
  <div id="mybuttons">
		<a class="easyui-linkbutton" data-cmd="save" data-options="iconCls:'icon-save',plain:true">保存</a>
        <a class="easyui-linkbutton" data-cmd="cancel" data-options="iconCls:'icon-cancel',plain:true">取消</a>
  </div>
<div id="loadPermission"><a class="easyui-linkbutton"
                            data-options="iconCls:'icon-reload',plain:true"
                            data-cmd="loadPermission">重新加载权限</a></div>
<div id="removePermission"><a class="easyui-linkbutton"
                              data-options="iconCls:'icon-delete',plain:true"
                              data-cmd="removePermission">清空已选权限</a></div>
  <%--设置弹出界面--%>
  <div id="role_dialog">
      <form id="role_form" method="post">
          <input type="hidden" name="id"/>
          <table align="center" style="padding-top: 30px">
              <tr>
                  <td>角色名称:<input name="name" class="easyui-textbox" style="width: 135px;"></td>
                  <td>角色编码:<input name="sn" class="easyui-textbox" style="width: 135px;"></td>
              </tr>
              <tr>
                  <td>
                      <table id="allPermission" style="width:170px;height:250px"></table>
                  </td>
                  <td> <table id="rolePermission" style="width:170px;height:250px"></table></td>
              </tr>

          </table>
      </form>
  </div>

  </body>
</html>
