<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <%@include file="/static/commons/commons.jsp" %>
    <script src="/static/js/universitytrace.js"></script>
    <title>大客户</title>
</head>
<body style="width:100%;height:100%">
<div id="universitytrace_datagrid">
</div>
<div id="universitytrace_toolbar">
    <shiro:hasPermission name="incomeItem:saveOrUpdate">
    <a class="easyui-linkbutton" data-cmd="add" data-options="plain:true,iconCls:'icon-add'">添加</a>
    <a class="easyui-linkbutton" data-cmd="edit" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
    </shiro:hasPermission>

    <shiro:hasPermission name="universitytrace:delete">
    <a class="easyui-linkbutton" data-cmd="delete" data-options="plain:true,iconCls:'icon-delete'">删除</a>
    </shiro:hasPermission>
    <a id="btn_changeState" class="easyui-linkbutton" iconCls="icon-man" plain="true" data-cmd="changeState">签约</a>
    <a class="easyui-linkbutton" iconCls="icon-see" plain="true" data-cmd="see">查看</a>
    <a class="easyui-linkbutton" data-cmd="load" data-options="plain:true,iconCls:'icon-reload'">刷新</a>

    <input id="keyword" class="easyui-textbox"
           data-options="prompt:'学校/地址'" name="keyword" style="width: 110px;">
    <input id="subjectId" class="easyui-combobox"
                data-options="url:'/systemDictionaryItem/queryBySn.do?sn=intentionSubject',valueField:'id',textField:'name',panelHeight:'auto',prompt:'意向学科'"
                name="subjectId" style="width: 110px;">
    <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="searchForm">查询</a>
    <a class="easyui-linkbutton" iconCls="icon-exportXls" plain="true" data-cmd="exportXls">导出</a>
</div>

<div id="universitytrace_buttons">
    <a id="see_save" class="easyui-linkbutton" data-cmd="save" data-options="plain:true,iconCls:'icon-save'">保存</a>
    <a class="easyui-linkbutton" data-cmd="cancel" data-options="plain:true,iconCls:'icon-cancel'">关闭</a>
