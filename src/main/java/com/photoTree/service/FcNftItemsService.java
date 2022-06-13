package com.photoTree.service;

import java.util.List;

import com.photoTree.base.service.impl.BaseService;
import com.photoTree.domain.FcNftItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

@Service
public class FcNftItemsService {

	@Autowired
    private BaseService baseService;


    /**
     * 根据nft的id查找
     * @param nftId
     * @return
     */
    public List<FcNftItems> getByNftId(Long nftId){
        QueryWrapper<FcNftItems> wrapper = new QueryWrapper<>();
        wrapper.eq(FcNftItems.NFT_ID,nftId);
        wrapper.eq(FcNftItems.DELETED,false);
        return baseService.findByCondition(FcNftItems.class, wrapper);
    }



}
