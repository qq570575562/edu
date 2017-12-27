$(function () {
    //1.变量抽取
    var chart_datagrid = $("#chart_datagrid");

    //2.把方法放进来(放到一个对象中统一管理方法)
    var methodObj = {

        load: function () {
            var keyword = $("#keyword").textbox("reset");
            var groupByName = $("#groupByName").combobox("reset");
            var beginTime = $("#beginTime").datebox("reset");
            var endTime = $("#endTime").datebox("reset");
            chart_datagrid.datagrid("load");
        },
        search:function () {
            var keyword = $("#keyword").textbox("getValue");
            var groupByName = $("#groupByName").combobox("getValue");
            console.log(groupByName)
            var beginTime = $("#beginTime").datebox("getValue");
            console.log(beginTime)
            var endTime = $("#endTime").datebox("getValue");
            chart_datagrid.datagrid("load",{
                keyword:keyword,
                groupByName:groupByName,
                beginTime:beginTime,
                endTime:endTime,
            });
        },
        export:function () {
            $.messager.confirm("温馨提示","您确定要导出吗?",function (r) {
                if(r){
                    window.location.href = "/chart/export.do";
                }
            })
        },
        pie:function () {
            var keyword = $("#keyword").textbox("getValue");
            var groupByName = $("#groupByName").combobox("getValue");
            console.log(groupByName)
            var beginTime = $("#beginTime").datebox("getValue");
            var endTime = $("#endTime").datebox("getValue");
            $.dialog.open("/chart/pieChart.do?keyword="+keyword+"&beginTime="+beginTime+"&endTime="+endTime+"&groupByName="+groupByName,{
                title:"正式学员缴费情况饼状图",
                width: 650,
                height: 450,
                background:"#000000",
            });

        },
        bar:function () {
            var keyword = $("#keyword").textbox("getValue");
            console.log(keyword)
            var groupByName = $("#groupByName").combobox("getValue");
            console.log(groupByName)
            var beginTime = $("#beginTime").datebox("getValue");
            var endTime = $("#endTime").datebox("getValue");
            $.dialog.open("/chart/barChart.do?keyword="+keyword+"&beginTime="+beginTime+"&endTime="+endTime+"&groupByName="+groupByName,{
                id:"ajaxList",
                title:"正式学员缴费情况柱状图",
                width: 650,
                height: 450,
                background:"#000000",
            })
        }

    }
    // $("#search").searchbox({
    //     searcher:function () {
    //         var keyword = $("#search").searchbox('getValue');
    //         var keyword = $("#search").searchbox('getValue');
    //         var keyword = $("#search").searchbox('getValue');
    //         var keyword = $("#search").searchbox('getValue');
    //         chart_datagrid.datagrid("load",{
    //             "keyword":keyword
    //         })
    //     }
    // })

    //3.页面加载完后统一绑定事件
    $("[data-cmd]").click(function () {
        //获取到该按钮要执行的方法(cmd)
        var method = $(this).data("cmd");
        //调用方法
        methodObj[method]();
    })
    chart_datagrid.datagrid({
        fit: true,
        fitColumns: true,
        toolbar: '#chart_toolbar',
        striped: true,
        url: '/chart/query.do',
        //pagination: true,不显示分页数据
        rownumbers: true,
        pagination:true,
        singleSelect: true,
        columns: [[
            {field: 'groupBy', title: "分组类型", width: 100},
            {field: 'paidTuition', title: '已付总费用', width: 100},
            {
                field: 'ownTuition', title: '未付总费用', width: 100
            },
            {
                field: 'paidNumber', title: '已付清人数', width: 100
            }
        ]],
    })
})

