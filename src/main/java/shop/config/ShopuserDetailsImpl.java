package shop.config;


import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import shop.model.Shopuser;

public class ShopuserDetailsImpl extends User{
	private Shopuser shopuser;

	public ShopuserDetailsImpl(Shopuser shopuser) {
		super(shopuser.getUsername(),
			  shopuser.getPassword(),
			  true, true, true, true, Arrays.asList(new SimpleGrantedAuthority("ROLE_SHOPUSER")));
		this.shopuser = shopuser;
	}

	public Shopuser getShopuser() {
		return shopuser;
	}
	
}
