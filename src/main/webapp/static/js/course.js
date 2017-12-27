$(function () {
    $("#course_datagrid").datagrid({
        // fit: true,
        url: "/course/query.do",
        pagination: true,
        striped: true,
        fitColumns: true,
        rownumbers : true,
        singleSelect: true,
        shadow:true,
        width:863,
        height:511,
        toolbar:'#toolbar',
        columns: [[
            {field: 'date', title: '日期', width: 100,sortable:true},
            {field: 'weekday', title: '星期', width: 100},
            {
                field: 'classGrade', title: '班级', width: 100, formatter: function (value, row, index) {
                return value ? value.className:''
            }
            },
            {field: 'courseName', title: '课程名称', width: 100},
            {
                field: 'leader', title: '班主任', width: 100, formatter: function (value, row, index) {
                return value ? value.realname : '';
            }
            },
            {field: 'teacher', title: '讲师', width: 100 ,formatter: function (value, row, index) {
                return value ? value.realname : '';
            }},
            {
                field: 'classroom', title: '教室', width: 100, formatter: function (value, row, index) {
                return value ? value.name : '';
            }

            },
            {field: 'remark', title: '备注', width: 100},
            {
                field: 'state', title: '状态', width: 100, formatter: function (value, row, index) {
                return value ? '已上': '未上';
            }
            }
        ]]

    });
    $("#date_datagrid").datagrid({
        url: "/classGrade/selectAll.do",
        striped: true,
        fitColumns: true,
        singleSelect: true,
        shadow:true,
        width:284,
        height:259,

        columns:[[
            {field:'className',title:'班级名称',width:100,align:'center'}
        ]],
        onDblClickRow:function (index,row) {
            $("#course_datagrid").datagrid("load",{
                classId:row.id
            });
        }


    })
});
function searchList() {
    var classGrade = $("#classGrade").textbox("getValue");
    var classroom = $("#classroom").textbox("getValue");
    var teacher = $("#teacher").textbox("getValue");
    var courseName = $("#courseName").textbox("getValue");
    var beginTime = $("#beginTime").datebox("getValue");
    var endTime = $("#endTime").datebox("getValue");
    $("#course_datagrid").datagrid('load',{
        "classGrade":classGrade,
        "classroom":classroom,
        "teacher":teacher,
        "beginTime":beginTime,
        "endTime":endTime,
        "courseName":courseName
    });
}
function load() {
    $("#classGrade").textbox("reset");
    $("#classroom").textbox("reset");
    $("#teacher").textbox("reset");
    $("#courseName").textbox("reset");
    $("#beginTime").datebox("reset");
    $("#endTime").datebox("reset");
    $("#course_datagrid").datagrid("load",[]);
}