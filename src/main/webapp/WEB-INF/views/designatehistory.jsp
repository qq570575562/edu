<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/static/commons/commons.jsp" %>
    <script src="/static/js/designatehistory.js"></script>
    <title>移交管理</title>
</head>
<body>
<div id="dh_datagrid">
</div>
<div id="toolbar">
    <a class="easyui-linkbutton" onclick="reload()" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    <a class="easyui-linkbutton" onclick="exportXls()" data-options="plain:true,iconCls:'icon-exportXls'">导出</a>
    <a class="easyui-linkbutton" data-options="plain:true">原拥有人</a>
    <input id="srcId" name="srcId"/>
    <a class="easyui-linkbutton" data-options="plain:true">现拥有人</a>
    <input id="tarId" name="tarId"/>
</div>
</body>
</html>
</html>
