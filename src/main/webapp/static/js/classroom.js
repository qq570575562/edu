$(function () {
    //1.变量抽取
    var classroom_form = $("#classroom_form");
    var classroom_datagrid = $("#classroom_datagrid");
    var classroom_dialog = $("#classroom_dialog");

    //2.把方法放进来(放到一个对象中统一管理方法)
    var methodObj = {
        add: function () {

            //清空表单
            classroom_form.form("clear");
            //设置标题
            classroom_dialog.dialog("setTitle", "新增班级");
            //打开弹出框
            classroom_dialog.dialog("open");
        },

        edit: function () {

            var row = classroom_datagrid.datagrid("getSelected");
            if (!row) {
                //$.messager.alert('温馨提示', "请选择一条数据!", 'warning');
                swal("请选择一条数据!", "", "warning")

                return;
            }

            //清空表单
            classroom_form.form("clear");

            //回显数据
            classroom_form.form("load", row);

            //设置标题
            classroom_dialog.dialog("setTitle", "编辑班级");
            //打开弹出框
            classroom_dialog.dialog("open");
        },

        cancel: function () {
            //关闭弹出框
            classroom_dialog.dialog("close");
        },

        saveOrUpdate: function () {
            classroom_form.form("submit", {
                url: '/classroom/saveOrUpdate.do',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        //提示信息
                        //$.messager.alert('温馨提示', '操作成功', 'info', function () {
                             swal("保存成功!", "", "success")
                            //关闭弹出框
                            classroom_dialog.dialog("close");
                            //重新加载数据表格
                            classroom_datagrid.datagrid("reload");
                        //});
                    } else {
                        //$.messager.alert('温馨提示', data.msg, 'error');
                        swal("保存失败", "请检查输入数据是否有误！", "error")
                    }
                }
            })
        },
        changeState: function () {
            var row = classroom_datagrid.datagrid("getSelected");
            if (!row) {
                //$.messager.alert('温馨提示', "请选择一条数据!", 'warning');
                swal("请选择一条数据!", "", "warning")
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
                    $.get("/classroom/changeState.do", {id: row.id}, function (data) {
                        if (data.success) {
                            swal("操作成功！", "如误操作,请重复刚才步骤!", "success");
                            classroom_datagrid.datagrid("reload");
                        }
                    }, "json")
                } else {
                    swal("已取消", "您已取消操作！", "error")
                }
            })


            /*$.messager.confirm('确认', '您确认想要执行该操作吗？', function (r) {
                if (r) {
                    //发送请求修改状态
                    $.get("/classroom/changeState.do", {id: row.id}, function (data) {
                        if (data.success) {
                            $.messager.alert('温馨提示', "操作成功!", 'info', function () {
                                //重新加载数据表格
                                classroom_datagrid.datagrid("reload");
                            });
                        } else {
                            $.messager.alert('温馨提示', data.msg, 'error');
                        }
                    }, "json")
                }
            });*/
        },

        load: function () {
            classroom_datagrid.datagrid("load");
        },
        show: function () {
            var row = classroom_datagrid.datagrid("getSelected");
            if (!row) {
                //$.messager.alert('温馨提示', "请选择一条数据!", 'warning');
                swal("请选择一条数据!", "", "warning")

                return;
            }
            $("#show_datagrid").datagrid("load",{
                id:row.id
            })
            $("#show_dialog").dialog("open");

        },

          searchForm: function () {
              //获取关键字input
              var keyword = $("#keyword").textbox("getValue");

              //让数据表格重新加载,并且带上查询的参数(会帮你带上分页的参数到后台,直接把后台返回的数据封装到datagrid中)
              classroom_datagrid.datagrid("load", {
                  keyword: keyword
              });
          }
    }
    $("#search").searchbox({
        searcher:function () {
            var keyword = $("#search").searchbox('getValue');
            classroom_datagrid.datagrid("load",{
                "keyword":keyword
            })
        }
    })

    //3.页面加载完后统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该按钮要执行的方法(cmd)
        var method = $(this).data("cmd");
        //调用方法
        methodObj[method]();
    })
    $("#show_dialog").dialog({
        title:"此教室所在班级信息",
        width: 600,
        height: 90,
        closed: true
    })

    $("#show_datagrid").datagrid({
        fit: true,
        url: "/classGrade/queryByClassroomId.do",
        fitColumns: true,
        rownumbers: true,
        singleSelect: true,
        shadow: true,
        scrollbarSize:0,
        toolbar: '#toolbar',
        columns: [[
            {field: 'className', title: '班级名', width: 100},
            {field: 'studentCount', title: '学生数量', width: 100},
            {field: 'state', title: '班级状态', width: 100,formatter: function (value, row, index) {
                return value ? "已开班" : '<font color="red">未开班</font>';
            }},
            {
                field: 'systemDictionaryItem',
                title: '所属学院',
                width: 100,
                formatter: function (value, row, index) {
                    return value ? value.name : '';
                }
            },
            {
                field: 'classroom', title: '所在教室', width: 100, formatter: function (value, row, index) {
                return value ? value.name : '';
            }
            },
            {
                field: 'leader', title: '班主任', width: 100, formatter: function (value, row, index) {
                return value ? value.realname : '<font color=\'red\'>尚未分配班主任</font>'
            }
            }
        ]],
    })

    classroom_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        toolbar: '#classroom_toolbar',
        striped: true,
        url: '/classroom/query.do',
        //pagination: true,不显示分页数据
        rownumbers: true,
        pagination:true,
        singleSelect: true,
        columns: [[
            {field: 'name', title: "教室名称", width: 100},
            {field: 'address', title: '教室地址', width: 100},
            {
                field: 'seatCount', title: '座位数', width: 100
            },
            {
                field: 'state', title: '状态', width: 100, formatter: function (value, row, index) {
                return value ? "启用中": "<font color='red'>停用中</font>";
            }
            },
            {
                field: 'slogan', title: '教室标语', width: 100
            }
        ]],
        onClickRow: function (index, row) {
            if (row.state) {
                //修改按钮
                $('#changState_btn').linkbutton({
                    text: '停用'
                })
                $("#show").linkbutton("enable");

            } else {
                //修改按钮
                $('#changState_btn').linkbutton({
                    text: '启用'
                })
                $("#show").linkbutton("disable");
            }
        }
    })

    classroom_dialog.dialog({
        width: 280,
        height: 220,
        modal:true,
        buttons: '#classroom_buttons',
        modal:true,
        closed: true
    })
})

