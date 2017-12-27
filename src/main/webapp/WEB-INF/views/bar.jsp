<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>销售报表柱状图</title>
<script type="text/javascript" src="/static/plugin/echarts/build/dist/echarts.js"></script>
 <script type="text/javascript">
        // 路径配置
        require.config({
            paths: {
                echarts: '/static/plugin/echarts/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/bar', // 使用柱状图就加载bar模块，按需加载
                'echarts/chart/line' // 使用折线图就加载line模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('formalStudentChartBar'));
                
                var option = {
                    title : {
                        text: '学员缴费情况柱状图',
                        x:'left'
                    },
                    tooltip : {
                        trigger: 'axis',
                        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        },
                        formatter: function (params){
                            return params[0].name + '<br/>'
                                + params[0].seriesName + ' : ' + params[0].value + '<br/>'
                                + params[1].seriesName + ' : ' + (params[1].value + params[0].value);
                        }
                    },
                    legend: {
                        selectedMode:true,
                        data:['学员已付总额', '学员未付总额'],
                        x:'center'
                    },
                    toolbox: {
                        show : true,
                        feature : {
                            mark : {show: true},
                            dataView : {show: true, readOnly: false},
                            restore : {show: true},
                            saveAsImage : {show: true}
                        }
                    },
                    calculable : true,
                    xAxis : [
                        {
                            type : 'category',
                            data : ${groupBy}
                        }
                    ],
                    yAxis : [
                        {
                            type : 'value',
                        }
                    ],
                    series : [
                        {
                            name:'学员需付总额',
                            type:'bar',
                            stack: 'sum',
                            barCategoryGap: '50%',
                            itemStyle: {
                                normal: {
                                    color: 'tomato',
                                    barBorderColor: 'tomato',
                                    barBorderWidth: 10,
                                    barBorderRadius:0,
                                    label : {
                                        show: true, position: 'insideTop'
                                    }
                                }
                            },
                            data:${paidTuitionList}
                        },
                        {
                            name:'学员未付总额',
                            type:'bar',
                            stack: 'sum',
                            itemStyle: {
                                normal: {
                                    color: '#fff',
                                    barBorderColor: 'tomato',
                                    barBorderWidth: 6,
                                    barBorderRadius:0,
                                    label : {
                                        show: true,
                                        position: 'top',
//                                        formatter: function (params) {
//                                            for (var i = 0, l = option.xAxis[0].data.length; i < l; i++) {
//                                                if (option.xAxis[0].data[i] == params.name) {
//                                                    return option.series[0].data[i] + params.value;
//                                                }
//                                            }
//                                        },
                                        textStyle: {
                                            color: 'tomato'
                                        }
                                    }
                                }
                            },
                            data:${ownTuitionList}
                        }
                    ]
                };
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );
    </script>
</head>
<body>
	<div id="formalStudentChartBar" style="height:400px;width:643px;"></div>
</body>
</html>