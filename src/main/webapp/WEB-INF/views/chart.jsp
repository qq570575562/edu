<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/static/plugin/jquery-easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/plugin/jquery-easyui/themes/icon.css">
    <script type="text/javascript" src="/static/plugin/jquery-easyui/jquery.min.js"></script>
    <script type="text/javascript"
            src="/static/plugin/artDialog/jquery.artDialog.js?skin=aero"></script>
    <script type="text/javascript" src="/static/plugin/artDialog/plugins/iframeTools.js"></script>
    <script type="text/javascript" src="/static/plugin/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/static/plugin/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script src="/static/js/chart.js"></script>
    <title>正式学员报表</title>
</head>
<body style="width:100%;height:100%">
<div id="chart_datagrid">
</div>
<div id="chart_toolbar">
    关键字:<input id="keyword" class="easyui-textbox" data-options="width:200" prompt="销售人员/居住地址/毕业学校/电话">
    <input id="beginTime" class="easyui-datebox" name="beginTime"  data-options="prompt:'入学开始时间'"  style="width: 120px;">~
    <input id="endTime" class="easyui-datebox" name="endTime"   data-options="prompt:'入学结束时间'" style="width: 120px;">
    <select id="groupByName" class="easyui-combobox" name="groupByName" style="width: 120px" data-options="panelHeight:'auto'">
            <option value="f.name">学员</option>
            <option value="saleman.realname">销售人员</option>
            <option value="sys.name">支付方式</option>
        </select>
    <a class="easyui-linkbutton" data-cmd="search" data-options="plain:true,iconCls:'icon-search'">搜索</a>
    <a class="easyui-linkbutton" data-cmd="load" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
    <a class="easyui-linkbutton" data-cmd="export" data-options="plain:true,iconCls:'icon-exportXls'">导出</a>
    <a class="easyui-linkbutton" data-cmd="pie" data-options="plain:true,iconCls:'icon-pie'">饼状图</a>
    <a class="easyui-linkbutton" data-cmd="bar" data-options="plain:true,iconCls:'icon-zhu'">柱状图</a>
</div>
</body>
</html>