</div>
<div id="universitytraceTraceAll_dialog">
    <form id="universitytrace_form" method="post">
        <input type="hidden" name="id">
        <table align="center" style="margin-top: 20px">
            <tr align="right">
                <td><span style="font-size: 14px">姓名:</span></td>
                <td><input class="easyui-textbox" name="name" style="width: 150px;height: 30px;"/></td>
                <td><span style="font-size: 14px">地址:</span></td>
                <td><input class="easyui-textbox" name="address" style="width: 150px;height: 30px;"/></td>
                <td><span style="font-size: 14px">重要性:</span></td>
                <td><input class="easyui-combobox" name="importance.id" style="width: 150px; height: 30px;"
                           data-options="url:'/systemDictionaryItem/queryBySn.do?sn=intentionDegree',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr align="right">
                <td><span style="font-size: 14px">意向程度:</span></td>
                <td><input class="easyui-combobox" name="wantedlevel.id" style="width: 150px; height: 30px;"
                           data-options="url:'/systemDictionaryItem/queryBySn.do?sn=intentionDegree',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
                <td><span style="font-size: 14px">意向学科:</span></td>
                <td><input class="easyui-combobox" name="subject.id" style="width: 150px; height: 30px;"
                           data-options="url:'/systemDictionaryItem/queryBySn.do?sn=intentionSubject',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
                <td><span style="font-size: 14px">意向学区:</span></td>
                <td><input class="easyui-combobox" name="college.id" style="width: 150px; height: 30px;"
                           data-options="url:'/systemDictionaryItem/queryBySn.do?sn=college',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr align="right">
                <td><span style="font-size: 14px">联系人:</span></td>
                <td><input class="easyui-combobox" name="contact.id" style="width: 150px; height: 30px;"
                           data-options="url:'/contact/selectAll.do',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
                <td><span style="font-size: 14px">营销人员:</span></td>
                <td><input class="easyui-combobox" name="marketer.id" style="width: 150px; height: 30px;"
                           data-options="url:'/employee/selectAll.do',textField:'realname',valueField:'id',panelHeight:'auto'"/>
                </td>
                <td><span style="font-size: 14px">跟进人员:</span></td>
                <td><input class="easyui-combobox" name="tracer.id" style="width: 150px; height: 30px;"
                           data-options="url:'/employee/selectBySn.do?sn=SALE',textField:'realname',valueField:'id',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr align="right">
                <td><span style="font-size: 14px">上次跟进:</span></td>
                <td><input class="easyui-datetimebox" name="prevtrancetime" style="width: 150px; height: 30px;"/></td>
                <td><span style="font-size: 14px">下次跟进:</span></td>
                <td><input class="easyui-datetimebox" name="nexttrancetime" style="width: 150px; height: 30px;"/></td>
                <td><span style="font-size: 14px">跟进状态:</span></td>
                <td>
                    <select class="easyui-combobox" id="un_traceState" name="tracestate"
                            style="width:150px; height: 30px;" data-options="panelHeight:'auto'">
                        <option value="1">已跟进</option>
                        <option value="0">未跟进</option>
                    </select>
                </td>
            </tr>
            <tr align="right">
                <td><span style="font-size: 14px">客户状态:</span></td>
                <td>
                    <select class="easyui-combobox" id="un_customerStatus" name="customerstatus"
                            style="width:150px; height: 30px;" data-options="panelHeight:'auto'">
                        <option value="1">已签约</option>
                        <option value="0">未签约</option>
                    </select>
                </td>
                <td><span style="font-size: 14px">学校电话:</span></td>
                <td><input class="easyui-textbox" name="schooltel" style="width: 150px; height: 30px;"/></td>
                <td><span style="font-size: 14px">邮箱:</span></td>
                <td><input class="easyui-textbox" name="email" style="width: 150px; height: 30px;"/></td>
            </tr>
            <tr align="right">
                <td><span style="font-size: 14px">学生数:</span></td>
                <td><input class="easyui-textbox" name="stunumber" style="width: 150px; height: 30px;"/></td>
                <td><span style="font-size: 14px">IT专业学生数:</span></td>
                <td><input class="easyui-numberbox" name="itstunumber" style="width: 150px; height: 30px;"/></td>
                <td><span style="font-size: 14px">学校类型:</span></td>
                <td><input class="easyui-combobox" name="type.id" style="width: 150px; height: 30px;"
                           data-options="url:'/systemDictionaryItem/queryBySn.do?sn=type',textField:'name',valueField:'id',panelHeight:'auto'"/>
                </td>
            </tr>
            <tr align="right">
                <td><span style="font-size: 14px">传真:</span></td>
                <td><input class="easyui-numberbox" name="telegraph" style="width: 150px; height: 30px;"/></td>
                <td><span style="font-size: 14px">邮政编码:</span></td>
                <td><input class="easyui-numberbox" name="postcode" style="width: 150px; height: 30px;"/></td>
                <td><span style="font-size: 14px">教师数量:</span></td>
                <td><input class="easyui-numberbox" name="teachernumber" style="width: 150px; height: 30px;"/></td>
            </tr>
            <tr align="right">
                <td><span style="font-size: 14px">备注:
                <td colspan="6" align="left"><input type="textarea" name="remark"
                                                    style="height: 50px;width: 610px"/>
                </td>
            </tr>
            <tr align="right">
                <td><span style="font-size: 14px">简介:
                <td colspan="6" align="left"><input type="textarea" name="introduction"
                                                    style="height: 50px;width: 610px"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<div id="universitytrace_import">
    <form action="/universitytrace/importXls1.do" method="post" enctype="multipart/form-data">

        <a class="easyui-linkbutton" iconCls="icon-help" plain="true" data-cmd="download">模板下载</a><br>
        多文件上传:<input name="files" class="easyui-filebox" style="width:350px"
                     data-options="accept:'application/vnd.ms-excel',multiple:true"><br>
        <input type="submit" value="提交">
    </form>
    <%--测试--%>
</div>
</body>
</html>