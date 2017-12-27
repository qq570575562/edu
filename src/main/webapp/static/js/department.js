$(function () {
    //1.变量抽取
    var dept_form = $("#dept_form");
    var dept_datagrid = $("#dept_datagrid");
    var dept_dialog = $("#dept_dialog");

    //2.把方法放进来(放到一个对象中统一管理方法)
    var methodObj = {
        add: function () {

            //清空表单
            dept_form.form("clear");
            //设置标题
            dept_dialog.dialog("setTitle", "新增部门");
            //打开弹出框
            dept_dialog.dialog("open");
        },

        edit: function () {

            var row = dept_datagrid.datagrid("getSelected");
            if (!row) {
                //$.messager.alert('温馨提示', "请选择一条数据!", 'warning');

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

            //清空表单
            dept_form.form("clear");

            //处理数据回显
            if (row["manager"]) {
                row["manager.id"] = row.manager.id;
            }

            if (row["parent"]) {
                row["parent.id"] = row.parent.id;
            }

            //回显数据
            dept_form.form("load", row);

            //设置标题
            dept_dialog.dialog("setTitle", "编辑部门");
            //打开弹出框
            dept_dialog.dialog("open");
        },

        cancel: function () {
            //关闭弹出框
            dept_dialog.dialog("close");
        },

        save: function () {
            dept_form.form("submit", {
                url: '/department/saveOrUpdate.do',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        //关闭弹出框
                        dept_dialog.dialog("close");
                        swal({title: "保存成功", text: "", type: "success"})
                        //重新加载数据表格
                        dept_datagrid.datagrid("reload");
                    } else {
                        swal("保存失败!", "", "error")
                    }

                    /*//关闭弹出框   原生弹出效果
                    dept_dialog.dialog("close");
                    //重新加载数据表格
                    dept_datagrid.datagrid("reload");

                    //提示信息
                    $.messager.alert('温馨提示', '操作成功', 'info', function () {
                        //关闭弹出框
                        dept_dialog.dialog("close");
                        //重新加载数据表格
                        dept_datagrid.datagrid("reload");
                    })
                } else {
                    $.messager.alert('温馨提示', data.msg, 'error');
                }*/

                }
            })
        },

        scrap: function () {

            var row = dept_datagrid.datagrid("getSelected");
            if (!row) {
                //$.messager.alert('温馨提示', "请选择一条数据!", 'warning');  默认原生的弹窗效果

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
                    $.get("/department/changeState.do", {id: row.id}, function (data) {
                        if (data.success) {
                            swal("操作成功！", "如误操作,请重复刚才步骤!", "success");
                            dept_datagrid.datagrid("reload");
                        }
                    }, "json")
                } else {
                    swal("已取消", "您已取消操作！", "error")
                }
            })


            /*这是默认原生的弹窗效果
            $.messager.confirm('确认', '您确认想要执行该操作吗？', function (r) {
                if (r) {
                    //发送请求修改状态
                    $.get("/department/changeState.do", {id: row.id}, function (data) {
                        if (data.success) {
                            $.messager.alert('温馨提示', "操作成功!", 'info', function () {
                                //重新加载数据表格
                                dept_datagrid.datagrid("reload");
                            });
                        } else {
                            $.messager.alert('温馨提示', data.msg, 'error');
                        }
                    }, "json")
                }
            });*/
        },

        load: function () {
            dept_datagrid.datagrid("load");
        },

        /*  searchForm: function () {
              //获取关键字input
              var keyword = $("#keyword").textbox("getValue");

              //让数据表格重新加载,并且带上查询的参数(会帮你带上分页的参数到后台,直接把后台返回的数据封装到datagrid中)
              dept_datagrid.datagrid("load", {
                  keyword: keyword
              });
          }*/
    }


    //3.页面加载完后统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该按钮要执行的方法(cmd)
        var method = $(this).data("cmd");
        //调用方法
        methodObj[method]();
    })


    dept_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        toolbar: '#dept_toolbar',
        striped: true,
        url: '/department/query.do',
        //pagination: true,不显示分页数据
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {field: 'sn', title: "编号", width: 100},
            {field: 'name', title: '部门名称', width: 100},
            {
                field: 'parent', title: '上级部门', width: 100, formatter: function (value, row, index) {
                    return row.parent ? value.name : "";
                }
            },

            {
                field: 'manager', title: '部门经理', width: 100, formatter: function (value, row, index) {
                    return row.manager ? value.realname : "";
                }
            },
            {
                field: 'state', title: '状态', width: 100, formatter: function (value, row, index) {
                    //数据是int数值,1是正常
                    return value ? "正常" : "<font color='red'>报废</font>";
                }
            }
        ]],
        onClickRow: function (index, row) {
            if (row.state) {
                //修改按钮
                $('#changState_btn').linkbutton({
                    text: '离职'
                })

            } else {
                //修改按钮
                $('#changState_btn').linkbutton({
                    text: '复职'
                })
            }
        }
    })

    dept_dialog.dialog({
        width: 300,
        height: 225,
        modal:true,
        buttons: '#dept_buttons',
        closed: true
    })
})

