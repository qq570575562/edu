<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <%@include file="/static/commons/commons.jsp" %>
    <script src="/static/js/payment.js"></script>
    <script src="/static/plugin/jquery-easyui/base.js"></script>
    <title>支出管理</title>
</head>
<body>
    <div id="payment_datagrid">
    </div>
    <div id="toolbar">
        <shiro:hasPermission name="payment:saveOrUpdate">
            <a class="easyui-linkbutton" data-cmd="add" data-options="plain:true,iconCls:'icon-add'">添加</a>
            <a class="easyui-linkbutton" data-cmd="edit" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
        </shiro:hasPermission>
        <a class="easyui-linkbutton"  data-cmd="remove" data-options="plain:true,iconCls:'icon-delete'">删除</a>
        <a class="easyui-linkbutton" data-cmd="reload" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
        <a class="easyui-linkbutton" id="audit" data-cmd="audit" data-options="plain:true,iconCls:'icon-see'">审核</a>
            <input id="classId"  class="easyui-combobox" name="classId" style="width: 120px;"
                   data-options="width:110,textField:'className',url:'/classGrade/selectAll.do',valueField:'id',panelHeight:'auto',prompt:'班级名称'"/>
            <input id="paymodeId" class="easyui-combobox" name="paymodeId" style="width: 120px;"
                             data-options="width:110,prompt:'支付方式',textField:'name',url:'/systemDictionaryItem/queryBySn.do?sn=paymode',valueField:'id',panelHeight:'auto'"/>
            <input id="payBeginTime" class="easyui-datebox" style="width: 120px;" data-options="prompt:'开始时间'" name="payBeginTime">~<input id="payEndTime" data-options="prompt:'结束时间'" class="easyui-datebox" style="width: 120px;"  name="payEndTime">
            <a  href="javascript:;"  class="easyui-linkbutton" data-cmd="searchList" iconCls="icon-search" plain=true >搜索</a>
            &nbsp;&nbsp;<a class="easyui-linkbutton" data-cmd="export" data-options="plain:true,iconCls:'icon-exportXls'">导出Excel</a>

    </div>
    <div id="payment_buttons">
        <a class="easyui-linkbutton" data-cmd="saveOrUpdate" data-options="plain:true,iconCls:'icon-save'">保存</a>
        <a class="easyui-linkbutton" data-cmd="cancel" data-options="plain:true,iconCls:'icon-cancel'">取消</a>
    </div>
  <div id="payment_dialog">
      <form action="#" id="payment_form" method="post">
          <table align='center' style="margin-top:20px">
              <tbody >
              <input type="hidden" name="id">
              <tr align="right">
                  <td>支出日期:</td>
                  <td><input name="date" type="text" class="easyui-datebox"  style="width:110px"/></td>
              </tr>
              <tr align="right">
                  <td>支付金额:</td>
                  <td><input name="cost" type="text" class="easyui-numberbox" style="width:110px"/></td>
              </tr>
              <tr id="payment_password"  align="right">
                  <td>摘要:</td>
                  <td><input name="digest" type="text" class="easyui-textbox" style="width:110px"/></td>
              </tr>
              <tr align="right">
                  <td>支付人:</td>
                  <td><input name="payer.id" class="easyui-combobox"
                             data-options="width:110,url:'/employee/selectByRoleSn.do?sn=PAYER',textField:'realname',valueField:'id',panelHeight:'auto'"/></td>
              </tr>
              <tr align="right">
                  <td>经手人:</td>
                  <td><input name="brokerage.id" class="easyui-combobox"
                             data-options="width:110,url:'/employee/selectByRoleSn.do?sn=BROKERAGE',textField:'realname',valueField:'id',panelHeight:'auto'"/></td>
              </tr>
              <tr align="right">
                  <td>支付方式:</td>
                  <td><input name="paymode.id" class="easyui-combobox"
                             data-options="width:110,url:'/systemDictionaryItem/queryBySn.do?sn=paymode',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
              </tr>
              <tr>
                  <td>花费类型:</td>
                  <td><input name="costtype" type="text" class="easyui-textbox"  style="width:110px"/></td>
              </tr>
              <tr align="right">
                  <td>单据号:</td>
                  <td><input name="docnumber" class="easyui-textbox" style="width:110px"/></td>
              </tr>

              <tr align="right">
                  <td>所属班级:</td>
                  <td><input name="classGrade.id" class="easyui-combobox"
                             data-options="width:110,url:'/classGrade/selectAll.do',textField:'className',valueField:'id',panelHeight:'auto'"/></td>
              </tr>

              </tbody>
          </table>
      </form>
  </div>
    <div class="easyui-dialog" id="importXls" data-options="width:150,height:150,closed:true">
        <form method="post" action="/payment/importXls.do" enctype="multipart/form-data">
            <input type="file" name="file"/>
            <input type="submit" value="提交"/>
        </form>
    </div>
</body>
</html>
