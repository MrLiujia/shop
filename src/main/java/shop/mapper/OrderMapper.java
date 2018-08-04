package shop.mapper;

import shop.model.Orders;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import shop.model.OrderItem;

public interface OrderMapper {
	
	void create(Orders orders);

    void addItem(OrderItem orderItem);

	Orders findOne(@Param("shopuserId") Long shopuserId, 
				   @Param("ordersId")Long ordersId);

	List<Orders> findAll(Long shopuserId);



}
