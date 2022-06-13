package com.photoTree.controller;

import com.photoTree.base.controller.BaseController;
import com.photoTree.utils.ResponseUtil;
import com.photoTree.vo.FcOrderLogVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.photoTree.annotation.RequiresPermissionsDesc;
import com.photoTree.service.FcOrderService;

@RestController
@RequestMapping("/admin/order")
public class AdminOrderController  extends BaseController {

    @Autowired
    private FcOrderService orderService;

    @RequiresPermissions("admin:order:list")
    @RequiresPermissionsDesc(menu = {"交易管理", "交易信息"}, button = "查询")
    @PostMapping("/list")
    public Object list(String address,
                       Long staTime,
                       String sort,
                       String order) {
        IPage<FcOrderLogVo> ipage = orderService.querySelective(address, staTime, this.getPageInfo(), this.isAsc(order), sort);
        return ResponseUtil.okList(ipage);
    }
}
