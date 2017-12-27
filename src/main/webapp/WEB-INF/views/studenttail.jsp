<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
   <%@include file="/static/commons/commons.jsp" %>
    <script src="/static/js/studenttail.js"></script>
    <link href="/static/assets/plugins/sweetalert/dist/sweetalert.css" rel="stylesheet"><%--SweetAlert弹窗css依赖--%>
    <title>学员跟踪</title>
</head>
<body>
    <div id="st_datagrid">
    </div>
    <div id="toolbar">
        <shiro:hasPermission name="studenttail:saveOrUpdate">
        <a class="easyui-linkbutton" data-cmd="edit" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="studenttail:changState">
        <a class="easyui-linkbutton" id="stateId" data-cmd="changState" data-options="plain:true,iconCls:'icon-man'">审核</a>
        </shiro:hasPermission>
        <a class="easyui-linkbutton" data-cmd="reload" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
        <a class="easyui-linkbutton" data-cmd="check" data-options="plain:true,iconCls:'icon-see'">查看</a>
        <input id="pid" name="pid" data-options=" prompt:'选择学生'"/>
        <a class="easyui-linkbutton" onclick="lasttime()" data-options="plain:true,iconCls:'icon-search'">查询最新</a>
    </div>
   <div id="st_buttons">
        <a class="easyui-linkbutton" id="saveOrUpdate" data-cmd="saveOrUpdate" data-options="plain:true,iconCls:'icon-save'">保存</a>
        <a class="easyui-linkbutton" data-cmd="cancel" data-options="plain:true,iconCls:'icon-cancel'">取消</a>
    </div>
  <div id="st_dialog">
      <form action="#" id="st_form" method="post">
          <table align='center' style="margin-top:20px">
              <tbody align="center">
              <input type="hidden" name="id">
              <tr align="right">
                  <td>姓名: </td>
                  <td><input name="name" type="text" class="easyui-textbox" style="width: 105px"/></td>
                  <td>QQ: </td>
                  <td><input name="qq" type="text" class="easyui-textbox" style="width: 105px"/></td>
              </tr>
              <tr align="right">
                  <td>email: </td>
                  <td><input name="email" type="text" class="easyui-textbox" style="width: 105px"/></td>
                  <td>电话: </td>
                  <td><input name="tel" type="text" class="easyui-textbox" style="width: 105px"/></td>
              </tr>
              <tr align="right">
                  <td>营销人员: </td>
                  <td><input name="sale.id" class="easyui-combobox"
                             data-options="width:105,url:'/employee/selectByRoleSn.do?sn=SALE',textField:'realname',valueField:'id',panelHeight:'auto'"/></td>
                  <td>学校: </td>
                  <td><input name="university.id" class="easyui-combobox"
                             data-options="width:105,url:'/universitytrace/selectAll.do',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
              </tr>
              <tr align="right">
                  <td>意向班级: </td>
                  <td><input name="classId.id" class="easyui-combobox"
                             data-options="width:105,url:'/classGrade/selectAll.do',textField:'className',valueField:'id',panelHeight:'auto'"/></td>
                  <td>审核状态: </td>
                  <td>
                      <select name="state" id="com1" class="easyui-combobox" data-options="panelHeight:'auto',width:105">
                          <option value="0">待审核</option>
                          <option value="1">已审核</option>
                      </select>
                  </td>
              </tr>
              <tr align="right">
                  <td>意向程度: </td>
                  <td><input name="intentiondegree.id" class="easyui-combobox"
                             data-options="width:105,url:'/systemDictionaryItem/queryBySn.do?sn=intentionDegree',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
                  <td>跟进目的: </td>
                  <td><input name="goal.id" class="easyui-combobox"
                             data-options="width:105,url:'/systemDictionaryItem/queryBySn.do?sn=goal',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
              </tr>
              <tr align="right">
                  <td>交流方式: </td>
                  <td><input name="exchange.id" class="easyui-combobox"
                             data-options="width:105,url:'/systemDictionaryItem/queryBySn.do?sn=exchange',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
                  <td>约谈时间: </td>
                  <td><input name="aboutTime" type="text" class="easyui-datebox" style="width: 105px"/></td>
              </tr>
              <tr align="right">
                  <td>上次跟进: </td>
                  <td><input name="lasttime" type="text" class="easyui-datebox" style="width: 105px"/></td>
                  <td>下次跟进: </td>
                  <td><input name="followtime" type="text" class="easyui-datebox" style="width: 105px"/></td>
              </tr>
              <tr align="right">
                  <td>跟踪次数: </td>
                  <td><input name="tailnum" type="text" class="easyui-numberbox" style="width: 105px"/></td>
                  <td>持续时间: </td>
                  <td><input name="duration" type="text" class="easyui-numberbox" style="width: 105px"/></td>
              </tr>
              <tr align="right">
                  <td colspan="4">摘要:<input name="digest" type="textarea" style="width:281px;height:60px;"></td>
              </tr>
              <tr align="right">
                  <td colspan="4">内容:<input name="content" type="textarea" style="width:281px;height:60px;"></td>
              </tr>
              </tbody>
          </table>
      </form>
  </div>

    <div id="audio_buttons">
        <a class="easyui-linkbutton" data-cmd="audio" data-options="plain:true,iconCls:'icon-save'">确定</a>
        <a class="easyui-linkbutton" data-cmd="audioCancel" data-options="plain:true,iconCls:'icon-cancel'">取消</a>
    </div>
    <div id="audio_dialog">
        <form method="post" id="audio_form" action="#">
            <table align='center' style="margin-top:15px">
                <input type="hidden" name="id"/>
                <tbody align="center">
                <tr align="right">
                    <td>姓名:</td>
                    <td><input name="name" type="text" class="easyui-textbox" style="width:105px"/></td>
                </tr>
                <tr align="right">
                    <td>咨询人员:</td>
                    <td><input name="sale.id" class="easyui-combobox"
                               data-options="width:105,url:'/employee/selectByRoleSn.do?sn=SALE',textField:'realname',valueField:'id',panelHeight:'auto'"/></td>
                </tr>
                <tr align="right">
                    <td>交流方式:</td>
                    <td><input name="exchange.id" class="easyui-combobox"
                               data-options="width:105,url:'/systemDictionaryItem/queryBySn.do?sn=exchange',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
                </tr>
                <tr align="right">
                    <td>审核评价:</td>
                    <td><input name="appraise.id" class="easyui-combobox"
                               data-options="width:105,url:'/systemDictionaryItem/queryBySn.do?sn=appraise',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
                </tr>
                <tr align="right">
                    <td colspan="2">说明:<input name="audioex" type="textarea" style="width:105px;height:60px;"></td>
                </tr>
                </tbody>
            </table>
        </form>
    </div>
</body>
</html>
