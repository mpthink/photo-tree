package com.photoTree.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.photoTree.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * @Description FcAdminPermission
 * @author: mpthink
 * @Date 2022-6-5
 * @Version 2.1
 */
@TableName("`fc_admin_permission`")
public class FcAdminPermission extends BaseEntity {


    /**
     * 角色ID
     */
    @TableField("`role_id`")
    private Long roleId;

    /**
     * 权限
     */
    @TableField("`permission`")
    private String permission;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public static final String ROLE_ID = "`role_id`";

    public static final String PERMISSION = "`permission`";

    @Override
    public String toString() {
        return "FcAdminPermission{" +
        "roleId=" + roleId +
        ", permission=" + permission +
        "}";
    }
}
