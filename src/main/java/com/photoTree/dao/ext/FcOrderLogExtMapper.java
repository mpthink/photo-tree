package com.photoTree.dao.ext;

import com.photoTree.domain.FcOrderLog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface FcOrderLogExtMapper {

	@Update(value="update fc_order_log set expired = true where order_id = #{orderId}")
	void updateByOrderId(@Param("orderId")Long orderId);
	
	
	FcOrderLog getOne(@Param("address")String address, @Param("type")Integer type);
	
	int addOrderLog(@Param("log")FcOrderLog log);
}
