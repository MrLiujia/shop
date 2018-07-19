package shop.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import shop.mapper.ShopuserMapper;
import shop.service.ShopuserService;
@Service
public class ShopuserServiceImpl implements ShopuserService{
	
	private ShopuserMapper shopuserMapper;
	private PasswordEncoder passwordEncoder;



	@Autowired
	public ShopuserServiceImpl(ShopuserMapper shopuserMapper, PasswordEncoder passwordEncoder) {
		this.shopuserMapper = shopuserMapper;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void create(String username, String password) {
		String encodedPassword = passwordEncoder.encode(password);
		shopuserMapper.create(username, encodedPassword);
	}



	

}
