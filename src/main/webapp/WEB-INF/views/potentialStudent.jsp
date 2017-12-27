<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/static/commons/commons.jsp" %>
    <script src="/static/plugin/jquery-easyui/base.js"></script> <%--灰色按钮还能点击补丁--%>
    <script src="/static/js/potentialStudent.js"></script>

    <title>潜在学员</title>
</head>
<body>
    <div id="pos_datagrid">
    </div>
    <div id="toolbar">
        <shiro:hasPermission name="potentialstudent:saveOrUpdate">
        <a class="easyui-linkbutton" data-cmd="add" data-options="plain:true,iconCls:'icon-add'">添加</a>
        <a class="easyui-linkbutton" data-cmd="edit" data-options="plain:true,iconCls:'icon-edit'">编辑</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="potentialstudent:changState">
        <a class="easyui-linkbutton" id="stateId" data-cmd="changState" data-options="plain:true,iconCls:'icon-formal'">转正</a>
        </shiro:hasPermission>
        <shiro:hasPermission name="potentialstudent:changtial">
        <a class="easyui-linkbutton" id="tailId" data-cmd="changtial" data-options="plain:true,iconCls:'icon-track'">跟踪</a>
        </shiro:hasPermission>
        <a class="easyui-linkbutton" data-cmd="reload" data-options="plain:true,iconCls:'icon-reload'">刷新页面</a>
        <a class="easyui-linkbutton" data-cmd="searchOp" data-options="plain:true,iconCls:'icon-search'">高级查询</a>
        <a class="easyui-linkbutton" data-cmd="exam" data-options="plain:true,iconCls:'icon-add'">申请考试</a>
        <a class="easyui-linkbutton" data-cmd="toPool" data-options="plain:true,iconCls:'icon-share'">放入资源池</a>
    </div>
    <div id="pos_buttons">
        <a class="easyui-linkbutton" data-cmd="saveOrUpdate" data-options="plain:true,iconCls:'icon-save'">保存</a>
        <a class="easyui-linkbutton" data-cmd="cancel" data-options="plain:true,iconCls:'icon-cancel'">取消</a>
    </div>
    <div id="search_buttons">
        <a class="easyui-linkbutton" data-cmd="searchForm" data-options="plain:true,iconCls:'icon-search'">搜索</a>
        <a class="easyui-linkbutton" data-cmd="searchCancel" data-options="plain:true,iconCls:'icon-cancel'">取消</a>
    </div>
    <div id="exam_buttons">
        <a class="easyui-linkbutton" data-cmd="examsave" data-options="plain:true,iconCls:'icon-save'">申请考试</a>
        <a class="easyui-linkbutton" data-cmd="examcancel" data-options="plain:true,iconCls:'icon-cancel'">取消</a>
    </div>
    <div id="fs_buttons">
        <a class="easyui-linkbutton" data-cmd="fssave" data-options="plain:true,iconCls:'icon-save'">转正</a>
        <a class="easyui-linkbutton" data-cmd="fscancel" data-options="plain:true,iconCls:'icon-cancel'">取消</a>
    </div>
    <div id="exam_dialog">
        <form id="exam_form" method="post" action="#">
            <table align='center' style="margin-top:20px">
                <tbody align="center">
                <input type="hidden" name="psId">
                <tr align="right" >
                    <td>姓名: </td>
                    <td><input name="name" type="text" class="easyui-textbox" style="width:110px"/></td>
                </tr>
                <tr align="right" >
                    <td>QQ: </td>
                    <td><input name="qq" type="text" class="easyui-numberbox" style="width:110px"/></td>
                </tr>
                <tr align="right" >
                    <td>电话: </td>
                    <td><input name="tel" type="text" class="easyui-numberbox" style="width:110px"/></td>
                </tr>
                <tr align="right" >
                    <td>考试时间: </td>
                    <td><input name="examtime" type="text" class="easyui-datebox" style="width:110px"/></td>
                </tr>
                <tr align="right" >
                    <td>营销人员: </td>
                    <td><input name="saleman.id" class="easyui-combobox"
                               data-options="width:110,url:'/employee/selectByRoleSn.do?sn=SALE',textField:'realname',valueField:'id',panelHeight:'auto'"/></td>
                </tr>
                <tr align="right" >
                    <td>意向班级: </td>
                    <td><input name="classId.id" class="easyui-combobox"
                               data-options="width:110,url:'/classGrade/selectAll.do',textField:'className',valueField:'id',panelHeight:'auto'"/></td>
                </tr>
            </table>
        </form>
    </div>
    //高级查询
    <div id="search_dialog">
        <form action="#" id="searchForm" method="post">

                <table align='center' style="margin-top:10px">
                    <tbody align="center">

                    <tr align="right" >
                        <td>姓名/邮箱: </td>
                        <td>
                            <input name="keyword" id="keyword" type="text" class="easyui-textbox" data-options="width:225"/>
                        </td>
                    </tr>
                    <tr align="right" >
                        <td>年龄: </td>
                        <td><input name="minAge" id="minAge" type="text" class="easyui-numberbox" data-options="width:105" >~
                            <input name="maxAge" id="maxAge" type="text" class="easyui-numberbox" data-options="width:105" />
                        </td>
                    </tr>
                    <tr align="right" >
                        <td>意向学科: </td>
                        <td>
                            <input name="intentionSubjectId" id="intentionSubjectId" class="easyui-combobox"
                                   data-options="width:225,url:'/systemDictionaryItem/queryBySn.do?sn=intentionSubject',textField:'name',valueField:'id',panelHeight:'auto'"/>
                        </td>
                    </tr>
                    <tr align="right" >
                        <td>意向程度: </td>
                        <td>
                            <input name="intentionDegreeId" id="intentionDegreeId" class="easyui-combobox"
                                   data-options="width:225,url:'/systemDictionaryItem/queryBySn.do?sn=intentionDegree',textField:'name',valueField:'id',panelHeight:'auto'"/>
                        </td>
                    </tr>
                    <tr align="right" >
                        <td>意向校区: </td>
                        <td>
                            <input name="intentionSchoolId" id="intentionSchoolId" class="easyui-combobox"
                                   data-options="width:225,url:'/systemDictionaryItem/queryBySn.do?sn=intentionSchool',textField:'name',valueField:'id',panelHeight:'auto'"/>
                        </td>
                    </tr>
                    <tr align="right" >
                        <td>建档日期: </td>
                        <td><input name="startTime" id="startTime" type="text" class="easyui-datebox" data-options="width:105" />~
                            <input name="endTime" id="endTime" type="text" class="easyui-datebox" data-options="width:105" />
                        </td>
                    </tr>


                <%--<tr>
                    <td>姓名/邮箱</td>
                    <td><input name="keyword" id="keyword" type="text" class="easyui-textbox"/></td>
                    <td>年龄</td>
                    <td><input name="minAge" id="minAge" type="text" class="easyui-textbox" data-options="width:50"/>~
                        <input name="maxAge" id="maxAge" type="text" class="easyui-textbox" data-options="width:50"/>
                    </td>
                </tr>
                <tr>
                    <td>意向学科</td>
                    <td><input name="intentionSubjectId" id="intentionSubjectId" class="easyui-combobox"
                               data-options="width:110,url:'/systemDictionaryItem/queryBySn.do?sn=intentionSubject',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
                    <td>意向校区</td>
                    <td><input name="intentionSchoolId" id="intentionSchoolId" class="easyui-combobox"
                               data-options="width:110,url:'/systemDictionaryItem/queryBySn.do?sn=intentionSchool',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
                </tr>
                <tr>
                    <td>建档日期</td>
                    <td><input name="startTime" id="startTime" type="text" class="easyui-datebox"/></td>
                    <td>~</td>
                    <td><input name="endTime" id="endTime" type="text" class="easyui-datebox"/></td>
                </tr>
                <tr>
                    <td>意向程度</td>
                    <td><input name="intentionDegreeId" id="intentionDegreeId" class="easyui-combobox"
                               data-options="width:110,url:'/systemDictionaryItem/queryBySn.do?sn=intentionDegree',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
                </tr>--%>
                </tbody>
            </table>
        </form>
    </div>
    //新增和添加弹出框
  <div id="pos_dialog">
      <form action="#" id="pos_form" method="post">

          <table align='center' style="margin-top:15px">
              <input type="hidden" name="id"> <!-- 如果判断row里面有id,会填充到这里的隐藏域中 -->
              <tbody>
              <tr align="right">
                  <td>姓名:</td>
                  <td>
                      <input type="text" name="name" class="easyui-textbox" style="width:110px">
                  </td>
                  <td>年龄:</td>
                  <td>
                      <input type="text" name="age" class="easyui-textbox" style="width:110px">
                  </td>
                  <td>性别:</td>
                  <td>
                      <select name="gender" id="com1" class="easyui-combobox" data-options="panelHeight:'auto',width:110">
                          <option value="1">男</option>
                          <option value="0">女</option>
                      </select>
                  </td>
              </tr>
              <tr align="right">
                  <td>电话:</td>
                  <td>
                      <input type="text" name="tel" class="easyui-numberbox" style="width:110px">
                  </td>
                  <td>学历:</td>
                  <td>
                      <input name="education.id" class="easyui-combobox"
                             data-options="width:110,url:'/systemDictionaryItem/queryBySn.do?sn=education',textField:'name',valueField:'id',panelHeight:'auto'"/>
                  </td>
                  <td>QQ:</td>
                  <td>
                      <input type="text" name="qq" class="easyui-numberbox" style="width:110px">
                  </td>
              </tr>
              <tr align="right">
                  <td>email:</td>
                  <td>
                      <input type="text" name="email" class="easyui-textbox" style="width:110px">
                  </td>
                  <td>微信:</td>
                  <td>
                      <input type="text" name="weChart" class="easyui-textbox" style="width:110px">
                  </td>
                  <td>学校:</td>
                  <td>
                      <input type="text" name="school" class="easyui-textbox" style="width:110px">
                  </td>
              </tr>
              <tr align="right">
                  <td>地址:</td>
                  <td>
                      <input type="text" name="address" class="easyui-textbox" style="width:110px">
                  </td>
                  <td>专业:</td>
                  <td>
                      <input type="text" name="major" class="easyui-textbox" style="width:110px">
                  </td>
                  <td>建档日期:</td>
                  <td>
                      <input type="text" name="filingTime" class="easyui-datebox" style="width:110px">
                  </td>
              </tr>
              <tr align="right">
                  <td>录入时间:</td>
                  <td>
                      <input type="text" name="inputTime" class="easyui-datebox" style="width:110px">
                  </td>
                  <td>最后跟进:</td>
                  <td>
                      <input type="text" name="lastTime" class="easyui-datebox" style="width:110px">
                  </td>
                  <td>下次跟进:</td>
                  <td>
                      <input type="text" name="followTime" class="easyui-datebox" style="width:110px">
                  </td>
              </tr>
              <tr align="right">
                  <td>约访时间:</td>
                  <td>
                      <input type="text" name="aboutTime" class="easyui-datebox" style="width:110px">
                  </td>
                  <td>入学时间:</td>
                  <td>
                      <input type="text" name="entranceTime" class="easyui-datebox"  style="width:110px">
                  </td>
                  <td>营销人员:</td>
                  <td>
                      <input name="sale.id" class="easyui-combobox"
                             data-options="width:110,url:'/employee/selectByRoleSn.do?sn=SALE',textField:'realname',valueField:'id',panelHeight:'auto'"/>
                  </td>
              </tr>
              <tr align="right">
                  <td>意向班级:</td>
                  <td>
                      <input name="classId.id" class="easyui-combobox"
                             data-options="width:110,url:'/classGrade/selectAll.do',textField:'className',valueField:'id',panelHeight:'auto'"/>
                  </td>
                  <td>大客户:</td>
                  <td>
                      <input name="universitytrace.id" class="easyui-combobox"
                             data-options="width:110,url:'/universitytrace/selectAll.do',textField:'name',valueField:'id',panelHeight:'auto'"/>
                  </td>
                  <td>介绍学员:</td>
                  <td>
                      <input name="student.id" class="easyui-combobox"
                             data-options="width:110,url:'/formalStudent/selectAll.do',textField:'name',valueField:'id',panelHeight:'auto'"/>
                  </td>
              </tr>
              <tr align="right">
                  <td>来源:</td>
                  <td>
                      <input name="source.id" class="easyui-combobox"
                             data-options="width:110,url:'/systemDictionaryItem/queryBySn.do?sn=source',textField:'name',valueField:'id',panelHeight:'auto'"/>
                  </td>
                  <td>意向校区:</td>
                  <td>
                      <input name="intentionSchool.id" class="easyui-combobox"
                             data-options="width:110,url:'/systemDictionaryItem/queryBySn.do?sn=intentionSchool',textField:'name',valueField:'id',panelHeight:'auto'"/>
                  </td>
                  <td>意向学科:</td>
                  <td>
                      <input name="intentionSubject.id" class="easyui-combobox"
                             data-options="width:110,url:'/systemDictionaryItem/queryBySn.do?sn=intentionSubject',textField:'name',valueField:'id',panelHeight:'auto'"/>
                  </td>
              </tr>
              <tr align="right">
                  <td>跟踪:</td>
                  <td>
                      <select name="tail" id="com4" class="easyui-combobox" data-options="panelHeight:'auto',width:110">
                          <option value="0">未跟踪</option>
                          <option value="1">已跟踪</option>
                      </select>
                  </td>
                  <td>客户类型:</td>
                  <td>
                      <select name="clientType" id="com2" class="easyui-combobox"
                              data-options="panelHeight:'auto',width:110">
                          <option value="0">线下</option>
                          <option value="1">线上</option>
                      </select>
                  </td>
                  <td>状态:</td>
                  <td>
                      <select name="state" id="com3" class="easyui-combobox" data-options="panelHeight:'auto',width:110">
                          <option value="0">非正式学员</option>
                          <option value="1">正式学员</option>
                      </select>
                  </td>
              </tr>
              <tr align="right">
                  <td>意向程度:</td>
                  <td>
                      <input name="intentionDegree.id" class="easyui-combobox"
                             data-options="width:110,url:'/systemDictionaryItem/queryBySn.do?sn=intentionDegree',textField:'name',valueField:'id',panelHeight:'auto'"/>
                  </td>
              </tr>

              </br>
              <tr align="right">
                  <td colspan="6">摘要: <input name="attention" type="textarea"
                                                                     style="width:475px;height:50px;"></td>
              </tr>
              <tr align="right">
                  <td colspan="6">备注: <input name="remark" type="textarea"
                                                                     style="width:475px;height:50px;"></td>
              </tr>
              </tbody>
          </table>


          <%--<table>
              <tbody align="center">
              <input type="hidden" name="id">
              <tr>
                  <td>姓名</td>
                  <td><input name="name" type="text" class="easyui-textbox"/></td>
                  <td>年龄</td>
                  <td><input name="age" type="text" class="easyui-textbox"/></td>
                  <td>性别</td>
                  <td><select name="gender" id="com1" class="easyui-combobox" data-options="panelHeight:'auto',width:110">
                      <option value="1">男</option>
                      <option value="0">女</option>
                  </select></td>
              </tr>
              <tr>
                  <td>电话</td>
                  <td><input name="tel" type="text" class="easyui-textbox"/></td>
                  <td>学历</td>
                  <td><input name="education.id" class="easyui-combobox"
                             data-options="width:110,url:'/systemDictionaryItem/queryBySn.do?sn=education',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
                  <td>QQ</td>
                  <td><input name="qq" type="text" class="easyui-textbox"/></td>
              </tr>
              <tr>
                  <td>email</td>
                  <td><input name="email" type="text" class="easyui-textbox"/></td>
                  <td>微信</td>
                  <td><input name="weiChart" type="text" class="easyui-textbox"/></td>
                  <td>学校</td>
                  <td><input name="school" type="text" class="easyui-textbox"/></td>
              </tr>
              <tr>
                  <td>地址</td>
                  <td><input name="address" type="text" class="easyui-textbox"/></td>
                  <td>专业</td>
                  <td><input name="major" type="text" class="easyui-textbox"/></td>
                  <td>建档日期</td>
                  <td><input name="filingTime" type="text" class="easyui-datebox"/></td>
              </tr>
              <tr>
                  <td>录入时间</td>
                  <td><input name="inputTime" type="text" class="easyui-datebox"/></td>
                  <td>最后跟进时间</td>
                  <td><input name="lastTime" type="text" class="easyui-datebox"/></td>
                  <td>下次跟进时间</td>
                  <td><input name="followTime" type="text" class="easyui-datebox"/></td>
              </tr>
              <tr>
                  <td>约访时间</td>
                  <td><input name="aboutTime" type="text" class="easyui-datebox"/></td>
                  <td>入学时间</td>
                  <td><input name="entranceTime" type="text" class="easyui-datebox"/></td>
                  <td>营销人员</td>
                  <td><input name="sale.id" class="easyui-combobox"
                             data-options="width:110,url:'/employee/selectByRoleSn.do?sn=SALE',textField:'realname',valueField:'id',panelHeight:'auto'"/></td>
              </tr>
              <tr>
                  <td>意向班级</td>
                  <td><input name="classId.id" class="easyui-combobox"
                                 data-options="width:110,url:'/classGrade/selectAll.do',textField:'className',valueField:'id',panelHeight:'auto'"/></td>
                  <td>大客户</td>
                  <td><input name="universitytrace.id" class="easyui-combobox"
                                 data-options="width:110,url:'/universitytrace/selectAll.do',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
                  <td>介绍学员</td>
                  <td><input name="student_id" class="easyui-combobox"
                                data-options="width:110,url:'/student/selectAll.do',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
              </tr>
              <tr>
                  <td>来源</td>
                  <td><input name="source.id" class="easyui-combobox"
                                 data-options="width:110,url:'/systemDictionaryItem/queryBySn.do?sn=source',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
                  <td>意向校区</td>
                  <td><input name="intentionSchool.id" class="easyui-combobox"
                                data-options="width:110,url:'/systemDictionaryItem/queryBySn.do?sn=intentionSchool',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
                  <td>意向学科</td>
                  <td><input name="intentionSubject.id" class="easyui-combobox"
                                 data-options="width:110,url:'/systemDictionaryItem/queryBySn.do?sn=intentionSubject',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
              </tr>
              <tr>
                  <td>跟踪</td>
                  <td><select name="tail" id="com4" class="easyui-combobox"  data-options="panelHeight:'auto',width:110">
                      <option value="0">未跟踪</option>
                      <option value="1">已跟踪</option>
                  </select></td>
                  <td>客户类型</td>
                  <td>
                      <select name="clientType" id="com2" class="easyui-combobox"  data-options="panelHeight:'auto',width:110">
                          <option value="0">线下</option>
                          <option value="1">线上</option>
                      </select>
                  </td>
                  <td>状态</td>
                  <td>
                      <select name="state" id="com3" class="easyui-combobox" data-options="panelHeight:'auto',width:110">
                          <option value="0">非正式学员</option>
                          <option value="1">正式学员</option>
                      </select>
                  </td>
              </tr>
              <tr>
                  <td>意向程度</td>
                  <td><input name="intentionDegree.id" class="easyui-combobox"
                             data-options="width:110,url:'/systemDictionaryItem/queryBySn.do?sn=intentionDegree',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
              </tr>
              <tr>
                  <td colspan="6">关注:<input name="attention" type="textarea" style="width:500px;height:50px;"></td>
              </tr>
              <tr>
                  <td colspan="6">备注:<input name="remark" type="textarea" style="width:500px;height:50px;"></td>
              </tr>
              </tbody>
          </table>--%>
      </form>
  </div>
    <div id="st_buttons">
        <a class="easyui-linkbutton" onclick="inserttail()" data-options="plain:true,iconCls:'icon-track'">跟踪</a>
        <a class="easyui-linkbutton" data-cmd="stcancel" data-options="plain:true,iconCls:'icon-cancel'">关闭</a>
    </div>
    //学员跟踪窗口
    <div id="st_dialog">
        <form action="#" id="st_form" method="post">
            <input type="hidden" name="pid"/>
            <input type="hidden" name="tailnum"/>
            <table align='center' style="margin-top:20px">
                <tbody>
                <tr align="right">
                    <td>姓名: </td>
                    <td><input name="name" type="text" class="easyui-textbox" style="width:110px"/></td>
                    <td>QQ: </td>
                    <td><input name="qq" type="text" class="easyui-textbox" style="width:110px"/></td>
                </tr>
                <tr align="right">
                    <td>email: </td>
                    <td><input name="email" type="text" class="easyui-textbox" style="width:110px"/></td>
                    <td>电话: </td>
                    <td><input name="tel" type="text" class="easyui-textbox" style="width:110px"/></td>
                </tr>
                <tr align="right">
                    <td>营销人员: </td>
                    <td><input name="sale.id" class="easyui-combobox"
                               data-options="width:110,url:'/employee/selectByRoleSn.do?sn=SALE',textField:'realname',valueField:'id',panelHeight:'auto'"/></td>
                    <td>学校: </td>
                    <td><input name="university.id" class="easyui-combobox"
                               data-options="width:110,url:'/universitytrace/selectAll.do',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
                </tr>
                <tr align="right">
                    <td>意向班级: </td>
                    <td><input name="classId.id" class="easyui-combobox"
                               data-options="width:110,url:'/classGrade/selectAll.do',textField:'className',valueField:'id',panelHeight:'auto'"/></td>
                    <td>审核状态</td>
                    <td>
                        <select name="state" id="stcom1" class="easyui-combobox" data-options="panelHeight:'auto',width:110">
                            <option value="0">待审核</option>
                            <option value="1">已审核</option>
                        </select>
                    </td>
                </tr>
                <tr align="right">
                    <td>意向程度: </td>
                    <td><input name="intentiondegree.id" class="easyui-combobox"
                               data-options="width:110,url:'/systemDictionaryItem/queryBySn.do?sn=intentionDegree',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
                    <td>跟进目的: </td>
                    <td><input name="goal.id" class="easyui-combobox"
                               data-options="width:110,url:'/systemDictionaryItem/queryBySn.do?sn=goal',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
                </tr>
                <tr align="right">
                    <td>交流方式: </td>
                    <td><input name="exchange.id" class="easyui-combobox"
                               data-options="width:110,url:'/systemDictionaryItem/queryBySn.do?sn=exchange',textField:'name',valueField:'id',panelHeight:'auto'"/></td>
                    <td>约谈时间: </td>
                    <td><input name="aboutTime" type="text" class="easyui-datebox"  style="width:110px"/></td>
                </tr>
                <tr align="right">
                    <td>上次跟进: </td>
                    <td><input name="lasttime" type="text" class="easyui-datebox"  style="width:110px"/></td>
                    <td>下次跟进: </td>
                    <td><input name="followtime" type="text" class="easyui-datebox"  style="width:110px"/></td>
                </tr>
                <tr align="right">
                    <td>持续时间: </td>
                    <td colspan="3"><input name="duration" type="text" style="width:307px;"/></td>
                </tr>
                <tr align="right">
                    <td colspan="4">摘要:<input name="digest" type="textarea" style="width:307px;height:60px;"></td>
                </tr>
                <tr align="right">
                    <td colspan="4">内容:<input name="content" type="textarea" style="width:307px;height:60px;"></td>
                </tr>
                </tbody>
            </table>


        </form>
    </div>

    <%--转为正式学员--%>
    <div id="formalStudent_dialog">
        <form id="formalStudent_form" method="post">
            <div id="tt" class="easyui-tabs" style="width:100%;height:430px"> <%--高度430完美--%>
                <div title="基本信息">
                    <input type="hidden" name="psid">
                    <table align="center" style="font-size: 14px" width="600px" cellpadding="0px" cellspacing="0px">

                        <tr style="height: 10px"></tr>
                        <tr>
                            <td colspan="6">
                                <span style="font-size: 16px;color: #00aff0"><b>基本信息</b></span>
                            </td>
                        </tr>

                        <tr align="right">
                            <td><span style="font-size: 14px">姓名:</span></td>
                            <td><input class="easyui-textbox" name="name" style="width: 110px;height: 30px;"/></td>
                            <td><span style="font-size: 14px">QQ:</span></td>
                            <td><input type="text" class="easyui-textbox" name="qqNum" style="width: 110px;height: 30px;"/></td>
                            <td><span style="font-size: 14px">入学时间:</span></td>
                            <td><input class="easyui-datebox" name="enrollDate" style="width: 110px;height: 30px;"/></td>
                        </tr>
                        <tr align="right">
                            <td><span style="font-size: 14px">来源:</span></td>
                            <td><input type="text" class="easyui-combobox" name="source.id"
                                       style="width: 110px;height: 30px;"
                                       data-options="url:'/systemDictionaryItem/queryBySn.do?sn=source',valueField:'id',textField:'name',panelHeight:'auto'">
                            </td>
                            <td><span style="font-size: 14px">年龄:</span></td>
                            <td><input type="text" name="age" class="easyui-textbox" style="width: 110px;height: 30px;"></td>
                            <td><span style="font-size: 14px">性别:</span></td>
                            <td>
                                <select class="easyui-combobox" name="gander" style="width: 110px;height: 30px;"
                                        data-options="panelHeight:'auto'">
                                    <option value='1'>男</option>
                                    <option value='0'>女</option>
                                </select>
                            </td>
                        </tr>
                        <tr align="right">
                            <td><span style="font-size: 14px">电话:</span></td>
                            <td><input type="text" class="easyui-textbox" name="tel" style="width: 110px;height: 30px;"></td>
                            <td><span style="font-size: 14px">地址:</span></td>
                            <td><input type="text" class="easyui-textbox" name="address" style="width: 110px;height: 30px;"></td>
                            <td><span style="font-size: 14px">邮箱:</span></td>
                            <td><input class="easyui-textbox"  name="email" style="width: 110px;height: 30px;"/></td>
                        </tr>
                        <tr align="right">
                            <td><span style="font-size: 14px">学历:</span></td>
                            <td><input type="text" class="easyui-combobox"
                                       data-options="url:'/systemDictionaryItem/queryBySn.do?sn=education',valueField:'id',
                                   textField:'name',panelHeight:'auto'"
                                       name="education.id" style="width: 110px;height: 30px;"></td>
                            <td><span style="font-size: 14px">学校:</span></td>
                            <td><input type="text" class="easyui-textbox" name="school" style="width: 110px;height: 30px;"></td>
                            <td><span style="font-size: 14px">专业:</span></td>
                            <td><input type="text" class="easyui-textbox" name="major" style="width: 110px;height: 30px;"></td>
                        </tr>
                        <tr align="right">
                            <td><span style="font-size: 14px">外语水平:</span></td>
                            <td><input type="text" class="easyui-combobox"
                                       data-options="url:'/systemDictionaryItem/queryBySn.do?sn=foreignLangLevel',valueField:'id',
                                   textField:'name',panelHeight:'auto'"
                                       name="foreignLangLevel.id" style="width: 110px;height: 30px;"></td>
                            <td><span style="font-size: 14px">工作年限:</span></td>
                            <td><input type="text" class="easyui-numberbox" name="yearsOfWorking" style="width: 110px;height: 30px;"></td>
                            <td><span style="font-size: 14px">所在班级:</span></td>
                            <td><input type="text" class="easyui-combobox" name="clz.id"
                                       data-options="url:'/classGrade/selectAll.do',valueField:'id',textField:'className',panelHeight:'auto'"
                                       style="width: 110px;height: 30px;"/>
                            </td>
                        </tr>
                        <tr align="right">
                            <td><span style="font-size: 14px">付款方式:</span></td>
                            <td><input type="text" class="easyui-combobox" name="payMode.id"
                                       style="width: 110px;height: 30px;"
                                       data-options="url:'/systemDictionaryItem/queryBySn.do?sn=payMode',valueField:'id',textField:'name',panelHeight:'auto'">
                            </td>
                            </td>
                            <td><span style="font-size: 14px">客户类型:</span></td>
                            <td><input type="text" class="easyui-combobox" name="clientType.id"
                                       style="width: 110px;height: 30px;"
                                       data-options="url:'/systemDictionaryItem/queryBySn.do?sn=clientType',valueField:'id',textField:'name',panelHeight:'auto'">
                            </td>
                            <td><span style="font-size: 14px">所在校区:</span></td>
                            <td><input type="text" class="easyui-combobox" name="campus.id"
                                       style="width: 110px;height: 30px;"
                                       data-options="url:'/systemDictionaryItem/queryBySn.do?sn=intentionSchool',valueField:'id',textField:'name',panelHeight:'auto'">
                            </td>
                        </tr>
                        <tr align="right">
                            <td><span style="font-size: 14px">营销人员:</span></td>
                            <td><input type="text" class="easyui-combobox" name="saleman.id"
                                       style="width: 110px;height: 30px;"
                                       data-options="url:'/employee/selectByRoleSn.do?sn=SALE',valueField:'id',textField:'realname',panelHeight:'auto'">
                            </td>
                            <td><span style="font-size: 14px">所在校区:</span></td>
                            <td><input type="text" class="easyui-combobox" name="campus.id"
                                       style="width: 110px;height: 30px;"
                                       data-options="url:'/systemDictionaryItem/queryBySn.do?sn=intentionSchool',valueField:'id',textField:'name',panelHeight:'auto'">
                            </td>
                        </tr>
                        <tr style="height: 10px"></tr>
                        <tr>
                            <td colspan="6">
                                <span style="font-size: 16px;color: #00aff0"><b>缴费信息</b></span>
                            </td>
                        </tr>
                        <tr align="right">
                            <td><span style="font-size: 14px">计划学费:</span></td>
                            <td><input type="text" class="easyui-numberbox" name="planningTuition" style="width: 110px;height: 30px;" ></td>
                            <td><span style="font-size: 14px">优惠金额:</span></td>
                            <td><input type="text" class="easyui-numberbox" name="discountAmount" style="width: 110px;height: 30px;"></td>
                            <td><span style="font-size: 14px">总学费:</span></td>
                            <td><input type="text" class="easyui-numberbox" name="totalTuition" style="width: 110px;height: 30px;"></td>
                        </tr>
                        <tr align="right">
                            <td><span style="font-size: 14px">其他费用:</span></td>
                            <td><input type="text" class="easyui-numberbox" name="otherCost" style="width: 110px;height: 30px;">
                            </td>
                            <td><span style="font-size: 14px">其他优惠:</span></td>
                            <td><input type="text" class="easyui-numberbox" name="otherDiscount" style="width: 110px;height: 30px;"></td>
                            <td><span style="font-size: 14px">优惠说明:</span></td>
                            <td><input type="text" class="easyui-textbox" name="discountDesc" style="width: 110px;height: 30px;"></td>
                        </tr>
                        <tr align="right">
                            <td><span style="font-size: 14px">已付学费:</span></td>
                            <td><input type="text" class="easyui-numberbox" name="paidTuition" style="width: 110px;height: 30px;"></td>
                            <td><span style="font-size: 14px">还欠学费:</span></td>
                            <td><input type="text" class="easyui-numberbox" name="ownTuition" style="width: 110px;height: 30px;">
                            </td>
                            <td><span style="font-size: 14px">最后付款时间:</span></td>
                            <td><input class="easyui-datebox" name="lastPaymentDate" style="width: 110px;height: 30px;">
                            </td>
                        </tr>
                        <tr align="right">
                            <td><span style="font-size: 14px">预付学费:</span></td>
                            <td><input type="text" class="easyui-numberbox" name="prePaidTuition" style="width: 110px;height: 30px;"></td>
                            <td><span style="font-size: 14px">缴费状态:</span></td>
                            <td>
                                <select name="paidState" class="easyui-combobox" panelHeight="auto"
                                        style="width: 110px;height: 30px;">
                                    <option value="0">未缴清</option>
                                    <option value="1">已缴清</option>
                                </select>
                            </td>
                        </tr>
                    </table>
                </div>

                <div title="私密信息" data-options="closable:true" style="overflow:auto;padding:20px;display:none;">
                    <table align="center" style="border-collapse:separate; border-spacing:0px 10px;">
                        <tr align="right">
                            <td><span style="font-size: 14px">身份证号:</span></td>
                            <td><input type="text" name="identityNum" size="35"></td>
                        </tr>
                        <tr align="right">
                            <td><span style="font-size: 14px">紧急联系人:</span></td>
                            <td><input type="text" name="urgentContact" size="35"></td>
                        </tr>
                        <tr align="right">

                            <td><span style="font-size: 14px">紧急联系电话:</span></td>
                            <td><input type="text" name="urgentTel" size="35"></td>
                        </tr>
                    </table>


                    <HR width="100%" color="white" SIZE=2>
                    <table align="center" cellpadding="5px">
                        <tr>
                            <td align="right"><span style="font-size: 14px">就业意向</span>:</td>
                            <td><label>
                                <input type="checkbox" name="employeementIntention" value="1">
                                <span style="font-size: 14px">拥有一份软件工程师工作</span>
                            </label></td>
                            <td><label>
                                <input type="checkbox" name="employeementIntention" value="2">
                                <span style="font-size: 14px">搬砖</span>
                            </label></td>
                            <td><label>
                                <input type="checkbox" name="employeementIntention" value="3">
                                <span style="font-size: 14px">挖煤</span>
                            </label></td>
                        </tr>
                        <tr>
                            <td></td>
                            <td><label>
                                <input type="checkbox" name="employeementIntention" value="4">
                                <span style="font-size: 14px">开挖掘机</span>
                            </label></td>
                            <td><label>
                                <input type="checkbox" name="employeementIntention" value="5">
                                <span style="font-size: 14px">修电脑</span>
                            </label></td>
                        </tr>
                        <tr>
                            <td align="right"><span style="font-size: 14px">工作经历</span>:</td>
                            <td colspan="3"><textarea name="workingExperience"
                                                      style="width: 450px;height: 130px"></textarea></td>
                        </tr>
                    </table>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
