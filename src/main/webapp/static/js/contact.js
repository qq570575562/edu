$(function () {
    //1.变量抽取
    var contact_form = $("#contact_form");
    var contact_datagrid = $("#contact_datagrid");
    var contact_dialog = $("#contact_dialog");

    //2.把方法放进来(放到一个对象中统一管理方法)
    var methodObj = {
        add: function () {
            //清空表单
            contact_form.form("clear");

            //设置一些字段选中
            $("#contact_main").combobox("setValue", 1);
            $("#contact_gander").combobox("setValue", 1);

            //设置标题
            contact_dialog.dialog("setTitle", "新增学校联系人");
            //打开弹出框
            contact_dialog.dialog("open");
        },

        edit: function () {

            var row = contact_datagrid.datagrid("getSelected");
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
            contact_form.form("clear");
            //处理数据回显
            if (row["university"]) {
                row["university.id"] = row.university.id;
            }
            //回显数据
            contact_form.form("load", row);
            //设置标题
            contact_dialog.dialog("setTitle", "编辑学校联系人");
            //打开弹出框
            contact_dialog.dialog("open");
        },

        cancel: function () {
            //关闭弹出框
            contact_dialog.dialog("close");
        },

        save: function () {
            contact_form.form("submit", {
                url: '/contact/saveOrUpdate.do',
                success: function (data) {
                    data = $.parseJSON(data);
                    if (data.success) {
                        //提示信息
                        //$.messager.alert('温馨提示', '操作成功', 'info', function () {

                            swal("保存成功！", "", "success");
                            //关闭弹出框
                            contact_dialog.dialog("close");
                            //重新加载数据表格
                            contact_datagrid.datagrid("reload");
                        //});
                    } else {
                        $.messager.alert('温馨提示', data.msg, 'error');
                    }
                }

            })
        },
        load: function () {
            contact_datagrid.datagrid("load");
        },

       searchForm: function () {
            //获取关键字input
            var keyword = $("#keyword").textbox("getValue");
            var universityId = $("#universityId").textbox("getValue");

            //让数据表格重新加载,并且带上查询的参数(带参数)
            contact_datagrid.datagrid("load", {
                keyword: keyword,
                universityId: universityId
            });
        },
        exportXls: function () {
            //xls导出
            window.location.href = "/contact/exportXls.do";
        },
    };

//3.页面加载完后统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该按钮要执行的方法(cmd)
        var method = $(this).data("cmd");
        //调用方法
        methodObj[method]();
    })

    contact_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        toolbar: '#contact_toolbar',
        striped: true,
        url: '/contact/query.do',
        pagination: true,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {field: 'id', title: "编号", width: 100},
            {field: 'name', title: '姓名', width: 100},
            {field: 'duty', title: '职务', width: 100},
            {field: 'facukty', title: '院系', width: 100},
            {field: 'dept', title: '部门', width: 100},
            {field: 'tel', title: '电话', width: 100},
            {field: 'qq', title: 'QQ', width: 100},
            {field: 'email', title: '邮箱', width: 100},
            {
                field: 'gander', title: '性别', width: 100, formatter: function (value, row, index) {
                    return value ? "男" : "女";
                }
            },
            {field: 'birthday', title: '生日', width: 100},
            {
                field: 'main', title: '主要联系人', width: 100, formatter: function (value, row, index) {
                    return value ? "是" : "否";
                }
            },
            {
                field: 'university', title: '大学', width: 100, formatter: function (value, row, index) {
                    return row.university ? value.name : "";
                }
            },
            {field: 'intro', title: '简介', width: 100},

        ]]
    })

    contact_dialog.dialog({
        width: '270',
        height: '470',
        modal:true,
        buttons: '#contact_buttons',
        closed: true
    })
})

