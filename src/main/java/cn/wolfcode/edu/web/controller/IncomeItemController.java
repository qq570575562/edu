package cn.wolfcode.edu.web.controller;

import cn.wolfcode.edu.annotation.PerminssionName;
import cn.wolfcode.edu.domain.IncomeItem;
import cn.wolfcode.edu.page.PageResult;
import cn.wolfcode.edu.query.IncomeItemQueryObject;
import cn.wolfcode.edu.service.IIncomeItemService;
import cn.wolfcode.edu.util.JsonResult;
import com.alibaba.druid.util.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("incomeItem")
public class IncomeItemController {
    @Autowired
    private IIncomeItemService incomeItemService;



    @RequestMapping("query")
    @ResponseBody
    public PageResult list(IncomeItemQueryObject qo) throws Exception {
        PageResult pageResult = incomeItemService.query(qo);
        return pageResult;
    }

    @RequestMapping("selectAll")
    @ResponseBody
    public List<IncomeItem> selectAll() {
        return  incomeItemService.selectAll();
    }

    @RequiresPermissions("incomeItem:view")
    @PerminssionName("收款管理菜单")
    @RequestMapping("view")
    public String view() {
        return "incomeItem";
    }

    @RequiresPermissions("incomeItem:saveOrUpdate")
    @PerminssionName("收款管理新增编辑")
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public JsonResult saveOrUpdate(IncomeItem incomeItem) throws Exception{

        try {
            if (incomeItem.getId() == null) {

                incomeItemService.insert(incomeItem);

            } else {
                incomeItemService.updateByPrimaryKey(incomeItem);

            }
        } catch (Exception e) {

            e.printStackTrace();
            if(!StringUtils.isEmpty(e.getMessage())){
                return new JsonResult(false, e.getMessage());
            }
            return new JsonResult(false, "保存失败！");
        }
        return new JsonResult(true, "成功");
    }

    @RequestMapping("changeState")
    @ResponseBody
    public JsonResult changeState(Long id) throws Exception {

        try {
            incomeItemService.changeState(id);
        } catch (Exception e) {
            return new JsonResult(false, "失败");
        }
        return new JsonResult(true, "成功");
    }

}
