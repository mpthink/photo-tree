package com.photoTree.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.photoTree.base.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;

/**
 * @Description FcNftLike
 * @author: mpthink
 * @Date 2022-6-5
 * @Version 2.1
 */
@TableName("`fc_nft_like`")
public class FcNftLike extends BaseEntity {


    /**
     * nft id
     */
    @TableField("`nft_id`")
    private Long nftId;

    /**
     * 用户地址
     */
    @TableField("`user_address`")
    private String userAddress;


    public Long getNftId() {
        return nftId;
    }

    public void setNftId(Long nftId) {
        this.nftId = nftId;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public static final String NFT_ID = "`nft_id`";

    public static final String USER_ADDRESS = "`user_address`";

    @Override
    public String toString() {
        return "FcNftLike{" +
        "nftId=" + nftId +
        ", userAddress=" + userAddress +
        "}";
    }
}
