<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
                'echarts/chart/pie', // 使用柱状图就加载bar模块，按需加载
                'echarts/chart/funnel' // 使用折线图就加载line模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('formalStudentChartPie'));
                var myChart2 = ec.init(document.getElementById('formalStudentChartPie2'));

                var option = {
                	    title : {
                	        text: '学员已缴费饼状图',
                	        x:'center'
                	    },
                	    tooltip : {
                	        trigger: 'item',
                	        formatter: "{a} <br/>{b} : {c} ({d}%)"
                	    },
                	    legend: {
                	        orient : 'vertical',
                	        x : 'left',
                	        data:${groupBy}
                	    },
                	    toolbox: {
                	        show : true,
                	        feature : {
                	            mark : {show: true},
                	            dataView : {show: true, readOnly: false},
                	            magicType : {
                	                show: true, 
                	                type: ['pie', 'funnel'],
                	                option: {
                	                    funnel: {
                	                        x: '25%',
                	                        width: '50%',
                	                        funnelAlign: 'left',
                	                        max:${paidTuitionMax}
                	                    }
                	                }
                	            },
                	            restore : {show: true},
                	            saveAsImage : {show: true}
                	        }
                	    },
                	    calculable : true,
                	    series : [

                            {
                                name:'已付总额',
                                type:'pie',
                                radius : '55%',
                                center: ['50%', '60%'],
                                data:${paidTuitionList}
                            }
                	    ]
                	};
                var option2 = {
                	    title : {
                	        text: '学员未缴费饼状图',
                	        x:'center'
                	    },
                	    tooltip : {
                	        trigger: 'item',
                	        formatter: "{a} <br/>{b} : {c} ({d}%)"
                	    },
                	    legend: {
                	        orient : 'vertical',
                	        x : 'left',
                	        data:${groupBy}
                	    },
                	    toolbox: {
                	        show : true,
                	        feature : {
                	            mark : {show: true},
                	            dataView : {show: true, readOnly: false},
                	            magicType : {
                	                show: true,
                	                type: ['pie', 'funnel'],
                	                option: {
                	                    funnel: {
                	                        x: '25%',
                	                        width: '50%',
                	                        funnelAlign: 'left',
                	                        max:${ownTuitionMax}
                	                    }
                	                }
                	            },
                	            restore : {show: true},
                	            saveAsImage : {show: true}
                	        }
                	    },
                	    calculable : true,
                	    series : [

                            {
                                name:'未付总额',
                                type:'pie',
                                radius : '55%',
                                center: ['50%', '60%'],
                                data:${ownTuitionList}
                            }
                	    ]
                	};
                // 为echarts对象加载数据
                myChart.setOption(option);
                myChart2.setOption(option2)
            }
        );
    </script>
</head>
<body>
	<div id="formalStudentChartPie" style="height:400px;width:600px;" ></div><br>
	<div id="formalStudentChartPie2" style="height:400px;width:600px;"></div>
</body>
</html>