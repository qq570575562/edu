<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">

    <title>Genius Education</title>
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html"/>
    <![endif]-->

    <link rel="shortcut icon" href="favicon.ico"> <link href="/static/bootstrap/css/bootstrap.min.css?v=3.3.6"
                                                        rel="stylesheet">
    <link href="/static/bootstrap/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="/static/bootstrap/css/animate.css" rel="stylesheet">
    <link href="/static/bootstrap/css/style.css?v=4.1.0" rel="stylesheet">
        <%--SweetAlert弹窗css依赖--%>
    <link href="/static/assets/plugins/sweetalert/dist/sweetalert.css" rel="stylesheet">
    <%--SweetAlert弹窗js依赖--%>
    <script type="text/javascript" src="/static/assets/plugins/sweetalert/dist/sweetalert.min.js"></script>

    <!-- 全局js -->
    <script src="/static/bootstrap/js/jquery.min.js?v=2.1.4"></script>
    <script src="/static/bootstrap/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="/static/bootstrap/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="/static/bootstrap/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="/static/bootstrap/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="/static/bootstrap/js/hplus.js?v=4.1.0"></script>
    <script type="text/javascript" src="/static/bootstrap/js/contabs.js"></script>

    <!-- 第三方插件 -->
    <script src="/static/bootstrap/js/plugins/pace/pace.min.js"></script>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!--左侧导航开始-->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <%--动态获取用户名<span><img alt="image" class="img-circle" src="<shiro:principal property='imageSmall'/>"/></span>--%>
                           <span><img alt="image" class="img-circle" src="/static/bootstrap/img/Albert.Einstein _small.jpg"/>
                            <%--<span><img alt="image" class="img-circle" src="/static/bootstrap/img/Albert.Einstein.jpg"/>--%>
                            </span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                               <span class="block m-t-xs"><strong class="font-bold">Albert.Einstein</strong></span>
                              <%--<span class="text-muted text-xs block"><shiro:principal property='realname'/><b class="caret"></b></span>--%>
                                </span>
                            </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a class="J_menuItem" href="/employee/editHeadview.do">修改头像</a>
                                </li>
                                <li><a class="J_menuItem" href="/static/yudi/index.html">个人资料</a>
                                </li>
                                <li><a class="J_menuItem" href="/static/jQueryHeadbird/bird.html">轻松一刻</a>
                                </li>
                                <li><a class="J_menuItem" href="/static/contact_us/index.html">联系我们</a>
                                </li>
                                <%--<li><a class="J_menuItem" href="mailbox.html">信箱</a>
                                </li>--%>
                                <li class="divider "></li>
                                <li><a href="login.do">安全退出</a>
                                </li>
                            </ul>
                        </div>
                        <div class="logo-element">G E
                        </div>
                    </li>
                    <li>
                        <a class="J_menuItem" href="main.do" data-index="0">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">主页</span>
                        </a>
                    </li>
                        <li>
                            <a class="J_menuItem"
                               href="/calendar.do"><i class="fa fa-calendar"></i> <span class="nav-label">计划</span></a>
                        </li>
                    <li>
                        <a href="#">
                            <i class="fa fa fa-bar-chart-o"></i>
                            <span class="nav-label">统计图表</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <shiro:hasPermission name="chart:view">
                            <li>
                                <a class="J_menuItem" href="/chart/view.do">正式学员报表</a>
                            </li>
                            </shiro:hasPermission>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-group"></i> <span class="nav-label">客户服务</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                             <shiro:hasPermission name="potentialstudent:view">
                            <li><a class="J_menuItem" href="/potentialstudent/view.do">潜在学员管理</a>
                            </li>
                             </shiro:hasPermission>
                             <shiro:hasPermission name="studenttail:view">
                            <li><a class="J_menuItem" href="/studenttail/view.do">学员信息跟踪</a>
                            </li>
                             </shiro:hasPermission>
                            
                             <shiro:hasPermission name="studentPool:view">
                            <li><a class="J_menuItem" href="/studentPool/view.do">潜在客户池</a>
                            </li>
                             </shiro:hasPermission>
                             <shiro:hasPermission name="universitytrace:view">
                            <li><a class="J_menuItem" href="/universitytrace/view.do">大客户</a>
                            </li>
                             </shiro:hasPermission>
                             <shiro:hasPermission name="contact:view">
                            <li><a class="J_menuItem" href="/contact/view.do">学校联系人</a>
                            </li>
                             </shiro:hasPermission>
                             <shiro:hasPermission name="designatehistory:view">
                            <li><a class="J_menuItem" href="/designatehistory/view.do">移交管理</a>
                            </li>
                             </shiro:hasPermission>
                              <shiro:hasPermission name="examination:view">
                            <li><a class="J_menuItem" href="/examination/view.do">入学考试</a>
                            </li>
                              </shiro:hasPermission>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-mortar-board"></i> <span class="nav-label">学员管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                             <shiro:hasPermission name="formalStudent:view">
                            <li><a class="J_menuItem" href="/formalStudent/view.do">正式学员</a>
                            </li>
                             </shiro:hasPermission>
                            <shiro:hasPermission name="promotedStudent:view">
                            <li><a class="J_menuItem" href="/promotedStudent/view.do">升班留级</a>
                            </li>
                            </shiro:hasPermission>
                             <shiro:hasPermission name="incomeItem:view">
                            <li><a class="J_menuItem" href="/incomeItem/view.do">收款管理</a>
                            </li>
                             </shiro:hasPermission>
                             <shiro:hasPermission name="runoff:view">
                            <li><a class="J_menuItem" href="/runoff/view.do">流失管理</a>
                            </li>
                             </shiro:hasPermission>
                        </ul>

                    <li>
                        <a href="#"><i class="fa fa-book"></i> <span class="nav-label">教务管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                             <shiro:hasPermission name="classGrade:view">
                            <li><a class="J_menuItem" href="/classGrade/view.do">班级管理</a>
                            </li>
                             </shiro:hasPermission>
                            <shiro:hasPermission name="course:view">
                            <li><a class="J_menuItem" href="/course/view.do">课程管理</a>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="classroom:view">
                            <li><a class="J_menuItem" href="/classroom/view.do">教室管理</a>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="payment:view">
                            <li><a class="J_menuItem" href="/payment/view.do">支出管理</a>
                            </li>
                            </shiro:hasPermission>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-table"></i> <span class="nav-label">日常办公</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <shiro:hasPermission name="salary:view">
                            <li><a class="J_menuItem" href="/salary/view.do">工资管理</a>
                            </li>
                            </shiro:hasPermission>
                             <shiro:hasPermission name="attendance:view">
                            <li><a class="J_menuItem" href="/attendance/view.do">考勤管理</a>
                            </li>
                             </shiro:hasPermission>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-gear"></i> <span class="nav-label">系统管理</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <shiro:hasPermission name="department:view">
                            <li><a class="J_menuItem" href="/department/view.do">部门管理</a>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="employee:view">
                            <li><a class="J_menuItem" href="/employee/view.do">员工管理</a>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="role:view">
                            <li><a class="J_menuItem" href="/role/view.do">角色管理</a>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="systemDictionary:view">
                            <li><a class="J_menuItem" href="/systemDictionary/view.do">数据字典</a>
                            </li>
                            </shiro:hasPermission>
                            <shiro:hasPermission name="systemLog:view">
                            <li><a class="J_menuItem" href="/systemLog/view.do">系统日志</a>
                            </li>
                            </shiro:hasPermission>
                        </ul>
                    </li>

                    <li>
                        <a class="J_menuItem" href="/static/jQuery-css3hover/about_us.html" data-index="0">
                            <i class="fa fa-paw"></i>
                            <span class="nav-label">关于我们</span>
                        </a>
                    </li>
                <br/>
                  <div>
               &emsp; &emsp; &nbsp;
                        <button type="button" class="btn btn-success btn-circle"
                                onclick="signIn()">
                            签到
                        </button>
                   
                   &emsp; 
                        <button type="button" class="btn btn-warning btn-circle"
                                onclick="signOut()">
                            签退
                        </button>
                    
                    </div>
                </ul>
            </div>
            
        </nav>
        <!--左侧导航结束-->
        <!--右侧部分开始-->
        <div id="page-wrapper" class="gray-bg dashbard-1">
           
            <div class="row content-tabs">
                <%--<button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
                </button>--%>
                <nav class="page-tabs J_menuTabs">
                    <div class="page-tabs-content">
                        <%-- <a href="javascript:;" class="active J_menuTab" data-id="index.html">首页</a>--%>
                    </div>
                </nav>
               <%--<button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
                </button>--%>
                <div class="btn-group roll-nav roll-right">
                    <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                    </button>
                    <ul role="menu" class="dropdown-menu dropdown-menu-right">
                        <%--<li class="J_tabShowActive"><a>定位当前选项卡</a>
                        </li>--%>
                        <li class="divider"></li>
                        <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                        </li>
                        <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                        </li>
                    </ul>
                </div>
                <a href="login.do" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-power-off"></i> 退出</a>
            </div>
            <div class="row J_mainContent" id="content-main">
                <iframe class="J_iframe"
                        name="iframe0"
                        width="100%"
                        height="100%"
                        src="main.do"
                        frameborder="0"
                        data-id="index_v1.html"
                        seamless></iframe>
            </div><center>
            <div class="footer">
                <div>&copy; 2018-2099 <a href="http://www.GeniusEducation.com/" target="_blank">Genius Education</a>
                </div>
            </div></center>
        </div>
        <!--右侧部分结束-->

    </div>
    <script type="text/javascript">
         function signIn() {

             //发送请求修改状态
             $.get("/attendance/signIn.do", function (data) {
                 if (data.success) {
                     swal("签到成功！", "", "success");
                     attendance_datagrid.datagrid("load");
                 } else {
                     swal("签到失败!", "", "error");
                 }
             }, "json");
             //签退按钮
         };

         function signOut() {

             //Sweet Alert弹窗效果
             swal({
                 title: "您确定要签退吗?",
                 text: "到下班时间才可以签退哦~",
                 type: "warning",
                 showCancelButton: true,
                 confirmButtonColor: "#DD6B55",
                 confirmButtonText: "确认",
                 cancelButtonText: "取消",
                 closeOnConfirm: false,
                 closeOnCancel: false
             }, function (isConfirm) {
                 if (isConfirm) {
                     //发送请求修改状态
                     $.get("/attendance/signOut.do", function (data) {
                         if (data.success) {
                             swal("签退成功！", "", "success");
                             attendance_datagrid.datagrid("load");
                         } else {
                             swal("签退失败!", "", "error");
                         }
                     });
                 } else {
                     swal("已取消", "您已取消签退！", "error");
                 }
             });
         };
   </script>

</body>

</html>
