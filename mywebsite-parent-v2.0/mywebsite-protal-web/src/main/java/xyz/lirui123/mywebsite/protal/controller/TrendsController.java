package xyz.lirui123.mywebsite.protal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.lirui123.mywebsite.pojo.TbTrends;
import xyz.lirui123.mywebsite.protal.service.TrendsService;
import xyz.lirui123.mywebsite.response.ResponseResult;

@Controller
@RequestMapping("/trends")
public class TrendsController {

    @Autowired
    private TrendsService trendsService;

    @RequestMapping("/search")
    @ResponseBody
    public ResponseResult search(@RequestBody TbTrends tbTrends, int page, int rows) {
        return trendsService.search(tbTrends, page, rows);
    }


    @RequestMapping("/findOneById")
    @ResponseBody
    public ResponseResult findOneById(Long id) {

        if (id == null) {
            return ResponseResult.build(400, "参数不合法");
        }

        TbTrends tbTrends = trendsService.findOneById(id);

        if (tbTrends == null) {
            return ResponseResult.build(400, "未查询到此动态");
        }

        return ResponseResult.ok(tbTrends);
    }

    @RequestMapping("/update")
    @ResponseBody
    public ResponseResult update(@RequestBody TbTrends tbTrends) {
        return trendsService.update(tbTrends);
    }
}
