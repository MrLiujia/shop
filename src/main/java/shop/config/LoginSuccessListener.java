package shop.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import shop.mapper.ShopuserMapper;
@Component
public class LoginSuccessListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent>{

	private ShopuserMapper shopuserMapper;
	
	@Autowired
	public LoginSuccessListener(ShopuserMapper shopuserMapper) {
		this.shopuserMapper = shopuserMapper;
	}

	@Override
	public void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {
		ShopuserDetailsImpl shopuserDetails = 
				 (ShopuserDetailsImpl) event.getAuthentication().getPrincipal();

		System.out.println("有人登录成功了: #" + shopuserDetails.getShopuser().getId() + ", " + shopuserDetails.getUsername());

		shopuserMapper.updateLastLoginTime(shopuserDetails.getShopuser().getId(), new Date());

	}

}
