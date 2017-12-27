<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/static/commons/commons.jsp" %>
    <script src="/static/js/StudentPool.js"></script>
    <title>潜在学员</title>
</head>
<body>
    <div id="pos_datagrid">
    </div>
    //页面按钮
    <div id="toolbar">
        <a class="easyui-linkbutton" data-cmd="reload" data-options="plain:true,iconCls:'icon-reload'">刷新页面</a>
        <a class="easyui-linkbutton" data-cmd="searchone" data-options="plain:true,iconCls:'icon-see'">查看学生</a>
        <a class="easyui-linkbutton" data-cmd="redo" data-options="plain:true,iconCls:'icon-distribution'">分配</a>
        <input id="beginTime" type="text" class="easyui-datebox" data-options="prompt:'开始时间'" style="width: 120px;"/>
        <input id="endTime" type="text" class="easyui-datebox" data-options="prompt:'结束时间'" style="width: 120px;"/>
        <a class="easyui-linkbutton" data-cmd="selecttime" data-options="plain:true">最后跟进时间</a>
    </div>
    //指派窗口按钮
    <div id="redo_buttons">
        <a class="easyui-linkbutton" data-cmd="add" data-options="plain:true,iconCls:'icon-save'">确定</a>
        <a class="easyui-linkbutton" data-cmd="redocancel" data-options="plain:true,iconCls:'icon-cancel'">取消</a>
    </div>
    //指派窗口
    <div id="redo_dialog">
        <form action="#" id="redo_form" method="post">
            <table align='center' style="margin-top:15px">
                <input name="poolId" type="hidden">
                <input type="hidden" name="qq">
                <tbody align="center">
                    <tr align='right'>
                        <td>姓名: </td>
                        <td><input name="name" id="name" type="text" class="easyui-textbox" data-options="width:100" /></td>
                        <td>电话: </td>
                        <td><input name="tel" id="tel" type="text" class="easyui-textbox" data-options="width:100" /></td>
                    </tr>
                    <tr align='right'>
                        <td>原拥有人: </td>
                        <td><input name="source.id" id="srcId" class="easyui-combobox"
                                   data-options="width:100,url:'/employee/selectByRoleSn.do?sn=SALE',textField:'realname',valueField:'id',panelHeight:'auto'"/></td>
                        <td>意向程度: </td>
                        <td><input name="intentiondegree.id" class="easyui-combobox"
                                   data-options="width:100,url:'/systemDictionaryItem/queryBySn.do?sn=intentionDegree',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
                    </tr>
                    <tr align='right'>
                        <td>意向班级: </td>
                        <td><input name="classId.id" class="easyui-combobox"
                                   data-options="width:100,url:'/classGrade/selectAll.do',textField:'className',valueField:'id',panelHeight:'auto'"/></td>
                        <td>要指派人: </td>
                        <td><input name="target.id" class="easyui-combobox"
                                   data-options="width:100,url:'/employee/selectByRoleSn.do?sn=SALE',textField:'realname',valueField:'id',panelHeight:'auto'"/>
                    </tr>
                    <tr>
                        <td colspan="4">详情信息:<input name="minute" type="textarea" style="width:270px;height:60px;"></td>
                    </tr>
                </tbody>
            </table>
        </form>
    </div>

    //页面查看窗口
    <div id="pos_buttons">
        <a class="easyui-linkbutton" data-cmd="cancel" data-options="plain:true,iconCls:'icon-cancel'">关闭</a>
    </div>
  <div id="pos_dialog">
      <form action="#" id="pos_form" method="post">
          <table align='center' style="margin-top:20px">
              <tbody>
              <input type="hidden" name="id">
              <tr align='right'>
                  <td>姓名: </td>
                  <td><input name="name" type="text" class="easyui-textbox" data-options="width:130" /></td>
                  <td>年龄: </td>
                  <td><input name="age" type="text" class="easyui-textbox" data-options="width:130"/></td>
                  <td>性别: </td>
                  <td><select name="gender" id="com1" class="easyui-combobox" data-options="panelHeight:'auto',width:130">
                      <option value="1">男</option>
                      <option value="0">女</option>
                  </select></td>
              </tr>
              <tr align='right'>
                  <td>电话: </td>
                  <td><input name="tel" type="text" class="easyui-textbox" data-options="width:130"/></td>
                  <td>学历: </td>
                  <td><input name="education.id" class="easyui-combobox"
                             data-options="width:130,url:'/systemDictionaryItem/queryBySn.do?sn=education',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
                  <td>QQ: </td>
                  <td><input name="qq" type="text" class="easyui-textbox" data-options="width:130"/></td>
              </tr>
              <tr align='right'>
                  <td>email: </td>
                  <td><input name="email" type="text" class="easyui-textbox" data-options="width:130"/></td>
                  <td>微信: </td>
                  <td><input name="weiChart" type="text" class="easyui-textbox" data-options="width:130"/></td>
                  <td>学校: </td>
                  <td><input name="school" type="text" class="easyui-textbox" data-options="width:130"/></td>
              </tr>
              <tr align='right'>
                  <td>地址: </td>
                  <td><input name="address" type="text" class="easyui-textbox" data-options="width:130"/></td>
                  <td>专业: </td>
                  <td><input name="major" type="text" class="easyui-textbox" data-options="width:130"/></td>
                  <td>建档日期: </td>
                  <td><input name="filingTime" type="text" class="easyui-datebox" data-options="width:130" /></td>
              </tr>
              <tr align='right'>
                  <td>录入时间: </td>
                  <td><input name="inputTime" type="text" class="easyui-datebox" data-options="width:130" /></td>
                  <td>最后跟进: </td>
                  <td><input name="lastTime" type="text" class="easyui-datebox" data-options="width:130" /></td>
                  <td>下次跟进: </td>
                  <td><input name="followTime" type="text" class="easyui-datebox" data-options="width:130" /></td>
              </tr>
              <tr align='right'>
                  <td>约访时间: </td>
                  <td><input name="aboutTime" type="text" class="easyui-datebox" data-options="width:130" /></td>
                  <td>入学时间: </td>
                  <td><input name="entranceTime" type="text" class="easyui-datebox" data-options="width:130" /></td>
                  <td>营销人员: </td>
                  <td><input name="sale.id" class="easyui-combobox"
                             data-options="width:130,url:'/employee/selectByRoleSn.do?sn=SALE',textField:'realname',valueField:'id',panelHeight:'auto'"/></td>
              </tr>
              <tr align='right'>
                  <td>意向班级: </td>
                  <td><input name="classId.id" class="easyui-combobox"
                                 data-options="width:130,url:'/classGrade/selectAll.do',textField:'className',valueField:'id',panelHeight:'auto'"/></td>
                  <td>大客户: </td>
                  <td><input name="universitytrace.id" class="easyui-combobox"
                                 data-options="width:130,url:'/universitytrace/selectAll.do',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
                  <td>介绍学员: </td>
                  <td><input name="student_id" class="easyui-combobox"
                                data-options="width:130,url:'/student/selectAll.do',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
              </tr>
              <tr align='right'>
                  <td>来源: </td>
                  <td><input name="source.id" class="easyui-combobox"
                                 data-options="width:130,url:'/systemDictionaryItem/queryBySn.do?sn=source',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
                  <td>意向校区: </td>
                  <td><input name="intentionSchool.id" class="easyui-combobox"
                                data-options="width:130,url:'/systemDictionaryItem/queryBySn.do?sn=intentionSchool',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
                  <td>意向学科: </td>
                  <td><input name="intentionSubject.id" class="easyui-combobox"
                                 data-options="width:130,url:'/systemDictionaryItem/queryBySn.do?sn=intentionSubject',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
              </tr>
              <tr align='right'>
                  <td>跟踪: </td>
                  <td><select name="tail" id="com4" class="easyui-combobox"  data-options="panelHeight:'auto',width:130">
                      <option value="0">未跟踪</option>
                      <option value="1">已跟踪</option>
                  </select></td>
                  <td>客户类型: </td>
                  <td>
                      <select name="clientType" id="com2" class="easyui-combobox"  data-options="panelHeight:'auto',width:130">
                          <option value="0">线下</option>
                          <option value="1">线上</option>
                      </select>
                  </td>
                  <td>状态: </td>
                  <td>
                      <select name="state" id="com3" class="easyui-combobox" data-options="panelHeight:'auto',width:130">
                          <option value="0">非正式学员</option>
                          <option value="1">正式学员</option>
                      </select>
                  </td>
              </tr>
              <tr align='right'>
                  <td>意向程度: </td>
                  <td><input name="intentionDegree.id" class="easyui-combobox"
                             data-options="width:130,url:'/systemDictionaryItem/queryBySn.do?sn=intentionDegree',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
              </tr>
              <tr align='right'>
                  <td colspan="6">关注: <input name="attention" type="textarea" style="width:533px;height:50px;"></td>
              </tr>
              <tr align='right'>
                  <td colspan="6">备注: <input name="remark" type="textarea" style="width:533px;height:50px;"></td>
              </tr>
              </tbody>
          </table>
      </form>
  </div>
</body>
</html>
