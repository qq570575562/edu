$(function () {
    var exa_datagrid = $("#exa_datagrid");
   var exa_dialog = $("#exa_dialog");
    var exa_form = $("#exa_form");
    var objMethod = {
        add: function () {
            exa_dialog.dialog("setTitle", "添加考试");
            exa_form.form("clear");
            $("#com1").combobox("setValue",0);
            exa_dialog.dialog("open");
        },
        remove:function () {
            var row = exa_datagrid.datagrid("getSelected");
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

                return;
            }

            //Sweet Alert弹窗效果
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
                    $.get("/examination/delete.do", {id: row.id}, function (data) {
                        if (data.success) {
                            swal("操作成功！", "如误操作,请重复刚才步骤!", "success");
                            exa_datagrid.datagrid("reload");
                        }
                    }, "json")
                } else {
                    swal("已取消", "您已取消操作！", "error")
                }
            })

        },
        edit: function () {
            var row = exa_datagrid.datagrid("getSelected");
            exa_form.form("clear");
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
            exa_dialog.dialog("setTitle", "编辑考试");
            if (row.saleman!=null){
                row["saleman.id"] = row.saleman.id;
            }
            if (row.handler!=null){
                row["handler.id"] = row.handler.id;
            }
            if (row.classId!=null){
                row["classId.id"] = row.classId.id;
            }
            exa_form.form("load", row);
            exa_dialog.dialog("open");
        },
        exportXls:function () {
            //Sweet Alert弹窗效果
            //发送请求修改状态
            /*$.messager.confirm("温馨提示","确定要导出吗",function (r) {
                if (r){
                    window.location.href='/examination/exportXls.do'
                }
            })*/


            swal({
                title: "您确定要执行该导出操作吗?",
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
                    window.location.href='/examination/exportXls.do'
                    swal("导出成功!", "", "success")
                } else {
                    swal("已取消", "您已取消操作！", "error")
                }
            })




        },
        changState: function () {
            var row = exa_datagrid.datagrid("getSelected");
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

                return;
            }

            //Sweet Alert弹窗效果
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
                    $.get("/examination/changState.do", {id: row.id}, function (data) {
                        if (data.success) {
                            swal("操作成功！", "如误操作,请重复刚才步骤!", "success");
                            exa_datagrid.datagrid("reload");
                        }
                    }, "json")
                } else {
                    swal("已取消", "您已取消操作！", "error")
                }
            })


            /*$.messager.confirm("温馨提示", "你确定操作吗？", function (r) {
                if (r) {
                    $.get("/examination/changState.do", {id: row.id}, function (data) {
                        if (data.success) {
                            $.messager.alert('温馨提示', '操作成功!', 'info');
                            exa_datagrid.datagrid("reload");
                        } else {
                            $.messager.alert('温馨提示', data.msg, "error");
                        }
                    })
                }
            })*/

        },
        reload: function () {
            exa_datagrid.datagrid("load",[])
        },
        cancel: function () {
            exa_dialog.dialog("close");
        },
        saveOrUpdate: function () {
            exa_form.form("submit", {
                url: "/examination/saveOrUpdate.do",
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        $.messager.alert('温馨提示', '保存成功', "info");
                        exa_dialog.dialog("close");
                        exa_datagrid.datagrid("reload");
                    } else {
                        //$.messager.alert('温馨提示', data.msg, "error");
                        swal("保存失败!", "", "error")

                    }
                }
            });
        },
        searchForm: function () {
            var psId = $("#potentialStudentId").combobox("getValue");
            //高级查询要让整个表单提交
            exa_datagrid.datagrid("load", {
                psId: psId
            });
        }
    }
    $("[data-cmd]").click(function () {
        var method = $(this).data("cmd");
        objMethod[method]();
    });
    exa_datagrid.datagrid({
        fit: true,
        url: "/examination/query.do",
        pagination: true,
        striped: true,
        fitColumns: true,
        rownumbers : true,
        singleSelect: true,
        shadow:true,
        toolbar: '#toolbar',
        columns: [[
            {field: 'id', title: '考试编号', width: 100},
            {field: 'name', title: '姓名', width: 100},
            {field: 'qq', title: 'QQ', width: 100},
            {field: 'tel', title: '电话', width: 100},
            {field: 'examtime', title: '考试时间', width: 100},
            {
                field: 'result', title: '考试结果', width: 100, formatter: function (value, row, index) {
                return value ? "通过" : "<font color='red'>没通过</font>"
            }
            },
            {
                field: 'saleman', title: '营销人员', width: 100, formatter: function (value, row, index) {
                return row.saleman ? value.realname : ''
            }
            },
            {
                field: 'handler', title: '审核人员', width: 100, formatter: function (value, row, index) {
                return row.handler ? value.realname : ''
            }
            },
            {
                field: 'classId', title: '意向班级', width: 100, formatter: function (value, row, index) {
                return row.classId ? value.className : ''
            }
            }
        ]],
        onClickRow: function (index, row) {
            if (row.result) {
                $("#stateId").linkbutton({
                    text: "审核不通过"
                });
            } else {
                $("#stateId").linkbutton({
                    text: "审核通过"
                });
            }
        }
    });
    exa_dialog.dialog({
        //初始化弹出框
        title: '新增考试',
        width: 240,
        height: 340,
        modal:true,
        closed: true,
        buttons: "#exa_buttons"
    });
});