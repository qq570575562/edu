<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/static/commons/commons.jsp" %>
    <script src="/static/plugin/jquery-easyui/base.js"></script> <%--灰色按钮还能点击补丁--%>
    <script src="/static/js/classGrade.js"></script>
    <title>班级</title>
</head>
<body>
    <div id="classGrade_datagrid">
    </div>
    <div id="toolbar">
        <shiro:hasPermission name="classGrade:saveOrUpdate">
            <a class="easyui-linkbutton" data-cmd="add" data-options="plain:true,iconCls:'icon-add'">添加</a>
            <a class="easyui-linkbutton" data-cmd="edit" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="classGrade:delete">
            <a class="easyui-linkbutton" data-cmd="remove" data-options="plain:true,iconCls:'icon-delete'">删除</a>
        </shiro:hasPermission>
            <a class="easyui-linkbutton" data-cmd="reload" data-options="plain:true,iconCls:'icon-reload'">刷新</a>
<shiro:hasPermission name="classGrade:distribute">
        <a class="easyui-linkbutton"  data-cmd="distribute" data-options="plain:true,iconCls:'icon-teacher'">分配班主任</a>
</shiro:hasPermission>
<shiro:hasPermission name="classGrade:importCourse">
        <a class="easyui-linkbutton"  data-cmd="importCourse" data-options="plain:true,iconCls:'icon-excel'">上传课程表</a>
</shiro:hasPermission>
    </div>
    <div id="classGrade_buttons">
        <a class="easyui-linkbutton" data-cmd="saveOrUpdate" data-options="plain:true,iconCls:'icon-save'">保存</a>
        <a class="easyui-linkbutton" data-cmd="cancel" data-options="plain:true,iconCls:'icon-cancel'">取消</a>
    </div>
    <div id="distribute_buttons">
        <a class="easyui-linkbutton" data-cmd="saveDis" data-options="plain:true,iconCls:'icon-save'">分配</a>
        <a class="easyui-linkbutton" data-cmd="cancelDis" data-options="plain:true,iconCls:'icon-cancel'">取消</a>
    </div>
    <div id="import_buttons">
        <a class="easyui-linkbutton" data-cmd="submitCourse" data-options="plain:true,iconCls:'icon-save'">提交</a>
    </div>
  <div id="classGrade_dialog">
      <form action="#" id="classGrade_form" method="post">
          <table align="center" style="margin-top: 20px">
              <tbody>
              <input type="hidden" name="id">
              <tr align="right">
                  <td>班级名称:</td>
                  <td><input name="className" type="text" class="easyui-textbox"  style="width:110px"/></td>
              </tr>
              <tr align="right">
                  <td>学生数量:</td>
                  <td><input name="studentCount" type="text" class="easyui-numberbox" style="width:110px"/></td>
              </tr>

              <tr align="right">
                  <td>所属学院:</td>
                  <td><input class="easyui-combobox" name="systemDictionaryItem.id"
                             data-options="width:110,textField:'name',url:'/systemDictionaryItem/queryBySn.do?sn=college',valueField:'id',panelHeight:'auto'"/></td>
              </tr>
              <tr align="right">
                  <td>所在教室:</td>
                  <td><input  class="easyui-combobox" name="classroom.id"
                             data-options="width:110,textField:'name',url:'/classroom/selectAll.do',valueField:'id',panelHeight:'auto'"/></td>
              </tr>
              </tbody>
          </table>
      </form>
  </div>
    <div class="easyui-dialog" id="importXls" data-options="width:150,height:150,closed:true">
        <form method="post" action="/classGradeloyee/importCourse.do" enctype="multipart/form-data">
            <input type="file" name="file"/>
            <input type="submit" value="提交"/>
        </form>
    </div>
<div id="distribute_dialog">
    <form id="saveDisForm" method="post">
    <input class="easyui-combobox" name="empId"
           data-options=" fit:true,textField:'realname',url:'/employee/selectByRoleSn.do?sn=CLASSLEADER',valueField:'id',panelHeight:'auto'" >
    </form>
</div>
<div id="import_dialog">
    <form id="import_form" method="post" enctype="multipart/form-data">
        <input  name="file" class="easyui-filebox"
                data-options="width:180,accept:'.xls'"  buttonText="选择课程表">
        <a href="javascript:;" class="easyui-linkbutton"  data-cmd="importCourse" plain=true></a>
    </form>
</div>
</body>
</html>
