package shop.service;

import shop.model.ShoppingCart;

public interface ShoppingCartService {

	void addToCart(Long shopuserId, Long cellphoneId, int quantity);


	void removeItem(Long shopuserId, Long cellphoneId);

	void decItem(Long shopuserId, Long cellphoneId);

	void incItem(Long shopuserId, Long cellphoneId);

	ShoppingCart findOneByShopuserId(Long shopuserId);


	void clearCart(Long shopuserId);

	

}
