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
    <title>日志管理</title>
     <%@include file="/static/commons/commons.jsp" %>
    <script type="text/javascript" src="/static/js/systemlog.js"></script>
  </head>
  <body style="width:100%;height:100%;">
  <%--数据表格--%>
  <div id="systemlog_datagrid"></div>
  
  <div id="mytoolbar" style="padding: 5px 0;">
       
    <shiro:hasPermission name="systemLog:deleteAll">
      <a class="easyui-linkbutton"
         data-options="iconCls:'icon-delete',plain:true"
         data-cmd="removeAll">清除所有日志</a>
    </shiro:hasPermission>
      <a class="easyui-linkbutton" data-options="iconCls:'icon-reload',plain:true" data-cmd="reload">刷新</a>
	</div>

  </body>
</html>
