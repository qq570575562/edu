$(function () {
    var pos_datagrid = $("#pos_datagrid");
    var pos_dialog = $("#pos_dialog");
    var pos_form = $("#pos_form");
    var objMethod = {
        //添加
        add: function () {
            pos_dialog.dialog("setTitle", "添加潜在学员信息");
            $("#pos_form input").prop("disabled",false);
            $("#saveOrUpdate").linkbutton("enable");
            pos_form.form("clear");
            $("#com1").combobox("setValue",1);
            $("#com2").combobox("setValue",1);
            $("#com3").combobox("setValue",0);
            $("#com4").combobox("setValue",0);
            $("#saveOrUpdate").linkbutton("enable");
            pos_dialog.dialog("open");
        },
        //编辑
        edit: function () {
            var row = pos_datagrid.datagrid("getSelected");
            pos_form.form("clear");
            if (!row) {
                //$.messager.alert('温馨提示', '请选择一行数据', "warning");
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
            $("#pos_form input").prop("disabled",false);
            $("#saveOrUpdate").linkbutton("enable");
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
            if (row.student!=null){
                row["student.id"] = row.student.id;
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
            pos_dialog.dialog("setTitle", "编辑潜在学员");
            pos_form.form("load", row);
            pos_dialog.dialog("open");
        },
        //查看学生信息
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
            pos_dialog.dialog("setTitle", "编辑潜在学员");
            pos_form.form("load", row);
            $("#pos_form input").prop("disabled",true);
            $("#saveOrUpdate").linkbutton("disable");
            pos_dialog.dialog("open");
        },

        searchstate:function () {
            pos_datagrid.datagrid("load",{
                state : false
            });
        },
        //转为正式员工
        changState: function () {
            var row = pos_datagrid.datagrid("getSelected");
            if (!row) {
                //$.messager.alert('温馨提示', '请选择一行数据', "warning");  原生弹窗效果

                swal({
                    title: "请选择一条数据",
                    //text: "删除后将无法恢复，请谨慎操作！",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "好的",
                    closeOnConfirm: false
                });

                return;
            }
            row["psid"]=row.id
            if (row.source!=null){
                row["source.id"] = row.source.id;
            }
            if (row.qq!=null){
                row["qqNum"] = row.qq;
            }
            if (row.classId!=null){
                row["clz.id"] = row.classId.id;
            }
            if (row.sale!=null){
                row["saleman.id"] = row.sale.id;
            }
            row["psid"] = row.id;
            if (row.intentionSubject!=null){
                row["intentionSubject.id"] = row.intentionSubject.id;
            }
            if (row.intentionSchool!=null){
                row["campus.id"] = row.intentionSchool.id;
            }
            if (row.education!=null){
              row["education.id"] = row.education.id;
            }
            $("#formalStudent_form").form("load",row);
            $("#formalStudent_dialog").dialog("open");
            /*// Sweet Alert弹窗效果
            swal({
                title: "您确定要执行该操作吗?",
                text: "请谨慎操作！",
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
                    $.get("/potentialstudent/changState.do", {id: row.id}, function (data) {
                        if (data.success) {
                            swal("转正成功！", "", "success");
                            pos_datagrid.datagrid("reload");
                        }
                    }, "json")
                } else {
                    swal("已取消", "您已取消操作！", "error")
                }
            })*/
        },
        //添加考试弹出框
        exam:function () {
            var row = pos_datagrid.datagrid("getSelected");
            if (!row) {
                //$.messager.alert('温馨提示', '请选择一行数据', "warning");
                swal({title: "请选择一行数据!", text: "", type: "warning"})
                return;
            }
            if (row.state) {
                //$.messager.alert('温馨提示', '正式学员不能申请考试', "warning");
                swal({title: "正式学员不能申请考试!", text: "", type: "warning"})
                return;
            }
            $("#exam_form").form("clear");
            //回显数据
            row["psId"] = row.id;
            row["result"]=0;
            if (row.classId!=null){
                row["classId.id"] = row.classId.id;
            }
            if (row.sale!=null){
                row["saleman.id"] = row.sale.id;
            }
            if (row.qq!=null){
                row["qq"] = row.qq;
            }
            //读取数据
            $("#exam_form").form("load",row);
            $("#name1").textbox("disable");
            $("#tel1").textbox("disable");
            $("#exam_dialog").dialog("open");
        },
        //添加跟踪弹出框
        changtial: function () {
            var row = pos_datagrid.datagrid("getSelected");
            if (!row) {
                //$.messager.alert('温馨提示', '请选择一行数据', "warning");
                swal({
                    title: "请选择一条数据",
                    //text: "删除后将无法恢复，请谨慎操作！",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "好的",
                    closeOnConfirm: false
                });

                return;
            }
            if (row.state){
                //$.messager.alert('温馨提示', '正式学员不能跟踪', "warning");
                swal({title: "正式学员不能跟踪!", text: "", type: "warning"})
                return;
            }
            $("#st_form").form("clear");
            row["tailnum"] = row.tailnum;
            row["pid"] = row.id ;
            if (row.sale!=null){
                row["sale.id"] = row.sale.id;
            }
            if (row.classId!=null){
                row["classId.id"] = row.classId.id;
            }
            if (row.intentionDegree!=null){
                row["intentiondegree.id"] = row.intentionDegree.id;
            }
            if (row.universitytrace!=null){
                row["university.id"] = row.universitytrace.id;
            }
            if (row.followTime!=null){
                row["followtime"]=row.followTime;
            }
            if (row.lastTime!=null){
                row["lasttime"]=row.lastTime;
            }
            $("#st_form").form("load",row);
            $("#stcom1").combobox("setValue",0);
            $("#st_dialog").dialog("open");

        },
        //打开高级查询窗口
        searchOp:function () {
            $("#searchForm").form("clear");
            $("#search_dialog").dialog("open");
        },
        //重新刷新当前页面
        reload: function () {
            pos_datagrid.datagrid("load",[]);
        },
        //取消高级查询窗口
        searchCancel:function () {
            $("#search_dialog").dialog("close");
        },
        //关闭考试窗口
        examcancel:function () {
            $("#exam_dialog").dialog("close");
        },
        fscancel:function () {
            $("#formalStudent_dialog").dialog("close");
        },
        //关闭潜在学员窗口
        cancel: function () {
            pos_dialog.dialog("close");
        },
        //关闭添加跟踪窗口
        stcancel:function () {
            $("#st_dialog").dialog("close");
        },
        //添加修改潜在学员
        saveOrUpdate: function () {
            pos_form.form("submit", {
                url: "/potentialstudent/saveOrUpdate.do",
                success: function (data) {
                    data = $.parseJSON(data)
                    if (data.success) {
                        //$.messager.alert('温馨提示', '保存成功', "info");
                        swal({title: "保存成功!", text: "", type: "success"})
                        pos_dialog.dialog("close");
                        pos_datagrid.datagrid("reload");
                    } else {
                        //$.messager.alert('温馨提示', data.msg, "error");
                        swal("保存失败！", "", "error");
                    }
                }
            });
        },
        toPool:function () {
            var row = pos_datagrid.datagrid("getSelected");
            if (!row) {
                //$.messager.alert('温馨提示', '请选择一行数据', "warning");
                swal({
                    title: "请选择一条数据",
                    //text: "删除后将无法恢复，请谨慎操作！",
                    type: "warning",
                    showCancelButton: false,
                    confirmButtonColor: "#DD6B55",
                    confirmButtonText: "好的",
                    closeOnConfirm: false
                });

                return;
            }
            if (row.state){
                //$.messager.alert('温馨提示', '正式学员不能跟踪', "warning");
                swal({title: "正式学员不能放入资源池!", text: "", type: "warning"})
                return;
            }
            swal({
                title: "您确定要执行该操作吗?",
                text: "请谨慎操作！",
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
                    $.get("/potentialstudent/toPool.do", {id: row.id}, function (data) {
                        if (data.success) {
                            swal("操作成功！", "", "success");
                            pos_datagrid.datagrid("load");
                        }
                    }, "json")
                } else {
                    swal("已取消", "您已取消操作！", "error")
                }
            })

           /* $.get("/potentialstudent/toPool.do",{id:row.id},function (data) {
                if (data.success){
                    pos_datagrid.datagrid("load");
                }else{
                    swal("移动失败！", data.msg, "error");
                }
            })*/
        },
        examsave:function () {
            $("#exam_form").form("submit",{
                url:"/examination/saveOrUpdate.do",
                success: function (data) {
                    data = $.parseJSON(data)
                    if (data.success) {
                        //$.messager.alert('温馨提示', '保存成功', "info");
                        swal({title: "申请成功!", text: "", type: "success"})
                        $("#exam_dialog").dialog("close");
                    } else {
                        //$.messager.alert('温馨提示', data.msg, "error");
                        swal({title: "保存失败!", text: data.msg, type: "error"})
                    }
                }
            })
        },
        fssave:function () {
            $("#formalStudent_form").form("submit",{
                url:"/formalStudent/saveOrUpdate.do",
                success: function (data) {
                    console.log(data)
                    data = $.parseJSON(data)
                    if (data.success) {
                        //$.messager.alert('温馨提示', '保存成功', "info");
                        swal({title: "保存成功!", text: "", type: "success"})
                        $("#formalStudent_dialog").dialog("close");
                        $("#pos_datagrid").datagrid("reload");
                    } else {
                        //$.messager.alert('温馨提示', data.msg, "error");
                        swal({title: "保存失败!", text: data.msg, type: "error"})
                    }
                }
            })
        },
        //表单提交
        searchForm: function () {
            $("#search_dialog").dialog("close");
            var keyword = $("#keyword").textbox("getValue");
            var minAge = $("#minAge").textbox("getValue");
            var maxAge = $("#maxAge").textbox("getValue");
            var intentionSubjectId = $("#intentionSubjectId").combobox("getValue");
            var intentionSchoolId = $("#intentionSchoolId").combobox("getValue");
            var intentionDegreeId = $("#intentionDegreeId").combobox("getValue");
            var startTime = $("#startTime").datebox("getValue");
            var endTime = $("#endTime").datebox("getValue");
            //高级查询要让整个表单提交
            pos_datagrid.datagrid("load", {
                keyword: keyword,
                minAge:minAge,
                maxAge:maxAge,
                intentionSubjectId:intentionSubjectId,
                intentionSchoolId:intentionSchoolId,
                intentionDegreeId:intentionDegreeId,
                startTime:startTime,
                endTime:endTime
            });
        }
    }
    $("[data-cmd]").click(function () {
        var method = $(this).data("cmd");
        objMethod[method]();
    });
    //潜在学员查询
    pos_datagrid.datagrid({
        fit: true,
        url: "/potentialstudent/query.do",
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
                return value ? '正式学员' : '<font color="red">非正式学员</font>'
            }} ,
            {
                field: 'tail', title: '跟踪', width: 100, formatter: function (value, row, index) {
                return value ? '<font color="#daa520">已跟踪</font>' : '未跟踪'
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
        title: '添加潜在学员信息',
        width: 640,
        height: 530,
        closed: true,
        buttons: "#pos_buttons"
    });
    //初始化高级查询窗口
    $("#search_dialog").dialog({
        title:'高级查询',
        width: 350,
        height: 270,
        closed: true,
        buttons: "#search_buttons"
    })
    //初始化跟踪窗口
    $("#st_dialog").dialog({
        //初始化弹出框
        title: '添加客户跟踪',
        width: 450,
        height: 470,
        closed: true,
        buttons: "#st_buttons"
    });
    $("#exam_dialog").dialog({
        title: '申请考试',
        width: 240,
        height: 280,
        closed: true,
        buttons:"#exam_buttons"
    });
    $("#formalStudent_dialog").dialog({
        title:"转正",
        width: 700,
        height: 530,
        closed: true,
        buttons:"#fs_buttons"
    })
});
function inserttail() {
    $("#st_form").form("submit",{
        url: "/studenttail/saveOrUpdate.do",
        success: function (data) {
            data = $.parseJSON(data)
            if (data.success) {
                //$.messager.alert('温馨提示', '保存成功', "info");
                swal("保存成功！", "", "success");
                $("#pos_datagrid").datagrid("reload");
                $("#st_dialog").dialog("close");
            } else {
                //$.messager.alert('温馨提示', data.msg, "error");
                swal("保存失败！", "", "error");
            }
        }
    });
}