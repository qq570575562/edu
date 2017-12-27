$(function () {
    var systemDictionary_datagrid = $("#systemDictionary_datagrid");
    var systemDictionary_dialog = $("#systemDictionary_dialog");
    var systemDictionary_form = $("#systemDictionary_form");
    var systemDictionaryItem_datagrid = $("#systemDictionaryItem_datagrid");
    var systemDictionaryItem_dialog = $("#systemDictionaryItem_dialog");
    var systemDictionaryItem_form = $("#systemDictionaryItem_form");


    var objMethod = {
        add: function () {
            systemDictionary_dialog.dialog("setTitle", "添加字典目录");
            systemDictionary_form.form("clear");
            systemDictionary_dialog.dialog("open").dialog("center");
        },
        edit: function () {
            var row = systemDictionary_datagrid.datagrid("getSelected");
            systemDictionary_form.form("clear");
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
            systemDictionary_dialog.dialog("setTitle", "编辑字典目录");
            systemDictionary_form.form("load", row);
            systemDictionary_dialog.dialog("open");
        },
        remove: function () {
            //获取选中行
            var row = systemDictionary_datagrid.datagrid("getSelected");
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

            //发送ajax请求
            $.get("/systemDictionary/delete.do", {id: row.id}, function (data) {

                if (data.flag) {
                    //$.messager.alert("温馨提示", "删除成功", "info", function () {
                    swal("删除成功!", "", "success")
                    systemDictionary_datagrid.datagrid("load");

                    //});
                } else {
                    //$.messager.alert("温馨提示", "删除失败", "error");
                    swal("删除失败!", "", "error")
                }
            });
        },
        reload: function () {
            systemDictionary_datagrid.datagrid("load")
        },
        cancel: function () {
            systemDictionary_dialog.dialog("close");
        },
        saveOrUpdate: function () {
            systemDictionary_form.form("submit", {
                url: "/systemDictionary/saveOrUpdate.do",
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        //$.messager.alert('温馨提示', '保存成功', "info");
                        swal("保存成功!", "", "success")
                        systemDictionary_dialog.dialog("close");
                        systemDictionary_datagrid.datagrid("reload");
                    } else {
                        //$.messager.alert('温馨提示', data.msg, "error");
                        swal("保存失败!", "", "error");
                    }
                }
            });
        },
        itemAdd: function () {
            systemDictionaryItem_dialog.dialog("setTitle", "添加字典目录明细");
            systemDictionaryItem_form.form("clear");
            systemDictionaryItem_dialog.dialog("open");
        },
        itemEdit: function () {
            var row = systemDictionaryItem_datagrid.datagrid("getSelected");
            systemDictionaryItem_form.form("clear");
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
            $.get("/systemDictionaryItem/selectRoleId.do", {id: row.id}, function (data) {
                $("#role_id").combobox("setValues", data);
            });
            systemDictionaryItem_dialog.dialog("setTitle", "编辑字典目录明细");
            row["systemDictionary.sn"] = row.systemDictionary.sn;

            systemDictionaryItem_form.form("load", row);
            systemDictionaryItem_dialog.dialog("open");
        },
        itemRemove: function () {
            //获取选中行
            var row = systemDictionaryItem_datagrid.datagrid("getSelected");
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

            //发送ajax请求
            $.get("/systemDictionaryItem/delete.do", {id: row.id}, function (data) {

                if (data.flag) {
                    //$.messager.alert("温馨提示", "删除成功", "info", function () {
                    swal("保存成功!", "", "success")
                    systemDictionaryItem_datagrid.datagrid("load");
                    //});
                } else {
                    //$.messager.alert("温馨提示", "删除失败", "error");
                    swal("保存失败!", "", "error")
                }
            });
        },

        itemRload: function () {
            systemDictionaryItem_datagrid.datagrid("load")
        },
        itemCancel: function () {
            systemDictionaryItem_dialog.dialog("close");
        },
        itemSaveOrUpdate: function () {
            systemDictionaryItem_form.form("submit", {
                url: "/systemDictionaryItem/saveOrUpdate.do",
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        //$.messager.alert('温馨提示', '保存成功', "info");

                        swal("保存成功", "", "success")
                        systemDictionaryItem_dialog.dialog("close");
                        systemDictionaryItem_datagrid.datagrid("reload");
                    } else {
                        //$.messager.alert('温馨提示', data.msg, "error");
                        swal("保存失败!", "", "error")
                    }
                }
            });
        }
    };
    $("[data-cmd]").click(function () {
        var method = $(this).data("cmd");
        objMethod[method]();
    });

    systemDictionary_dialog.dialog({
        //初始化弹出框
        title: '添加字典目录',
        width: 300,
        height: 200,
        closed: true,
        buttons: "#systemDictionary_buttons"
    }).dialog("center");
    systemDictionary_datagrid.datagrid({
        fit: true,
        url: "/systemDictionary/query.do",
        pagination: true,
        striped: true,
        fitColumns: true,
        rownumbers: true,
        singleSelect: true,
        shadow: true,
        toolbar: '#systemDictionary_toolbar',
        onSelect: function (index, row) {
            var sn = row.sn;
            systemDictionaryItem_datagrid.datagrid({url: "/systemDictionaryItem/queryBySn.do?sn=" + sn})
        },
        columns: [[
            {field: 'sn', title: '字典明细名称', width: 100},
            {field: 'name', title: '字典目录', width: 100},
            {field: 'intro', title: '字典明细简介', width: 100}
        ]],
    });
    systemDictionaryItem_datagrid.datagrid({
        fit: true,
        pagination: true,
        striped: true,
        fitColumns: true,
        rownumbers: true,
        singleSelect: true,
        shadow: true,
        toolbar: '#systemDictionaryItem_toolbar',
        columns: [[
            {field: 'name', title: '字典明细名称', width: 100},
            {
                field: 'systemDictionary', title: '字典目录', width: 100, formatter: function (value, row, index) {
                    return value ? value.name : "";
                }
            },
            {field: 'intro', title: '字典明细简介', width: 100}
        ]],

    });
    systemDictionaryItem_dialog.dialog({
        //初始化弹出框
        title: '添加字典明细',
        width: 300,
        height: 200,
        closed: true,
        buttons: "#systemDictionaryItem_buttons"
    });
});
