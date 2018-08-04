package shop.service;

import java.util.List;

import shop.model.Orders;

public interface OrderService {

	Orders create(Long shopuserId, Long receivingAddressId);

	Orders findOne(Long shopuserId, Long ordersId);

	List<Orders> findAll(Long shopuserId);

	String payFrom(Long shopuserId, Long id);

}
