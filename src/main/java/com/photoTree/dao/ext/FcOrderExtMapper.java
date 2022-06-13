package com.photoTree.dao.ext;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.photoTree.domain.FcOrder;
import com.photoTree.domain.FcOrderLog;
import com.photoTree.dto.FcOrderDto;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FcOrderExtMapper {
    
	public List<FcOrderDto> listVo(FcOrder fcOrder);

    @SuppressWarnings("rawtypes")
    Integer countUser(@Param(Constants.WRAPPER) Wrapper ew);
    
    @SuppressWarnings("rawtypes")
    List<FcOrderLog> getAccumulatedMoney(@Param(Constants.WRAPPER) Wrapper ew);

    @SuppressWarnings("rawtypes")
    IPage<FcOrderLog> findByPage(IPage<FcOrderLog> page, @Param(Constants.WRAPPER) Wrapper ew);

    @SuppressWarnings("rawtypes")
    List<String> getAll(@Param(Constants.WRAPPER) Wrapper ew);


}
