<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<html>
<head>
    <%@include file="/static/commons/commons.jsp" %>
    <script src="/static/plugin/jquery-easyui/base.js"></script> <%--灰色按钮还能点击补丁--%>
    <script src="/static/js/formalStudent.js"></script>
    <title>正式员工</title>
</head>
<body style="width:100%;height:100%">
<table id="formalStudent_datagrid"></table>
<div id="formalStudent_toolbar">
    <shiro:hasPermission name="formalStudent:saveOrUpdate">
    <a id="btn_eidt" class="easyui-linkbutton" iconCls="icon-edit" plain="true" data-cmd="edit">编辑</a>
    </shiro:hasPermission>
    <a class="easyui-linkbutton" iconCls="icon-reload" plain="true" data-cmd="reload">刷新</a>
    <a class="easyui-linkbutton" iconCls="icon-see" plain="true" data-cmd="check">查看</a>
    <a id="btn_transfer" class="easyui-linkbutton" iconCls="icon-changeClasses" plain="true" data-cmd="transfer">转班</a>
    <a id="btn_suspend" class="easyui-linkbutton" iconCls="icon-rest" plain="true" data-cmd="suspend">休学</a>
    <a id="btn_leave" class="easyui-linkbutton" iconCls="icon-loss" plain="true" data-cmd="leave">流失</a>
            <%--test--%>

    <select class="easyui-combobox"
               data-options="url:'/classGrade/selectAll.do',prompt:'姓名/班级', valueField:'id',textField:'className',panelHeight:'auto'"
               name="clzId"  style="width: 120px">
</select>
    <input type="text" class="easyui-datebox" name="beginDate" data-options="prompt:'入学开始时间'" style="width:110px"/> ~
    <input type="text" class="easyui-datebox" name="endDate" data-options="prompt:'入学结束时间'"  style="width:110px"/>

    <input name="keyword" class="easyui-textbox"  data-options="prompt:'姓名/班级'" style="width:100px"/>

    <a class="easyui-linkbutton" iconCls="icon-search" plain="true" data-cmd="searchForKey">查询</a>
    <a  id="mb" class="easyui-menubutton"
       data-options="menu:'#mm',iconCls:'icon-fastSearch'">快查</a>
    <%--href="javascript:void(0)"  下面是mm的选项--%>
    <div id="mm" style="width:150px;">
        <div data-options="iconCls:'icon-alreadyPaid'">已付</div>
        <div data-options="iconCls:'icon-notPayment'">未付</div>
        <div class="menu-sep"></div>
        <div data-options="iconCls:'icon-sick'">休学</div>
        <div data-options="iconCls:'icon-learn'">学习中</div>
        <div class="menu-sep"></div>
        <div data-options="iconCls:'icon-onLine'">线上</div>
        <div data-options="iconCls:'icon-offLine'">线下</div>
        <div class="menu-sep"></div>
        <div data-options="iconCls:'icon-see'">所有</div>
    </div>
    <a class="easyui-linkbutton" iconCls="icon-exportXls" plain="true" data-cmd="exportXls">导出</a>

</div>

<div id="formalStudent_btns">
    <a class="easyui-linkbutton" id="see_save_btn" iconCls="icon-save" plain="true" data-cmd="save">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel">取消</a>
</div>
<div id="formalStudent_transfer_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save_transfer">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel_transfer">取消</a>
</div>
<div id="formalStudent_leaving_btns">
    <a class="easyui-linkbutton" iconCls="icon-save" plain="true" data-cmd="save_leaving">保存</a>
    <a class="easyui-linkbutton" iconCls="icon-cancel" plain="true" data-cmd="cancel_leaving">取消</a>
</div>


