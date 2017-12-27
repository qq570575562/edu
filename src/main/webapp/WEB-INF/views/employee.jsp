<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
   <%@include file="/static/commons/commons.jsp" %>
    <script type="text/javascript" src="/static/plugin/fancyBox/jquery.fancybox.js"></script>
    <script src="/static/js/employee.js"></script>

    <title>员工管理</title>
</head>
<body>
    <div id="emp_datagrid">
    </div>
    <div id="toolbar">
       <shiro:hasPermission name="employee:saveOrUpdate">
            <a class="easyui-linkbutton" data-cmd="add" data-options="plain:true,iconCls:'icon-add'">添加</a>
            <a class="easyui-linkbutton" data-cmd="edit" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
     </shiro:hasPermission>
        <shiro:hasPermission name="employee:changState">
        <a class="easyui-linkbutton"
           id="stateId"
           data-cmd="changState"
           data-options="plain:true,iconCls:'icon-dimission'">离职</a>
        </shiro:hasPermission>
        <a class="easyui-linkbutton" data-cmd="reload" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
        <input type="text"
               id="keyword"
               class="easyui-textbox"
               name="keyword"
               data-options="prompt:'姓名/邮箱'"
               style="width: 125px;">
        <a class="easyui-linkbutton" data-cmd="searchForm" data-options="plain:true,iconCls:'icon-search'">搜索</a>
    </div>
    <div id="emp_buttons">
        <a class="easyui-linkbutton" data-cmd="saveOrUpdate" data-options="plain:true,iconCls:'icon-save'">保存</a>
        <a class="easyui-linkbutton" data-cmd="cancel" data-options="plain:true,iconCls:'icon-cancel'">取消</a>
    </div>
  <div id="emp_dialog">
      <form action="#" id="emp_form" method="post" enctype="multipart/form-data">
          <table align='center' style="margin-top:15px">
              <input type="hidden" name="id">
              <tbody>
              <tr align="right">
                  <td>用户名:</td>
                  <td><input type="text" name="username" class="easyui-textbox" prompt="用户名" required="ture" style="width:110px" >
                      <!-- prompt:灰色提示信息 --></td>
              </tr>
              <tr align="right">
                  <td>真实名称:</td>
                  <td> <input type="text"
                              name="realname"
                              class="easyui-textbox"
                              iconCls="icon-man"
                              prompt="真实姓名"
                              required="ture" style="width:110px" > <!-- prompt:灰色提示信息 --></td>
              </tr>
              <tr id="emp_password" align="right">
                  <td>密码:</td>
                  <td><input type="text"
                             name="password"
                             class="easyui-passwordbox"
                             iconCls="icon-lock"  style="width:110px" > <%--easyui-passwordbox: 小眼睛密码不显示--%></td>
              </tr>
              <tr align="right">
                  <td>手机:</td>
                  <td><input name="tel" type="text" class="easyui-numberbox" style="width:110px"/></td>
              </tr>
              <tr align="right">
                  <td>入职时间:</td>
                  <td><input name="inputTime" class="easyui-datebox" style="width:110px" /></td>
              </tr>
              <tr align="right">
                  <td>薪资:</td>
                  <td><input name="salary" class="easyui-numberbox" style="width:110px" /></td>
              </tr>
              <tr align="right">
                  <td>邮箱:</td>
                  <td><input name="email" class="easyui-textbox" data-options="validType:'email'" style="width:110px" /></td>
              </tr>
              <tr align="right">
                  <td>管理员:</td>
                  <td><select name="admin" class="easyui-combobox" data-options="panelHeight:'auto',width:110">
                      <option value="1">是</option>
                      <option value="0">否</option>
                  </select></td>
              </tr>
              <tr align="right">
                  <td>所在部门:</td>
                  <td><input name="dept.id" class="easyui-combobox"
                             data-options="width:110,url:'/department/selectAll.do',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
              </tr>
              <tr align="right">
                  <td>拥有角色:</td>
                  <td><input class="easyui-combobox" id="role_id"
                             data-options="width:142,url:'/role/selectAll.do',textField:'name',valueField:'id',panelHeight:'auto',multiple:true"/></td>
              </tr>
              </tbody>
          </table>
      </form>
  </div>
    <div class="easyui-dialog" id="importXls" data-options="width:150,height:150,closed:true">
        <form method="post" action="/employee/importXls.do" enctype="multipart/form-data">
            <input type="file" name="file"/>
            <input type="submit" value="提交"/>
        </form>
    </div>
</body>
</html>
