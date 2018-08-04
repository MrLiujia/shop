package shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shop.model.ReceivingAddress;

public interface ReceivingAddressMapper {

	void address(ReceivingAddress receivingAddress);
	
	List<ReceivingAddress> findAll(Long shopuserId);

	ReceivingAddress findOne(@Param("shopuserId")Long shopuserId,
							@Param("receivingAddressId")Long receivingAddressId);

	void update(ReceivingAddress receivingAddress);
	
	

}
