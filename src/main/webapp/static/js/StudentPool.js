$(function () {
    var pos_datagrid = $("#pos_datagrid");
    var pos_dialog = $("#pos_dialog");
    var pos_form = $("#pos_form");
    var objMethod = {
        searchone:function () {
            var row = pos_datagrid.datagrid("getSelected");
            pos_form.form("clear");
            if (!row) {
                swal({
                    title: "请选择一条数据",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "好的",
                    closeOnConfirm: false
                });
                return;
            }
            if (row.source!=null){
                row["source.id"] = row.source.id;
            }
            if (row.classId!=null){
                row["classId.id"] = row.classId.id;
            }
            if (row.sale!=null){
                row["sale.id"] = row.sale.id;
            }
            if (row.universitytrace!=null){
                row["universitytrace.id"] = row.universitytrace.id;
            }
            if (row.intentionSchool!=null){
                row["intentionSchool.id"] = row.intentionSchool.id;
            }
            if (row.intentionSubject!=null){
                row["intentionSubject.id"] = row.intentionSubject.id;
            }
            if (row.education!=null){
                row["education.id"] = row.education.id;
            }
            if (row.intentionDegree!=null){
                row["intentionDegree.id"] = row.intentionDegree.id;
            }
            pos_form.form("load", row);
            $("#pos_form input").prop("disabled",true);
            $("#saveOrUpdate").linkbutton("disable");
            pos_dialog.dialog("open");
        },
        redocancel:function () {
            $("#redo_dialog").dialog("close");
        },
        add:function () {
            $("#redo_form").form("submit",{
               url:"/studentPool/redo.do",
               success: function (data) {
                   swal("操作成功！",'指派成功', "success");
                   //$.messager.alert('温馨提示', '指派成功', "info");
                   $("#redo_dialog").dialog("close");
                   //刷新页面
                   pos_datagrid.datagrid("load");
               }
            });
        },
        selecttime:function () {
            pos_datagrid.datagrid("load",{
                startTime:$("#beginTime").datebox("getValue"),
                endTime:$("#endTime").datebox("getValue")
            })
        },
        //移交打开窗口  读取数据回显
        redo:function () {
            var row = pos_datagrid.datagrid("getSelected");
            $("#redo_form").form("clear");
            if (!row) {
                swal({
                    title: "请选择一条数据",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "好的",
                    closeOnConfirm: false
                });
                return;
            }
            row["poolId"] = row.id ;
            if (row.qq!=null){
                row["qq"]=row.qq;
            }
            if (row.sale!=null){
                row["source.id"] = row.sale.id;
            }
            if (row.classId!=null){
                row["classId.id"] = row.classId.id;
            }
            if (row.intentionDegree!=null){
                row["intentiondegree.id"] = row.intentionDegree.id;
            }
            $("#redo_form").form("load",row);
            $("#redo_dialog").dialog("open")
        },
        //关闭潜在学员窗口
        cancel: function () {
            pos_dialog.dialog("close");
        },
        //重新刷新当前页面
        reload: function () {
            $("#beginTime").datebox("clear");
            $("#endTime").datebox("clear");
            pos_datagrid.datagrid("load",[]);
        }
    }
    $("[data-cmd]").click(function () {
        var method = $(this).data("cmd");
        objMethod[method]();
    });
    //潜在学员查询
    pos_datagrid.datagrid({
        fit: true,
        url: "/studentPool/query.do",
        pagination: true,
        striped: true,
        fitColumns: true,
        rownumbers : true,
        singleSelect: true,
        shadow:true,
        toolbar: '#toolbar',
        columns: [[
            {field: 'name', title: '姓名', width: 100},
            {field: 'sale', title: '营销人员', width: 100, formatter: function (value, row, index) {
                return row.sale ? value.realname : ''
            }},
            {field: 'qq', title: 'QQ', width: 100},
            {field: 'age', title: '年龄', width: 100},
            {field: 'email', title: '邮箱', width: 100},
            {field: 'tailnum', title: '跟踪次数', width: 100},
            {field: 'tel', title: '电话', width: 100},
            {field: 'universitytrace', title: '学校', width: 100,formatter:function (value,row,index) {
                return row.universitytrace ? value.name : '' ;
            }},
            {field: 'lastTime', title: '最后跟进时间', width: 100},
            {field: 'aboutTime', title: '约访日期', width: 100},
            {field: 'followTime', title: '下次跟进时间', width: 100},
            {field: 'intentionDegree', title: '意向程度', width: 100,formatter:function (value,row,index) {
                return row.intentionDegree ? value.name : '' ;
            }},
            {field: 'intentionSchool', title: '意向校区', width: 100,formatter:function (value,row,index) {
                return row.intentionSchool ? value.name : '' ;
            }},
            {
                field: 'state', title: '状态', width: 100, formatter: function (value, row, index) {
                return value ? '<font color="#37C40A">正式学员</font>' : '非正式学员'
            }} ,
            {
                field: 'tail', title: '跟踪', width: 100, formatter: function (value, row, index) {
                return value ? '<font color="#8a2be2">已跟踪</font>' : '未跟踪'
            }} ,
            {field: 'remark', title: '备注', width: 100}
        ]],
        onClickRow: function (index, row) {
            if (row.state) {
                $("#stateId").linkbutton('disable');
            } else {
                $("#stateId").linkbutton('enable');
            }
        }
    });
    //初始化添加潜在学员窗口
    pos_dialog.dialog({
        title: '查看潜在学员信息',
        width: 680,
        height: 510,
        closed: true,
        buttons: "#pos_buttons"
    });
    $("#redo_dialog").dialog({
        title: '移交',
        width: 390,
        height: 260,
        closed: true,
        buttons:"#redo_buttons"
    })
});