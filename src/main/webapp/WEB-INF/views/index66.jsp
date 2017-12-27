<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>


<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout 后台大布局 - Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">

    <script>
        layui.use('element', function(){
            var element = layui.element;

            //获取hash来切换选项卡，假设当前地址的hash为lay-id对应的值
            var layid = location.hash.replace(/^#test1=/, '');
            element.tabChange('test1', layid); //假设当前地址为：http://a.com#test1=222，那么选项卡会自动切换到“发送消息”这一项

            //监听Tab切换，以改变地址hash值
            element.on('tab(test1)', function(){
                location.hash = 'test1='+ this.getAttribute('lay-id');
            });
        });
    </script>

</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">layui 后台布局</div>


        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
            <li class="layui-nav-item"><a href="">商品管理</a></li>
            <li class="layui-nav-item"><a href="">用户</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="/static/layui/images/Albert.Einstein.jpg" class="layui-nav-img">
                    Albert.Einstein
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree" lay-filter="test">
                <li class="layui-nav-item"><a href="main.do">首页</a></li>
                <li class="layui-nav-item"><a href="#">计划</a></li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">统计图表</a>
                    <dl class="layui-nav-child">
                        <dd><a href="javascript:;">正式学员报表</a></dd>
                        <dd><a href="javascript:;">流失人员报表</a></dd>
                        <dd><a href="javascript:;">图表组合</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">客户服务</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/potentialstudent/view.do" target="demo">潜在学员管理</a></dd>
                        <dd><a href="javascript:;">学员信息跟踪</a></dd>
                        <dd><a href="/view.do">潜在客户池</a></dd>
                        <dd><a href="/universitytrace/view.do">大客户</a></dd>
                        <dd><a href="/contact/view.do">学校联系人</a></dd>
                        <dd><a href="javascript:;">移交管理</a></dd>
                        <dd><a href="/examination/view.do">入学考试</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">学员管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/potentialstudent/view.do" target="right">潜在学员管理</a></dd>
                        <dd><a href="javascript:;">正式学员</a></dd>
                        <dd><a href="javascript:;">升班留级</a></dd>
                        <dd><a href="javascript:;">收款管理</a></dd>
                        <dd><a href="javascript:;">流失管理</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;">学员管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/potentialstudent/view.do" class target="demo">教务管理</a></dd>
                        <dd><a href="/classGrade/view.do">班级管理</a></dd>
                        <dd><a href="/course/view.do">课程管理</a></dd>
                        <dd><a href="/classromm/view.do">教室管理</a></dd>
                        <dd><a href="spinners.html">支出管理</a></dd>
                    </dl>
                </li>


            </ul>
        </div>
    </div>

    <div class="layui-body">
        <!-- 内容主体区域 -->
        <div style="padding: 15px;">
            内容主体区域

            <br><br>
            <blockquote class="layui-elem-quote">
                layui 之所以赢得如此多人的青睐，更多是在于它“前后台系统通吃”的能力。既可编织出绚丽的前台页面，又可满足繁杂的后台功能需求。
                <br>layui 后台布局， 致力于让每一位开发者都能轻松搭建自己的后台模板。
            </blockquote>

            <a href="/doc/element/layout.html#admin" target="_blank" class="layui-btn layui-btn-big">获取该布局代码</a>

            <br><br><br><br>


            下面是充数内容，为的是出现滚动条<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>充数内容<br>
        </div>
    </div>


    <center>
        <div class="layui-footer">
            <!-- 底部固定区域 -->
            © layui.com - 底部固定区域
        </div>
    </center>
</div>
<script src="/static/layui/layui.js" charset="utf-8"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function () {
        var element = layui.element;

    });
</script>
<script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cspan id='cnzz_stat_icon_30088308'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "w.cnzz.com/c.php%3Fid%3D30088308' type='text/javascript'%3E%3C/script%3E"));</script>

<%--<div id="main">
    <iframe name="right" id="rightMain" src="" tar
            frameborder="no" scrolling="auto" width="100%" height="100%"
            allowtransparency="true"/>
</div>--%>

<iframe src="main.do" frameborder="0" scrolling="yes" height="100px" width="100px" noresize="noresize"></iframe>

</body>
</html>
