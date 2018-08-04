package shop.service;

import java.util.List;

import javax.validation.Valid;

import shop.model.ReceivingAddress;

public interface ReceivingAddressService {


	void address( ReceivingAddress receivingAddress, Long shopuserId);

	List<ReceivingAddress> findAll(Long shopuserId);

	ReceivingAddress findOne(Long shopuserId, Long receivingAddressId);

	void update( ReceivingAddress receivingAddress);





}
