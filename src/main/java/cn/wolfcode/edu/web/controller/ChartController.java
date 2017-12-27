package cn.wolfcode.edu.web.controller;
import cn.wolfcode.edu.domain.FormalStudentChart;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.FormalStudentChartQueryObject;
import cn.wolfcode.edu.service.impl.ChartServiceImpl;
import com.alibaba.druid.util.StringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.*;

@Controller
@RequestMapping("chart")
public class ChartController {
    @Autowired
    private ChartServiceImpl chartService;
    private Date beginTime;
    private Date endTime;
    private String keyword;
    private String groupByName;
    @RequestMapping("query")
    @ResponseBody
    public PageResult query(HttpServletRequest request,FormalStudentChartQueryObject qo) throws Exception{
        String groupByName = qo.getGroupByName();
        Date beginTime = qo.getBeginTime();
        Date endTime = qo.getEndTime();
        String keyword = qo.getKeyword();
        if (!StringUtils.isEmpty(groupByName)){
            this.groupByName = groupByName;
        }
        if (!StringUtils.isEmpty(keyword)){
            this.keyword = keyword;
        }
        if (beginTime!=null){
            this.beginTime = beginTime;
        }
        if (endTime != null){
            this.endTime = endTime;
        }
        PageResult pageResult = chartService.query(qo);
        List<FormalStudentChart> rows = (List<FormalStudentChart>) pageResult.getRows();
        for (Object row : rows) {
            System.out.println(row);
        }
        return pageResult;
    }
    @RequestMapping("view")
    public String view() {
        return "chart";
    }
    @RequestMapping("export")
    public void export(HttpServletResponse response) throws  Exception {
        FormalStudentChartQueryObject qo = new FormalStudentChartQueryObject();
        qo.setBeginTime(beginTime);
        qo.setEndTime(endTime);
        qo.setKeyword(keyword);
        qo.setGroupByName(groupByName);
        PageResult result = chartService.query(qo);
        List<FormalStudentChart> rows = (List<FormalStudentChart>) result.getRows();

        //得先设置文件下载响应头
        response.setHeader("Content-Disposition", "attachment;filename=formalStudentChart.xls");
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
        WritableSheet sheet = workbook.createSheet("formalStudentChart", 0);
        sheet.addCell(new Label(0, 0, "分组类型"));
        sheet.addCell(new Label(1, 0, "已付总费用"));
        sheet.addCell(new Label(2, 0, "未付总费用"));
        sheet.addCell(new Label(3, 0, "已付清人数"));
        for (int i = 0,j=1; i < rows.size(); j++,i++) {
            FormalStudentChart formalStudentChart = rows.get(i);
            Label groupBy = new Label(0, j, formalStudentChart.getGroupBy());
            System.out.println(groupBy);
            Label paidTuition = new Label(1, j, formalStudentChart.getPaidTuition().toString());
            Label ownTuition = new Label(2, j, formalStudentChart.getOwnTuition().toString());
            Label paidNumber = new Label(3, j, formalStudentChart.getPaidNumber().toString());
            sheet.addCell(groupBy);
            sheet.addCell(paidTuition);
            sheet.addCell(ownTuition);
            sheet.addCell(paidNumber);
        }
        workbook.write();
        workbook.close();
    }
    @RequestMapping("pieChart")
    public String pieChart(HttpServletRequest request,Model model, @ModelAttribute("qo")FormalStudentChartQueryObject qo) throws Exception{
        String keyword = qo.getKeyword();
        if ("".equals(keyword)){
            keyword = null;
        }
        if (keyword != null){
            keyword = new String(keyword.getBytes("ISO-8859-1"),"UTF-8");
            qo.setKeyword(keyword);
        }
        qo.setKeyword(keyword);
        PageResult result = chartService.query(qo);
        System.out.println(qo.getGroupByName());
        List<FormalStudentChart> rows = (List<FormalStudentChart>) result.getRows();
        for (FormalStudentChart row : rows) {
            System.out.println(row);
        }
        ArrayList<String> groupBy = new ArrayList<>();
        List<Map<String, Object>> paidTuitionList = new ArrayList<>();
        List<Map<String, Object>> ownTuitionList = new ArrayList<>();
        BigDecimal paidTuitionMax = BigDecimal.ZERO;
        BigDecimal ownTuitionMax = BigDecimal.ZERO;
        for (FormalStudentChart row : rows) {
            Map<String, Object> paidTuitionMap = new HashMap<>();
            Map<String, Object> ownTuitionMap = new HashMap<>();
            groupBy.add(row.getGroupBy());
            paidTuitionMap.put("name", row.getGroupBy());
            paidTuitionMap.put("value", row.getPaidTuition());
            ownTuitionMap.put("name", row.getGroupBy());
            ownTuitionMap.put("value", row.getOwnTuition());
            paidTuitionList.add(paidTuitionMap);
            ownTuitionList.add(ownTuitionMap);
            System.out.println(row.getPaidTuition());
            BigDecimal paidTuition = new BigDecimal(row.getPaidTuition().toString());
            BigDecimal ownTuition = new BigDecimal(row.getOwnTuition().toString());
            if (paidTuition.compareTo(paidTuitionMax)>0) {
                paidTuitionMax = paidTuition;
            }
            if (ownTuition.compareTo(ownTuitionMax)>0) {
                ownTuitionMax = ownTuition;
            }
        }
        model.addAttribute("groupBy", new ObjectMapper().writeValueAsString(groupBy));
        model.addAttribute("paidTuitionList", new ObjectMapper().writeValueAsString(paidTuitionList));
        model.addAttribute("ownTuitionList", new ObjectMapper().writeValueAsString(ownTuitionList));
        model.addAttribute("paidTuitionMax", paidTuitionMax);
        model.addAttribute("ownTuitionMax", ownTuitionMax);
        return "pie";
    }
    @RequestMapping("barChart")
    public String barChart(Model model, @ModelAttribute("qo")FormalStudentChartQueryObject qo) throws Exception{
        if (keyword != null){
            String keyword = qo.getKeyword();
            keyword = new String(keyword.getBytes("ISO-8859-1"),"UTF-8");
            qo.setKeyword(keyword);
        }
        PageResult result = chartService.query(qo);
        System.out.println(qo.getGroupByName());
        System.out.println(qo.getBeginTime());
        System.out.println(qo.getEndTime());
        System.out.println(qo.getKeyword());
        List<FormalStudentChart> rows = (List<FormalStudentChart>) result.getRows();
        for (FormalStudentChart row : rows) {
            System.out.println(row);
        }
        ArrayList<String> groupBy = new ArrayList<>();
        List<Map<String, Object>> paidTuitionList = new ArrayList<>();
        List<Map<String, Object>> ownTuitionList = new ArrayList<>();
        for (FormalStudentChart row : rows) {
            Map<String, Object> paidTuitionMap = new HashMap<>();
            Map<String, Object> ownTuitionMap = new HashMap<>();
            groupBy.add(row.getGroupBy());
            paidTuitionMap.put("name", row.getGroupBy());
            paidTuitionMap.put("value", row.getPaidTuition());
            ownTuitionMap.put("name", row.getGroupBy());
            ownTuitionMap.put("value", row.getOwnTuition());
            paidTuitionList.add(paidTuitionMap);
            ownTuitionList.add(ownTuitionMap);
            BigDecimal paidTuition = new BigDecimal(row.getPaidTuition().toString());
            BigDecimal ownTuition = new BigDecimal(row.getOwnTuition().toString());
        }
        model.addAttribute("groupBy", new ObjectMapper().writeValueAsString(groupBy));
        model.addAttribute("paidTuitionList", new ObjectMapper().writeValueAsString(paidTuitionList));
        model.addAttribute("ownTuitionList", new ObjectMapper().writeValueAsString(ownTuitionList));
        return "bar";
    }
}
