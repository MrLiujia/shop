package shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shop.model.ShoppingCartItem;

public interface ShoppingCartMapper {




	void incItemQuantity(@Param("shopuserId")Long shopuserId, 
						 @Param("cellphoneId")Long cellphoneId, 
						 @Param("quantity")int quantity);


	void createItem(@Param("shopuserId")Long shopuserId, 
			 		@Param("cellphoneId")Long cellphoneId, 
			 		@Param("quantity")int quantity);


	List<ShoppingCartItem> findAllItems(Long shopuserId);




	void removeItem(@Param("shopuserId")Long shopuserId, 
				@Param("cellphoneId")Long cellphoneId);


	Integer findItemQuantity(@Param("shopuserId")Long shopuserId,
							 @Param("cellphoneId")Long cellphoneId);


	void deleteItemsByUserId(Long shopuserId);

	

}
