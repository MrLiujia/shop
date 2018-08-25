package shop.service;

import java.util.List;
import java.util.Map;



import shop.model.Orders;

public interface OrderService {

	Orders create(Long shopuserId, Long receivingAddressId); 

	Orders findOne(Long shopuserId, Long ordersId);

	List<Orders> findAll(Long shopuserId);

	String payFrom(Long shopuserId, Long id);

    /**
     * 支付宝验签
     * @param paramMap 所有请求参数
     * @throws AlipaySignatureException 若签名无效
     */
	void verifySignature(Map<String, String> paramMap) throws AlipaySignatureException;

}
