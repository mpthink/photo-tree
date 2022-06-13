package com.photoTree.controller;

import com.photoTree.base.controller.BaseController;
import com.photoTree.domain.FcAdminUser;
import com.photoTree.domain.FcUser;
import com.photoTree.utils.AdminResponseCode;
import com.photoTree.utils.ResponseUtil;
import com.photoTree.vo.FcUserVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.photoTree.annotation.RequiresPermissionsDesc;
import com.photoTree.service.FcAdminUserService;
import com.photoTree.service.FcUserService;
import com.photoTree.service.LogHelper;

@RestController
@RequestMapping("/admin/user")
public class AdminFcUserController extends BaseController {

    @Autowired
    private FcAdminUserService adminService;

    @Autowired
    private FcUserService userService;

    @Autowired
    private LogHelper logHelper;

    @RequiresPermissions("admin:user:list")
    @RequiresPermissionsDesc(menu = {"用户管理", "用户列表"}, button = "查询")
    @PostMapping("/list")
    public Object list(String address,
                       Integer verify,
                       String name,
                       String sort,
                       String order) {
        IPage<FcUserVo> iPage = userService.querySelective(address, verify, name, this.getPageInfo(), this.isAsc(order), sort);
        return ResponseUtil.okList(iPage);
    }

    private Object validate(FcAdminUser admin) {
        String name = admin.getUsername();
        if (StringUtils.isEmpty(name)) {
            return ResponseUtil.badArgument();
        }
        return null;
    }


    @RequiresPermissions("admin:user:detail")
    @RequiresPermissionsDesc(menu = {"用户管理", "用户列表"}, button = "详情")
    @PostMapping("/detail")
    public Object detail(@NotNull Long id) {
        FcUser user = userService.findById(id);
        return ResponseUtil.ok(user);
    }

    @RequiresPermissions("admin:user:update")
    @RequiresPermissionsDesc(menu = {"用户管理", "用户列表"}, button = "编辑")
    @PostMapping("/update")
    public Object update(FcAdminUser admin) {
        Object error = validate(admin);
        if (error != null) {
            return error;
        }

        Long anotherAdminId = admin.getId();
        if (anotherAdminId == null) {
            return ResponseUtil.badArgument();
        }
        if (adminService.updateById(admin) == 0) {
            return ResponseUtil.updatedDataFailed();
        }

        logHelper.logAuthSucceed("编辑员工", admin.getUsername());
        return ResponseUtil.ok(admin);
    }

    @RequiresPermissions("admin:user:delete")
    @RequiresPermissionsDesc(menu = {"用户管理", "用户列表"}, button = "删除")
    @PostMapping("/delete")
    public Object delete(FcAdminUser admin) {
        Long anotherAdminId = admin.getId();
        if (anotherAdminId == null) {
            return ResponseUtil.badArgument();
        }

        // 员工不能删除自身账号
        Subject currentUser = SecurityUtils.getSubject();
        FcAdminUser currentAdmin = (FcAdminUser) currentUser.getPrincipal();
        if (currentAdmin.getId().equals(anotherAdminId)) {
            return ResponseUtil.fail(AdminResponseCode.ADMIN_DELETE_NOT_ALLOWED, "Employees cannot delete their own accounts");
        }

        adminService.deleteById(anotherAdminId);
        logHelper.logAuthSucceed("删除员工", admin.getUsername());
        return ResponseUtil.ok();
    }



    @RequiresPermissions("admin:user:verify")
    @RequiresPermissionsDesc(menu = {"用户管理", "用户列表"}, button = "认证")
    @PostMapping("/verify")
    public Object verify(@NotNull Long id, Integer userVerify) {
        FcUser user = userService.findById(id);
        if (user == null) {
            return ResponseUtil.badArgument();
        }
        boolean ok=userService.verifyContract(user,userVerify);
        return ResponseUtil.ok(ok);
    }


    @RequiresPermissions("admin:user:enable")
    @RequiresPermissionsDesc(menu = {"用户管理", "用户列表"}, button = "禁用")
    @PostMapping("/enable")
    public Object enable(@NotNull Long id) {
        FcUser user = userService.findById(id);
        if (user == null) {
            return ResponseUtil.badArgument();
        }
        boolean ok=userService.disableContract(user);
        return ResponseUtil.ok(ok);
    }


    @RequiresPermissions("admin:user:disable")
    @RequiresPermissionsDesc(menu = {"用户管理", "用户列表"}, button = "启用")
    @PostMapping("/disable")
    public Object disable(@NotNull Long id) {
        FcUser user = userService.findById(id);
        if (user == null) {
            return ResponseUtil.badArgument();
        }
        boolean ok=userService.enableContract(user);
        return ResponseUtil.ok(ok);
    }

    @RequiresPermissions("admin:user:stat")
    @RequiresPermissionsDesc(menu = {"用户管理","用户列表"},button = "统计")
    @PostMapping("/stat")
    public Object stat(){
        return ResponseUtil.ok(userService.statUser());
    }
}
