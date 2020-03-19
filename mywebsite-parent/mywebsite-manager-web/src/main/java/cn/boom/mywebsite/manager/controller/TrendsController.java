package cn.boom.mywebsite.manager.controller;

import cn.boom.mywebsite.content.service.TrendsService;
import cn.boom.mywebsite.pojo.MyWebSiteResult;
import cn.boom.mywebsite.pojo.TbTrends;
import com.alibaba.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/trends")
public class TrendsController {

    @Reference
    private TrendsService trendsService;

    @RequestMapping("/search")
    @ResponseBody
    public MyWebSiteResult search(@RequestBody TbTrends tbTrends, int page, int rows) {
        return trendsService.search(tbTrends, page, rows);
    }


    @RequestMapping("/findOneById")
    @ResponseBody
    public MyWebSiteResult findOneById(Long id) {

        if (id == null) {
            return MyWebSiteResult.build(400, "参数不合法");
        }

        TbTrends tbTrends = trendsService.findOneById(id);

        if (tbTrends == null) {
            return MyWebSiteResult.build(400, "未查询到此动态");
        }

        return MyWebSiteResult.ok(tbTrends);
    }

    @RequestMapping("/add")
    @ResponseBody
    public MyWebSiteResult add(@RequestBody TbTrends tbTrends) {
        return trendsService.add(tbTrends);
    }


    @RequestMapping("/update")
    @ResponseBody
    public MyWebSiteResult update(@RequestBody TbTrends tbTrends) {
        return trendsService.update(tbTrends);
    }

    @RequestMapping("/deleteIds")
    @ResponseBody
    public MyWebSiteResult deleteIds(Long[] ids) {
        return trendsService.deleteIds(ids);
    }

    @RequestMapping("/updateStatusIds")
    @ResponseBody
    public MyWebSiteResult updateStatusIds(Long[] ids, String status) {
        return trendsService.updateStatusIds(ids,status);
    }
}
