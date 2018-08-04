package shop.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mapper.ShoppingCartMapper;
import shop.model.ShoppingCart;
import shop.model.ShoppingCartItem;
import shop.service.ShoppingCartService;
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
	private ShoppingCartMapper shoppingCartMapper;
	
	@Autowired
	public ShoppingCartServiceImpl(ShoppingCartMapper shoppingCartMapper) {
		this.shoppingCartMapper = shoppingCartMapper;
	}


	@Override
	public void addToCart(Long shopuserId, Long cellphoneId, int quantity) {
		Integer itemQuantity = shoppingCartMapper.findItemQuantity(shopuserId, cellphoneId);
		if (itemQuantity != null) {
			if (itemQuantity + quantity == 0) {
				shoppingCartMapper.removeItem(shopuserId, cellphoneId);
			}else {
				shoppingCartMapper.incItemQuantity(shopuserId, cellphoneId, quantity);
			}
			
		}else {
			shoppingCartMapper.createItem(shopuserId, cellphoneId, quantity);
		}
		
	}


	public List<ShoppingCartItem> findAllItems(Long shopuserId) {
		// TODO Auto-generated method stub
		return shoppingCartMapper.findAllItems(shopuserId);
	}


	@Override
	public void removeItem(Long shopuserId, Long cellphoneId) {
		shoppingCartMapper.removeItem(shopuserId,cellphoneId);
	}


	@Override
	public void decItem(Long shopuserId, Long cellphoneId) {
		 addToCart(shopuserId, cellphoneId, -1);
	}


	@Override
	public void incItem(Long shopuserId, Long cellphoneId) {
		addToCart(shopuserId, cellphoneId, +1);
	}


	@Override
	public ShoppingCart findOneByShopuserId(Long shopuserId) {
		// TODO Auto-generated method stub
		return new ShoppingCart(findAllItems(shopuserId));
	}


	@Override
	public void clearCart(Long shopuserId) {
		
		shoppingCartMapper.deleteItemsByUserId(shopuserId);
	}




	

}
