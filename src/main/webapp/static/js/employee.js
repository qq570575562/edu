$(function () {
    var emp_datagrid = $("#emp_datagrid");
    var emp_dialog = $("#emp_dialog");
    var emp_form = $("#emp_form");
    var objMethod = {
        add: function () {

            $("#emp_password").show();
            emp_dialog.dialog("setTitle", "添加员工");
            emp_form.form("clear");
            emp_dialog.dialog("open");
        },
        edit: function () {
            $("#emp_password").hide();
            var row = emp_datagrid.datagrid("getSelected");
            emp_form.form("clear");
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
            $.get("/employee/selectByEmployeeId.do", {id: row.id}, function (data) {
                $("#role_id").combobox("setValues", data);
            }, "json");
            emp_dialog.dialog("setTitle", "编辑员工");
            if (row.dept != null) {
                row["dept.id"] = row.dept.id;
            }
            emp_form.form("load", row);
            emp_dialog.dialog("open");
        },
        changState: function () {
            var row = emp_datagrid.datagrid("getSelected");
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

            // Sweet Alert弹窗效果
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
                    $.get("/employee/changState.do", {id: row.id}, function (data) {
                        if (data.success) {
                            swal("操作成功！", "如误操作,请重复刚才步骤!", "success");
                            emp_datagrid.datagrid("reload");
                        }
                    }, "json")
                } else {
                    swal("已取消", "您已取消操作！", "error")
                }
            })


            /*原生弹窗效果
            $.messager.confirm("温馨提示", "你确定操作吗？", function (r) {
                if (r) {
                    $.get("/employee/changState.do", {id: row.id}, function (data) {
                        if (data.success) {
                            $.messager.alert('温馨提示', '操作成功!', 'info');
                            emp_datagrid.datagrid("reload");
                        } else {
                            $.messager.alert('温馨提示', data.msg, "error");
                        }
                    })
                }
            })*/

        },
        reload: function () {
            emp_datagrid.datagrid("load")
        },
        cancel: function () {
            emp_dialog.dialog("close");
        },
        saveOrUpdate: function () {
            emp_form.form("submit", {
                url: "/employee/saveOrUpdate.do",
                onSubmit: function (param) {
                    var ids = $("#role_id").combobox("getValues");
                    for (var i = 0; i < ids.length; i++) {
                        param["roles[" + i + "].id"] = ids[i];
                    }
                    return true;
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        //$.messager.alert('温馨提示', '保存成功', "info");
                        swal("保存成功!", "", "success")
                        emp_dialog.dialog("close");
                        emp_datagrid.datagrid("reload");
                    } else {
                        //$.messager.alert('温馨提示', data.msg, "error");
                        swal("保存失败", "请检查输入数据是否有误！", "error")
                    }
                }
            })
        },
        searchForm: function () {
            var keyword = $("#keyword").textbox("getValue");
            //高级查询要让整个表单提交
            emp_datagrid.datagrid("load", {
                keyword: keyword
            });
        }
    }
    $("[data-cmd]").click(function () {
        var method = $(this).data("cmd");
        objMethod[method]();
    });
    emp_datagrid.datagrid({
        fit: true,
        url: "/employee/query.do",
        pagination: true,
        striped: true,
        fitColumns: true,
        rownumbers: true,
        singleSelect: true,
        shadow: true,
        toolbar: '#toolbar',
        columns: [[
            {field: 'username', title: '用户名', width: 100},
            {field: 'realname', title: '真实名称', width: 100},
            {field: 'tel', title: '电话', width: 100},
            {field: 'email', title: '邮箱', width: 100},
            {field: 'inputTime', title: '入职时间', width: 100},
            {field: 'salary', title: '薪资', width: 100},
            {
                field: 'admin', title: '超级管理员', width: 100, formatter: function (value, row, index) {
                    return value ? '是' : '否'
                }
            },
            {
                field: 'state', title: '在职状态', width: 100, formatter: function (value, row, index) {
                    return value ? "在职" : "<font color='red'>离职</font>"
                }
            },
            {
                field: 'dept', title: '部门', width: 100, formatter: function (value, row, index) {
                    return value ? value.name : ''
                }
            }
        ]],
        onClickRow: function (index, row) {
            if (row.state) {
                $("#stateId").linkbutton({
                    text: "离职"
                });
            } else {
                $("#stateId").linkbutton({
                    text: "入职"
                });
            }
        }
    });
    emp_dialog.dialog({
        //初始化弹出框
        title: '添加员工',
        width: 240,
        height: 385,
        modal:true,
        closed: true,
        buttons: "#emp_buttons"
    });
});