<div id="formalStudent_leaving_dialog">
    <form id="formalStudent_leaving_form" method="post">
        <input id="leaving_stuId" type="hidden" name="id">
        <table align="center" style="margin-top: 20px" cellspacing="10px" cellpadding="5px">
            <tr>
                <td>是否退款</td>
                <td>
                    <label>
                        <input type="radio" name="isMoneyBack" value="1">
                        <span style="font-size: 12px">是</span>
                    </label>&nbsp;&nbsp;&nbsp;
                    <label>
                        <input type="radio" name="isMoneyBack" value="0">
                        <span style="font-size: 12px">否</span>
                    </label>
                </td>
            </tr>
            <tr style="height: 20px;"></tr>
            <tr>
                <td>流失阶段</td>
                <td>
                    <select class="easyui-combobox" style="width: 150px; height: 28px;" name="leavingPhase"
                            data-options="panelHeight:'auto'">
                        <option value="基础班">基础班</option>
                        <option value="大神班">大神班</option>
                    </select>
                </td>
            </tr>
            <tr style="height: 20px;"></tr>
            <tr id="drawBackAmount_tr">
                <td>退款金额</td>
                <td><input class="easyui-numberbox" name="drawBackAmount" style="width: 150px; height: 28px;"/>
                </td>
            </tr>
            <tr style="height: 20px;"></tr>
            <tr>
                <td>退学原因</td>
                <td>
                    <textarea name="leavingReason" style="width: 150px;height: 60px"></textarea>
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="formalStudent_transfer_dialog">
    <form id="formalStudent_transfer_form" method="post">
        <input id="transfer_stuId" type="hidden" name="ids">
        <table align="center" style="margin-top: 20px">
            <tr align="right">
                <td>当前班级:</td>
                <td><input type="text" class="easyui-combobox" name="clz.id" style="width: 120px;"
                           data-options="url:'/classGrade/selectAll.do',valueField:'id',textField:'className',panelHeight:'auto',readonly:'true' "/>
                </td>
            </tr>
            <tr align="right">
                <td>转入班级:</td>
                <td><input type="text" id="afterClass" class="easyui-combobox" data-options="url:'/classGrade/selectAll.do',valueField:'id',textField:'className',panelHeight:'auto'"
                           name="afterClassId" style="width: 120px;"/>
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="formalStudent_dialog">
    <form id="formalStudent_form" method="post">
        <div id="tt" class="easyui-tabs" style="width:95%;height:390px;">
            <div title="基本信息">
                <input type="hidden" name="id">
                <table align="center" style="font-size: 12px" width="600px" cellpadding="0px" cellspacing="0px">

                    <tr style="height: 10px"></tr>
                    <tr>
                        <td colspan="6">
                            <span style="font-size: 16px;color: #00aff0"><b>基本信息</b></span>
                        </td>
                    </tr>

                    <tr style="height: 25px">
                        <td><span style="font-size: 12px">姓名:</span></td>
                        <td><input class="easyui-validatebox" name="name" style="width: 120px;height: 21px;"/></td>
                        <td><span style="font-size: 12px">QQ:</span></td>
                        <td><input type="text" name="qqNum" style="width: 120px;height: 21px;"/></td>
                        <td><span style="font-size: 12px">入学时间:</span></td>
                        <td><input class="easyui-datebox" name="enrollDate" style="width: 120px;height: 21px;"/></td>
                    </tr>
                    <tr style="height: 25px">
                        <td><span style="font-size: 12px">来源:</span></td>
                        <td><input type="text" class="easyui-combobox" name="source.id"
                                   style="width: 120px;height: 21px;"
                                   data-options="url:'/systemDictionaryItem/queryBySn.do?sn=source',valueField:'id',textField:'name',panelHeight:'auto'">
                        </td>
                        <td><span style="font-size: 12px">年龄:</span></td>
                        <td><input type="text" name="age" style="width: 120px;height: 21px;"></td>
                        <td><span style="font-size: 12px">性别:</span></td>
                        <td>
                            <select class="easyui-combobox" name="gander" style="width: 120px;height: 21px;"
                                    data-options="panelHeight:'auto'">
                                <option value='1'>男</option>
                                <option value='0'>女</option>
                            </select>
                        </td>
                    </tr>
                    <tr style="height: 25px">
                        <td><span style="font-size: 12px">电话:</span></td>
                        <td><input type="text" data-options="" name="tel" style="width: 120px;height: 21px;"></td>
                        <td><span style="font-size: 12px">地址:</span></td>
                        <td><input type="text" name="address" style="width: 120px;height: 21px;"></td>
                        <td><span style="font-size: 12px">邮箱:</span></td>
                        <td><input class="easyui-validatebox" data-options="required:true,validType:'email'"
                                   name="email" style="width: 120px;height: 21px;"/></td>
                    </tr>
                    <tr style="height: 25px">
                        <td><span style="font-size: 12px">学历:</span></td>
                        <td><input type="text" class="easyui-combobox"
                                   data-options="url:'/systemDictionaryItem/queryBySn.do?sn=education',valueField:'id',
                                   textField:'name',panelHeight:'auto'"
                                   name="education.id" style="width: 120px;height: 21px;"></td>
                        <td><span style="font-size: 12px">学校:</span></td>
                        <td><input type="text" name="school" style="width: 120px;height: 21px;"></td>
                        <td><span style="font-size: 12px">专业:</span></td>
                        <td><input type="text" name="major" style="width: 120px;height: 21px;"></td>
                    </tr>
                    <tr style="height: 25px">
                        <td><span style="font-size: 12px">外语水平:</span></td>
                        <td><input type="text" class="easyui-combobox"
                                   data-options="url:'/systemDictionaryItem/queryBySn.do?sn=foreignLangLevel',valueField:'id',
                                   textField:'name',panelHeight:'auto'"
                                   name="foreignLangLevel.id" style="width: 120px;height: 21px;"></td>
                        <td><span style="font-size: 12px">工作年限:</span></td>
                        <td><input type="text" name="yearsOfWorking" style="width: 120px;height: 21px;"></td>
                        <td><span style="font-size: 12px">所在班级:</span></td>
                        <td><input type="text" class="easyui-combobox" name="clz.id"
                                   data-options="url:'/classGrade/selectAll.do',valueField:'id',textField:'className',panelHeight:'auto'"
                                   style="width: 120px;height: 21px;"/>
                        </td>
                    </tr>

                    <tr style="height: 25px">
                        <td><span style="font-size: 12px">付款方式:</span></td>
                        <td><input type="text" class="easyui-combobox" name="payMode.id"
                                   style="width: 120px;height: 21px;"
                                   data-options="url:'/systemDictionaryItem/queryBySn.do?sn=payMode',valueField:'id',textField:'name',panelHeight:'auto'">
                        </td>
                        <td><span style="font-size: 12px">客户类型:</span></td>
                        <td><input type="text" class="easyui-combobox" name="clientType.id"
                                   style="width: 120px;height: 21px;"
                                   data-options="url:'/systemDictionaryItem/queryBySn.do?sn=clientType',valueField:'id',textField:'name',panelHeight:'auto'">
                        </td>
                        <td><span style="font-size: 12px">状态:</span></td>
                        <td>
                            <select class="easyui-combobox" name="state" data-options="panelHeight:'auto'"
                                    style="width: 120px;height: 21px;">
                                <option value="0">休学中</option>
                                <option value="1">正常</option>
                                <option value="2">退学状态</option>
                            </select>
                        </td>
                    </tr>
                    <tr style="height: 25px">
                        <td><span style="font-size: 12px">营销人员:</span></td>
                        <td><input type="text" class="easyui-combobox" name="saleman.id"
                                   style="width: 120px;height: 21px;"
                                   data-options="url:'/employee/selectByRoleSn.do?sn=SALE',valueField:'id',textField:'realname',panelHeight:'auto'">
                        </td>
                        <td><span style="font-size: 12px">所在校区:</span></td>
                        <td><input type="text" class="easyui-combobox" name="campus.id"
                                   style="width: 120px;height: 21px;"
                                   data-options="url:'/systemDictionaryItem/queryBySn.do?sn=intentionSchool',valueField:'id',textField:'name',panelHeight:'auto'">
                        </td>
                    </tr>


                    <tr style="height: 10px"></tr>
                    <tr>
                        <td colspan="6">
                            <span style="font-size: 16px;color: #00aff0"><b>缴费信息</b></span>
                        </td>
                    </tr>
                    <tr style="height: 25px">
                        <td><span style="font-size: 12px">计划学费:</span></td>
                        <td><input type="text" name="planningTuition" style="width: 120px;height: 21px;"
                                   readonly="readonly"></td>
                        <td><span style="font-size: 12px">优惠金额:</span></td>
                        <td><input type="text" name="discountAmount" style="width: 120px;height: 21px;"
                                   readonly="readonly"></td>
                        <td><span style="font-size: 12px">总学费:</span></td>
                        <td><input type="text" name="totalTuition" style="width: 120px;height: 21px;"
                                   readonly="readonly"></td>
                    </tr>
                    <tr style="height: 25px">
                        <td><span style="font-size: 12px">其他费用:</span></td>
                        <td><input type="text" name="otherCost" style="width: 120px;height: 21px;" readonly="readonly">
                        </td>
                        <td><span style="font-size: 12px">其他优惠:</span></td>
                        <td><input type="text" name="otherDiscount" style="width: 120px;height: 21px;"
                                   readonly="readonly"></td>
                        <td><span style="font-size: 12px">优惠说明:</span></td>
                        <td><input type="text" name="discountDesc" style="width: 120px;height: 21px;"
                                   readonly="readonly"></td>
                    </tr>
                    <tr style="height: 25px">
                        <td><span style="font-size: 12px">已付学费:</span></td>
                        <td><input type="text" name="paidTuition" style="width: 120px;height: 21px;"
                                   readonly="readonly"></td>
                        <td><span style="font-size: 12px">还欠学费:</span></td>
                        <td><input type="text" name="ownTuition" style="width: 120px;height: 21px;" readonly="readonly">
                        </td>
                        <td><span style="font-size: 12px">最后付款时间:</span></td>
                        <td><input class="easyui-datebox" name="lastPaymentDate" style="width: 120px;height: 21px;"
                                   readonly="readonly">
                        </td>
                    </tr>
                    <tr style="height: 25px">
                        <td><span style="font-size: 12px">预付学费:</span></td>
                        <td><input type="text" name="prePaidTuition" style="width: 120px;height: 21px;"
                                   readonly="readonly"></td>
                        <td><span style="font-size: 12px">缴费状态:</span></td>
                        <td>
                            <select name="paidState" class="easyui-combobox" panelHeight="auto"
                                    style="width: 120px;height: 21px;" readonly="readonly">
                                <option value="0">未缴清</option>
                                <option value="1">已缴清</option>
                            </select>
                        </td>

                    </tr>
                </table>
            </div>

            <div title="私密信息" data-options="closable:true" style="overflow:auto;padding:20px;display:none;">
                <table align="center" style="border-collapse:separate; border-spacing:0px 10px;">
                    <tr>


                        <td><span style="font-size: 12px">身份证号:</span></td>
                        <td><input type="text" name="identityNum" size="35"></td>
                    </tr>
                    <tr>

                        <td><span style="font-size: 12px">紧急联系人:</span></td>
                        <td><input type="text" name="urgentContact" size="35"></td>
                    </tr>
                    <tr>

                        <td><span style="font-size: 12px">紧急联系电话:</span></td>
                        <td><input type="text" name="urgentTel" size="35"></td>
                    </tr>
                </table>


                <HR width="100%" color="white" SIZE=2>
                <table align="center" cellpadding="5px">
                    <tr>
                        <td align="right"><span style="font-size: 12px">就业意向</span>:</td>
                        <td><label>
                            <input type="checkbox" name="employeementIntention" value="1">
                            <span style="font-size: 12px">拥有一份软件工程师工作</span>
                        </label></td>
                        <td><label>
                            <input type="checkbox" name="employeementIntention" value="2">
                            <span style="font-size: 12px">搬砖</span>
                        </label></td>
                        <td><label>
                            <input type="checkbox" name="employeementIntention" value="3">
                            <span style="font-size: 12px">挖煤</span>
                        </label></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><label>
                            <input type="checkbox" name="employeementIntention" value="4">
                            <span style="font-size: 12px">开挖掘机</span>
                        </label></td>
                        <td><label>
                            <input type="checkbox" name="employeementIntention" value="5">
                            <span style="font-size: 12px">修电脑</span>
                        </label></td>
                    </tr>
                    <tr>
                        <td align="right"><span style="font-size: 12px">工作经历</span>:</td>
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