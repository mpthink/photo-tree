package com.photoTree.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.photoTree.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * @Description StaUser
 * @author: mpthink
 * @Date 2022-6-5
 * @Version 2.1
 */
@TableName("`sta_user`")
public class StaUser extends BaseEntity {


    /**
     * 登录人数
     */
    @TableField("`login_amount`")
    private Integer loginAmount;

    /**
     * 统计时间
     */
    @TableField("`sta_time`")
    private Long staTime;


    public Integer getLoginAmount() {
        return loginAmount;
    }

    public void setLoginAmount(Integer loginAmount) {
        this.loginAmount = loginAmount;
    }

    public Long getStaTime() {
        return staTime;
    }

    public void setStaTime(Long staTime) {
        this.staTime = staTime;
    }

    public static final String LOGIN_AMOUNT = "`login_amount`";

    public static final String STA_TIME = "`sta_time`";

    @Override
    public String toString() {
        return "StaUser{" +
        "loginAmount=" + loginAmount +
        ", staTime=" + staTime +
        "}";
    }
}
