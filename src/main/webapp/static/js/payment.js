$(function () {
    var payment_datagrid = $("#payment_datagrid");
    var payment_dialog = $("#payment_dialog");
    var payment_form = $("#payment_form");
    var objMethod = {
        add: function () {

            payment_dialog.dialog("setTitle", "添加支出");
            payment_form.form("clear");
            payment_dialog.dialog("open");
        },
        edit: function () {
            var row = payment_datagrid.datagrid("getSelected");
            payment_form.form("clear");
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
            /*  $.get("/payment/selectRoleId.do",{id:row.id},function (data) {
                 $("#role_id").combobox("setValues",data);
              });*/
            payment_dialog.dialog("setTitle", "编辑员工");
            if (row.payer != null) {
                row["payer.id"] = row.payer.id;
            }
            if (row.classGrade != null) {
                row["classGrade.id"] = row.classGrade.id;
            }
            if (row.brokerage != null) {
                row["brokerage.id"] = row.brokerage.id;
            }
            if (row.paymode != null) {
                row["paymode.id"] = row.paymode.id;
            }
            payment_form.form("load", row);
            payment_dialog.dialog("open");
        },

        export: function () {
            /*$.messager.confirm("温馨提示","您确定要导出吗?",function (r) {
                if(r){
                    window.location.href = "/payment/export.do";
                }
            })*/

            //Sweet Alert弹窗效果
            swal({
                title: "您确定要导出吗?",
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
                        window.location.href = "/payment/export.do";
                        swal("导出成功!", "", "success")
                } else {
                    swal("已取消", "您已取消操作!", "error")
                }
            })


        },
        remove: function () {
            var row = payment_datagrid.datagrid("getSelected");
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
                    $.get("/payment/remove.do", {id: row.id}, function (data) {
                        if (data.success) {
                            swal("删除成功！", data.msg, "success");
                            payment_datagrid.datagrid("reload");
                        } else {
                            swal("操作失败！", data.msg, "error");
                        }
                    }, "json")
                } else {
                    swal("已取消", "您已取消操作!", "error")
                }
            })


            /*$.messager.confirm("温馨提示","你确定要删除吗?",function (r) {
                if (r){
                    $.get("/payment/remove.do", {id: row.id}, function (data) {
                        if (data.success) {
                            $.messager.alert('温馨提示', '删除成功!', 'info',function () {
                                payment_datagrid.datagrid("reload");
                            });
                        } else {
                            $.messager.alert('温馨提示', data.msg, "error");
                        }
                    })
                }
            })*/


        },
        reload: function () {
            $("#audit").linkbutton("enable");
            payment_datagrid.datagrid("load")
        },
        cancel: function () {
            payment_dialog.dialog("close");
        },
        saveOrUpdate: function () {
            payment_form.form("submit", {
                url: "/payment/saveOrUpdate.do",
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        //$.messager.alert('温馨提示', '保存成功', "info");
                        swal("保存成功", "", "success")
                        payment_dialog.dialog("close");
                        payment_datagrid.datagrid("reload");
                    } else {
                        //$.messager.alert('温馨提示', data.msg, "error");
                        swal("保存失败!", "请检查输入数据是否有误!", "error")
                    }
                }
            });
        },
        searchList: function () {
            var classId = $("#classId").combobox("getValue");
            var paymodeId = $("#paymodeId").combobox("getValue");
            var payBeginTime = $("#payBeginTime").datebox("getValue");
            var payEndTime = $("#payEndTime").datebox("getValue");
            //高级查询要让整个表单提交
            payment_datagrid.datagrid("load", {
                classId: classId,
                paymodeId: paymodeId,
                payBeginTime: payBeginTime,
                payEndTime: payEndTime
            });
        },
        audit: function () {
            var row = payment_datagrid.datagrid("getSelected");
            if (row == null) {
                //$.messager.alert("温馨提示","请选择一条支出数据!","warning");

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
                    $.get('/payment/audit.do', {id: row.id}, function (data) {
                        if (data.success) {
                            swal("操作成功！", "", "success");
                            payment_datagrid.datagrid("reload");
                        }
                    }, "json")
                } else {
                    swal("已取消", "您已取消操作!", "error")
                }
            })

            /*$.messager.confirm("温馨提示","您确定要执行此操作吗?",function (r) {
                if (r){
                    $.get('/payment/audit.do',{id:row.id},function (data) {
                        if (data.success){
                            $.messager.alert("温馨提示","审核成功!","info",function () {

                                payment_datagrid.datagrid("reload");
                            });
                        }else {
                            swal("操作成功！", "如误操作,请重复刚才步骤!", "success");
                        }
                    })
                }
            })*/
        }
    }
    $("[data-cmd]").click(function () {
        var method = $(this).data("cmd");
        objMethod[method]();
    });
    payment_datagrid.datagrid({
        fit: true,
        url: "/payment/query.do",
        pagination: true,
        striped: true,
        fitColumns: true,
        rownumbers: true,
        singleSelect: true,
        shadow: true,
        toolbar: '#toolbar',
        columns: [[
            {field: 'date', title: '日期', width: 100, sortable: true},
            {field: 'cost', title: '支付金额', width: 100},
            {field: 'digest', title: '摘要', width: 100},
            {
                field: 'payer', title: '支出人', width: 100, formatter: function (value, row, index) {
                    return value ? value.realname : '';
                }
            },
            {
                field: 'brokerage', title: '经手人', width: 100, formatter: function (value, row, index) {
                    return value ? value.realname : '';
                }
            },
            {
                field: 'paymode', title: '支付方式', width: 100, formatter: function (value, row, index) {
                    return value ? value.name : '';
                }
            },
            {field: 'costtype', title: '花费类型', width: 100},
            {field: 'docnumber', title: '单据号', width: 100},
            {
                field: 'classGrade', title: '所属班级', width: 100, formatter: function (value, row, index) {
                    return value ? value.className : '';
                }
            },
            {
                field: 'auditor', title: '审核人', width: 100, formatter: function (value, row, index) {
                    return value ? value.realname : '';
                }
            },
            {
                field: 'state', title: '状态', width: 100, formatter: function (value, row, index) {
                    return value ? '已审核' : '<font color="red">未审核</font>';
                }
            },
        ]],
        onClickRow: function (index, row) {
            if (row.state) {
                $("#audit").linkbutton("disable");
            } else {
                $("#audit").linkbutton("enable");
            }
        }
    });
    payment_dialog.dialog({
        //初始化弹出框
        title: '添加员工',
        width: 240,
        height: 370,
        closed: true,
        modal: true,
        buttons: "#payment_buttons"
    });
});
