package com.ytu.shop.web.controller;

import com.ytu.shop.common.dto.PageInfo;
import com.ytu.shop.domain.TbEvaluate;
import com.ytu.shop.service.EvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("shop/evaluate")
public class EvaluateController extends BaseController<TbEvaluate,EvaluateService> {

    @Autowired
    private EvaluateService evaluateService;

    @GetMapping(value = "list")
    public String list() {
        return "shop/evaluate/list";
    }

    @ResponseBody
    @GetMapping(value = "getPage")
    public PageInfo getPage(HttpServletRequest request) {
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");
        String shopId = request.getParameter("shopId");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 10 : Integer.parseInt(strLength);
        shopId= shopId == null ? "" : shopId;

        PageInfo pageInfo = evaluateService.getPage(shopId,start, length);
        int count = evaluateService.getCount(shopId);
        try {
            pageInfo.setDraw(draw);
            pageInfo.setRecordsTotal(count);
            pageInfo.setRecordsFiltered(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }

}
