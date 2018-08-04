package shop.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mapper.ReceivingAddressMapper;
import shop.model.ReceivingAddress;
import shop.model.Shopuser;
import shop.service.ReceivingAddressService;
@Service
public class ReceivingAddressServiceImpl implements ReceivingAddressService{
	private ReceivingAddressMapper receivingAddressMapper;
	
	
	@Autowired
	public ReceivingAddressServiceImpl(ReceivingAddressMapper receivingAddressMappering) {
		this.receivingAddressMapper = receivingAddressMappering;
	}



	@Override
	public void address(ReceivingAddress receivingAddress, Long shopuserId) {
		// TODO Auto-generated method stub
		Shopuser shopuser = new Shopuser();
		 shopuser.setId(shopuserId);
		 receivingAddress.setShopuser(shopuser);
		 receivingAddressMapper.address(receivingAddress);
	}



	@Override
	public List<ReceivingAddress> findAll(Long shopuserId) {
		// TODO Auto-generated method stub
		return receivingAddressMapper.findAll(shopuserId);
	}



	@Override
	public ReceivingAddress findOne(Long shopuserId, Long receivingAddressId) {
		// TODO Auto-generated method stub
		return receivingAddressMapper.findOne(shopuserId, receivingAddressId);
	}



	@Override
	public void update( ReceivingAddress receivingAddress) {
		// TODO Auto-generated method stub
		receivingAddressMapper.update(receivingAddress);
	}







	
	
}
