package shop.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import shop.mapper.ShopuserMapper;
import shop.model.Shopuser;
@Service
public class ShopuserDetailsServiceImpl implements UserDetailsService{

	private ShopuserMapper shopuserMapper;
	@Autowired
	public ShopuserDetailsServiceImpl(ShopuserMapper shopuserMapper) {
		this.shopuserMapper = shopuserMapper;
	}

	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		Shopuser shopuser =  shopuserMapper.findOneByUsername(username);
		 if (shopuser == null) { 
	            throw new UsernameNotFoundException("用户名不存在: " + username);
	        }
		return new ShopuserDetailsImpl(shopuser); 
		
	}

}
