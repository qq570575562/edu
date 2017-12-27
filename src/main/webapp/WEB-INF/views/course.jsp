<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="/static/plugin/jquery-easyui/themes/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/static/plugin/jquery-easyui/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="/static/plugin/calendar/calender.css" tppabs="http://www.17sucai.com/preview/622817/2017-03-03/calendar/calender.css">
    <script type="text/javascript" src="/static/plugin/jquery-easyui/jquery.min.js"></script>
    <%--<script src="/static/plugin/calendar/calendar.js"></script>--%>
    <script src="/static/plugin/calendar/calendar.js" tppabs="http://www.17sucai.com/preview/622817/2017-03-03/calendar/js/calendar.js"></script>
    <script type="text/javascript" src="/static/plugin/jquery-easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="/static/plugin/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
    <script src="/static/js/course.js"></script>
    <style type="text/css">
        html {
            font: 500 14px 'roboto';
            color: #333;
            background-color: #fafafa;
        }
        a {
            text-decoration: none;
        }
        ul, ol, li {
            list-style: none;
            padding: 0;
            margin: 0;
        }
        #demo {
            width: 290px;
        }
        p {
            margin: 0;
        }
        /*#dt {*/
            /*!*margin: 100px auto;*!*/
            /*height: 28px;*/
            /*width: 250px;*/
            /*padding: 0 6px;*/
            /*border: 1px solid #ccc;*/
            /*outline: none;*/
        /*}*/
    </style>

    <title>课程</title>
</head>

<body>

<div>
    <div id="demo" style="float:left;">
        <div id="ca"></div>

        <div id="date_datagrid">

        </div>

    </div>
        <div id="course_datagrid" >

        </div>
</div>
<script>
    $('#ca').calendar({
        width: 280,
        height: 250,
        data: [
            {
                date: '2017/12/24',
                value: 'Christmas Eve'
            },
            {
                date: '2017/12/25',
                value: 'Merry Christmas'
            },
            {
                date: '2017/01/01',
                value: 'Happy New Year'
            }
        ],
        onSelected: function (view, date, data) {
           $("#course_datagrid").datagrid("load",{
              'date':date
           });
        },
        onSelect:function (date) {
            $("#course_datagrid").datagrid("load",{
                'date':date
            });
        }
    });

    $('#dd').calendar({
        trigger: '#dt',
        zIndex: 999,
        format: 'yyyy-MM-dd',
        onSelected: function (view, date, data) {
            console.log('event: onSelected')
        },
        onClose: function (view, date, data) {
            console.log('event: onClose')
            console.log('view:' + view)
            console.log('date:' + date)
            console.log('data:' + (data || 'None'));
        }
    });
</script>
    <div id="toolbar">

        班级:<input id="classGrade" class="easyui-textbox" name="classGrade" prompt="班级名">&nbsp;
        教室:<input id="classroom" class="easyui-textbox" name="classroom" prompt="教室名">&nbsp;
        课程名: <input id="courseName" class="easyui-textbox" name="courseName" prompt="课程名">&nbsp;
        老师:<input id="teacher" class="easyui-textbox" name="teacher" prompt="讲师名"><br>
        日期:<input id="beginTime" class="easyui-datebox" name="beginTime">~<input id="endTime" class="easyui-datebox" name="endTime">
        <a  href="javascript:;"  class="easyui-linkbutton" onclick="searchList()" iconCls="icon-search" plain=true >搜索</a>
        <a class="easyui-linkbutton" onclick="load()" data-options="plain:true,iconCls:'icon-reload'">查看全部</a>
    </div>
</body>
</html>
