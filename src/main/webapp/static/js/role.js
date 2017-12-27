$(function () {

    //获取表格中的对象
    var role_datagrid = $("#role_datagrid");
    var role_dialog = $("#role_dialog");
    var role_form = $("#role_form");
    var allPermission = $("#allPermission");
    var rolePermission = $("#rolePermission");

    var clone_data;


    role_dialog.dialog({
        width: 500,
        height: 450,
        collapsible: true,
        minimizable: true,
        maximizable: true,
        singleSelect: true,
        modal: true,
        closed: true,
        buttons: "#mybuttons",

    });

    //初始化数据表格
    role_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        url: '/role/selectAll.do',
        rownumbers: true,
        /*pagination: true,
        pageSize: 5,
        pageList: [3, 5, 7, 10, 15],*/
        singleSelect: true,
        columns: [[
            {field: 'name', title: '名称', width: 100},
            {field: 'sn', title: '编码', width: 100}
        ]],


        toolbar: "#mytoolbar"
    });
    //定义方法
    var methodObj = {
        add: function () {

            //清空已选框部分的数据
            rolePermission.datagrid("loadData", []);
            //全部权限重新加载数据
            allPermission.datagrid("loadData", clone_data);

            //清空表单
            role_form.form("clear");
            //设置标题
            role_dialog.dialog("setTitle", "角色新增");
            //弹窗
            role_dialog.dialog("open");
        },
        edit: function () {
            //获取选中行
            var row = role_datagrid.datagrid("getSelected");
            if (row == null) {
                //$.messager.confirm("温馨提示", "请选择一条数据", "waring");

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

            //清空已选框部分的数据
            rolePermission.datagrid("loadData", []);
            //全部权限重新加载数据
            allPermission.datagrid("loadData", clone_data);

            //回显已选权限数据
            rolePermission.datagrid("load", {roleId: row.id});
            // 清空
            role_form.form("clear");
            //设置值 回显表单
            role_form.form("load", row);

            //设置标题
            role_dialog.dialog("setTitle", "角色编辑");
            //弹窗
            role_dialog.dialog("open");

        },

        save: function () {
            //提交表单
            role_form.form('submit', {
                url: "/role/saveOrUpdate.do",
                onSubmit: function (param) {
                    var rows = rolePermission.datagrid("getRows");
                    for (var i = 0; i < rows.length; i++) {
                        param["permissions[" + i + "].id"] = rows[i].id;
                    }
                    return true;
                },
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        //关闭窗口
                        role_dialog.dialog("close");
                        //弹出提示
                        //$.messager.alert("温馨提示", "保存成功", "info", function () {
                        swal("保存成功！", "", "success");
                        role_datagrid.datagrid("reload");
                        //});
                    } else {
                        //$.messager.alert("温馨提示", data.msg, "error");
                        swal("保存失败", "请检查数据是否有误！", "error")
                    }
                }
            });
        },

        cancel: function () {
            role_dialog.dialog("close");
        },
        reload: function () {
            role_datagrid.datagrid("load");
        },


        remove: function () {

            //获取选中行
            var row = role_datagrid.datagrid("getSelected");
            if (row == null) {

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
                    $.get("/role/delete.do", {id: row.id}, function (data) {
                        if (data.success) {
                            swal("操作成功！", data.msg, "success");
                            role_datagrid.datagrid("load");
                        } else {
                            swal("操作失败！", data.msg, "error");
                        }
                    }, "json")
                } else {
                    swal("已取消", "您已取消操作！", "error")
                }
            })


            /* //发送ajax请求
             $.get("/role/delete.do", {id: row.id}, function (data) {

                 if (data.success) {
                     $.messager.alert("温馨提示", "删除成功", "info", function () {
                         role_datagrid.datagrid("load");
                     });
                 } else {
                     $.messager.alert("温馨提示", "删除失败", "error");
                 }
             });
 */

        },
        loadPermission: function () {
            $.messager.confirm("温馨提示", "权限加载可能需要一段时间,确定要加载吗", function (r) {
                if (r) {
                    $.get("/permission/loadPermisson.do", function (data) {

                        if (data.success) {
                            $.messager.alert("温馨提示", "加载成功", "info", function () {
                                allPermission.datagrid("load");
                            });
                        } else {
                            //$.messager.alert("温馨提示", "加载失败", "error");
                            swal("加载失败", "请联系管理员！", "error")
                        }
                    });
                }

            });

        },
        removePermission: function () {
            $.messager.confirm("温馨提示", "确定要清空已选权限吗", function (r) {
                if (r) {
                    var rows = rolePermission.datagrid("getRows");
                    for (var index = rows.length - 1; index >= 0; index--) {

                        //向全部权限中添加
                        allPermission.datagrid("appendRow", rows[index]);
                        //在已有权限中移除自己
                        rolePermission.datagrid("deleteRow", index);
                    }
                }

            });

        }
    };

    //绑定方法
    $("[data-cmd]").click(function () {

        //获取方法名
        var methodName = $(this).data("cmd");
        //执行方法
        methodObj[methodName]();
    });


    //初始化所有权限
    allPermission.datagrid({
        width: 210,
        height: 280,
        top: 50,
        fitColumns: true,
        title: "全部权限",
        url: '/permission/selectAll.do',
        rownumbers: true,
        singleSelect: true,
        rownumbers: true, //行号
        toolbar: "#loadPermission",
        columns: [[
            {field: 'name', title: '权限名称', width: 100, align: "center"},
        ]],

        //定义点击事件  点击之后添加到已选列表
        onClickRow: function (index, row) {
            //获取已有全部全部权限
            /*
           var rows = rolePermission.datagrid("getRows");
           for (var i = 0; i < rows.length; i++) {
               if (rows[i].id == row.id) {
                   rolePermission.datagrid("selectRow", i);
                   return;
               }
           }*/
            //向已有权限中添加
            rolePermission.datagrid("appendRow", row);
            //在全部权限中移除自己
            allPermission.datagrid("deleteRow", index);
        },
        onLoadSuccess: function (data) {
            clone_data = $.extend(true, {}, data);


        }
    });
    //初始化已有权限
    rolePermission.datagrid({
        width: 200,
        height: 280,
        title: "已有权限",
        fitColumns: true,
        url: '/permission/selectByRoleId.do',
        rownumbers: true,
        singleSelect: true,
        rownumbers: true, //行号
        toolbar: "#removePermission",
        columns: [[
            {field: 'name', title: '权限名称', width: 100, align: "center"},
        ]],
        //定义点击事件  点击之后添加到已选列表
        onClickRow: function (index, row) {
            //向全部权限添加
            allPermission.datagrid("appendRow", row);
            //在已有权限中移除自己
            rolePermission.datagrid("deleteRow", index);
        },
        onLoadSuccess: function (data) {

            //在加载完成之后 把和已有权限部分相同的部分移除
            //获取已有权限的id集合
            var rows = rolePermission.datagrid("getRows");
            var ids = $.map(rows, function (permission) {
                return permission.id;
            });

            //获取全部权限
            var allRows = allPermission.datagrid("getRows");
            for (var i = allRows.length - 1; i >= 0; i--) {

                if ($.inArray(allRows[i].id, ids) != -1) {
                    //移除自己
                    allPermission.datagrid("deleteRow", i);
                }
            }
        }
    });

});