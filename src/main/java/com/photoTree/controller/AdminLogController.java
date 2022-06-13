package com.photoTree.controller;

import com.photoTree.base.controller.BaseController;
import com.photoTree.domain.FcAdminLog;
import com.photoTree.utils.ResponseUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.photoTree.annotation.RequiresPermissionsDesc;
import com.photoTree.service.FcLogService;

@RestController
@RequestMapping("/admin/log")
public class AdminLogController  extends BaseController {

    @Autowired
    private FcLogService logService;

    @RequiresPermissions("admin:log:list")
    @RequiresPermissionsDesc(menu = {"系统管理", "操作日志"}, button = "查询")
    @PostMapping("/list")
    public Object list(String name,
                       String sort,
                       String order) {
        IPage<FcAdminLog> iPage = logService.querySelective(name, this.getPageInfo(), this.isAsc(order), sort);
        return ResponseUtil.okList(iPage);
    }



}
