package com.photoTree.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.photoTree.base.controller.BaseController;
import com.photoTree.domain.StaNft;
import com.photoTree.domain.StaNftDeal;
import com.photoTree.utils.ResponseUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.photoTree.annotation.RequiresPermissionsDesc;
import com.photoTree.service.FcOrderService;
import com.photoTree.service.FcUserService;
import com.photoTree.service.StaNftDealService;
import com.photoTree.service.StaNftService;
import com.photoTree.service.StaUserService;

/*
 * 后台统计
 *
 * */

@RestController
@RequestMapping("/admin/statistics")
public class AdminStatisticsController extends BaseController {

    @Autowired
    private FcOrderService orderService;
    @Autowired
    private StaNftService staNftService;
    @Autowired
    private StaNftDealService staNftDealService;
    @Autowired
    private StaUserService staUserService;
    @Autowired
    private FcUserService userService;

    @RequiresPermissions("admin:statistics:transaction")
    @RequiresPermissionsDesc(menu = {"交易管理", "交易统计"}, button = "统计")
    @PostMapping("/transaction")
    public Object transaction() {
        Map<String, Object> objectMap = orderService.statTransaction();
        return ResponseUtil.ok(objectMap);
    }

    @PostMapping("/list")
    public Object getIndex(Long staTime){
        if (null != staTime){
            staTime = staTime - staTime % 3600;
        }else {
            staTime = System.currentTimeMillis() / 1000 - System.currentTimeMillis() / 1000 % (24 * 60 * 60);
        }
        Map<String,Object> result = new HashMap<>();
        List<StaNft> staNftList = staNftService.getList(staTime);
        Map<String, Object> contrastNft = staNftService.contrastNft(staNftList, staTime);
        List<StaNftDeal> staNftDealList = staNftDealService.getList(staTime);
        Map<String,Object> contrastNftDeal= staNftDealService.contrastNftDeal(staNftDealList, staTime);
        Map<String, Integer> staUserList = staUserService.getList(staTime);
        Map<String, Object> newCreateList = userService.newCreateList(staTime);
        result.put("staNftList",staNftList);
        result.put("staNftDealList",staNftDealList);
        result.put("staUserList",staUserList);
        result.put("newCreateList",newCreateList);
        result.put("contrastNftDeal",contrastNftDeal);
        result.put("contrastNft",contrastNft);
        return ResponseUtil.ok(result);
    }
}
