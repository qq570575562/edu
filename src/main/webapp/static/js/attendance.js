$(function () {

    var attendance_datagrid = $('#attendance_datagrid');//数据表格
    var searchForm = $('#searchForm');//高级查询提交
    var attendance_dialog = $("#attendance_dialog");//新增/编辑对话框
    var attendance_form = $("#attendance_form")//对话框表单
    /**
     * 所有按钮方法抽取
     * @type {{}}
     */
    var methods = {
        //签到按钮
        /*signIn: function () {
            $.messager.confirm('签到确认', '是否要签到?', function (r) {
                if (r) {
                    $.get('/attendance/signIn.do', function (data) {
                        if (data.success) {
                            $.messager.alert('温馨提示', '签到成功', 'info', function () {
                                attendance_datagrid.datagrid('reload');
                            });
                        } else {
                            $.messager.alert('温馨提示', data.msg, 'error');
                            attendance_datagrid.datagrid('reload');
                        }
                    });
                }
            });
        },*/
        signIn: function () {

            //Sweet Alert弹窗效果

            //发送请求修改状态
            $.get("/attendance/signIn.do", function (data) {
                if (data.success) {
                    swal("签到成功！", "已完成签到!", "success");
                    attendance_datagrid.datagrid("load");
                } else {
                    swal("签到失败", data.msg, "error");
                }
            }, "json");
            //签退按钮
        },
        signOut:
            function () {

                //Sweet Alert弹窗效果
                swal({
                    title: "您确定要签退吗?",
                    text: "到下班时间才可以签退哟",
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
                                swal("签退成功！", "您已成功签退!", "success");
                                attendance_datagrid.datagrid("load");
                            } else {
                                swal("签退失败", data.msg, "error");
                            }
                        });
                    } else {
                        swal("已取消", "您已取消了签退！", "error");
                    }
                });

            },
        //高级查询提交
        searchForm: function () {
            var username = $("#username").textbox('getValue');
            var beginDate = $("#beginDate").datebox('getValue');
            var endDate = $("#endDate").datebox('getValue');
            attendance_datagrid.datagrid('load', {
                username: username,
                beginDate: beginDate,
                endDate: endDate
            });
        }
        ,
        //刷新按钮
        reload: function () {
            attendance_datagrid.datagrid('reload');
        }
        ,

        //对话框取消
        cancel: function () {
            attendance_dialog.dialog("close");
        }
        ,
        //对话框保存
        save: function () {
            swal({
                title: "您确定要执行补签操作吗?",
                text: "请检查员工和补签日期！",
                type: "warning",
                showCancelButton: true,
                confirmButtonColor: "#DD6B55",
                confirmButtonText: "确认",
                cancelButtonText: "取消",
                closeOnConfirm: false,
                closeOnCancel: false
            }, function (isConfirm) {
                if (isConfirm) {
                    attendance_form.form("submit", {
                        url: "/ attendance/resignIn.do",
                        success: function (data) {
                            data = $.parseJSON(data);
                            if (data.success) {
                                swal("保存成功!", "已完成补签", "success");
                                attendance_dialog.dialog("close");
                                attendance_datagrid.datagrid("reload");
                            } else {
                                swal("保存失败", data.msg, "error");
                                attendance_dialog.dialog("close");
                            }
                        }
                    })
                } else {
                    swal("已取消", "您已取消操作！", "error")
                }
            })

        },


        resignIn: function () {

            attendance_dialog.dialog("setTitle", "补签操作");
            attendance_form.form("clear");
            attendance_dialog.dialog("open");


        },
//导出功能
        exportXls: function () {
            //Sweet Alert弹窗效果
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
                    //xls导出
                    window.location.href = "/attendance/exportXls.do";
                    swal("导出成功!", "", "success")
                } else {
                    swal("已取消", "", "error")
                }
            })


        },
    }

    /**
     * 统一绑定按钮方法
     */
    $("[data-cmd]"
    ).click(function () {
        var method = $(this).data("cmd");
        methods[method]();
    });


    attendance_datagrid.datagrid({
        fitColumns: true,
        fit: true,
        striped: true,
        rownumbers: true,
        pageSize: 15,
        singleSelect: true,
        pagination: true,
        pageList: [15,20,25, 30, 40, 50],
        url: '/attendance/query.do',
        columns: [[
            {
                field: 'employee', title: '员工姓名', align: 'center', width: 100, formatter: function (value, row, index) {
                    return value ? value.username : '';
                }
            },
            {
                field: 'signInDay',
                title: '有效日期',
                align: 'center',
                width: 100,
                formatter: function (value, row, index) {
                    return value ? value : '';
                }
            },
            {
                field: 'signInIp', title: '签到IP', width: 100, align: 'center'
            },
            {
                field: 'signInTime', title: '签到时间', width: 100, align: 'center',
                formatter: function (value, row, index) {
                    //为空不操作
                    if (value == null) {
                        return;
                    }
                    //如果不在规定的时间内显示为红色
                    /*var date = new Date(value);
                    console.log(date)
                    var begin = 30 * 60 * 1000 + 8 * 60 * 60 * 1000;
                    var end = 30 * 60 * 1000 + 17 * 60 * 60 * 1000;
                    var time = date.getHours() * 60 * 60 * 1000 + date.getMinutes() * 60 * 1000 + date.getSeconds() * 1000;
                    console.log(time)
                    console.log(row.signInState)*/
                    if (row.signInState) {
                        return value;
                    } else {
                        return '<font color="red">' + value + '</font>';
                    }
                }
            },

            {
                field: 'signInState',
                title: '签到状态',
                width: 100,
                align: 'center',
                formatter: function (value, row, index) {
                    return value == null ? '' : value ? '正常' : '<font color="red">迟到</font>';
                }
            },
            {
                field: 'signOutTime',
                title: '签退时间',
                width: 100,
                align: 'center',
                formatter: function (value, row, index) {
                    //为空不操作
                    if (value == null) {
                        return;
                    }
                    //如果不在规定的时间内显示为红色
                    /*var date = new Date(value);
                    var begin = 30 * 60 * 1000 + 8 * 60 * 60 * 1000;
                    var end = 30 * 60 * 1000 + 17 * 60 * 60 * 1000;
                    var time = date.getHours() * 60 * 60 * 1000 + date.getMinutes() * 60 * 1000 + date.getSeconds() * 1000;*/
                    if (row.signOutState) {
                        return value
                    } else {
                        return '<font color="red">' + value + '</font>';
                    }
                }
            },

            {
                field: 'signOutState',
                title: '签退状态',
                width: 100,
                align: 'center',
                formatter: function (value, row, index) {
                    return value == null ? '' : value ? '正常' : '<font color="red">早退</font>';
                }
            },

            {field: 'resignInDate', title: '补签时间', width: 100, align: 'center'},
            {
                field: 'supEmployee',
                title: '补签人员',
                align: 'center',
                width: 100,
                formatter: function (value, row, index) {
                    return value ? value.username : '';
                }
            },
            {field: 'cause', title: '补签事由', width: 100, align: 'center'}
        ]],
        toolbar: '#attendance_toolbar'
    });


    attendance_dialog.dialog({
        width: 300,
        height: 250,
        modal: true,
        closed: true,
        buttons: '#dialog_btns'
    });

})
;