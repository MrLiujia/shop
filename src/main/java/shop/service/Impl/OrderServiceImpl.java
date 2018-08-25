package shop.service.Impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mapper.OrderMapper;
import shop.model.Orders;
import shop.model.OrderItem;
import shop.model.OrderState;
import shop.model.ReceivingAddress;
import shop.model.ShoppingCart;
import shop.model.ShoppingCartItem;
import shop.service.AlipaySignatureException;
import shop.service.OrderService;
import shop.service.ShoppingCartService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	private OrderMapper orderMapper;
	
	private ShoppingCartService shoppingCartService;
	private AlipayClient alipayClient;
	
    private String alipayReturnUrl;
    private String alipayNotifyUrl;
    private String alipayPublicKey;
    private String alipaySignType;
    
    private ObjectMapper objectMapper;
    
	
	@Autowired
	public OrderServiceImpl(OrderMapper orderMapper, 
							ShoppingCartService shoppingCartService,
							AlipayClient alipayClient,
							Environment env,
							ObjectMapper objectMapper) throws IOException {
		this.orderMapper = orderMapper;
		this.shoppingCartService = shoppingCartService;
		this.alipayClient = alipayClient;
        this.alipayReturnUrl = env.getProperty("alipay.returnUrl");
        this.alipayNotifyUrl = env.getProperty("alipay.notifyUrl");
        this.alipayPublicKey = FileUtils.readFileToString(
                new File(env.getProperty("alipay.alipayPublicKeyFile")), 
                "UTF-8");
        this.alipaySignType = env.getProperty("alipay.signType");
        
        this.objectMapper = objectMapper;
	}


	@Override
	public Orders create(Long shopuserId, Long receivingAddressId) {
		Orders orders = new Orders();
		orders.setShopuserId(shopuserId);
		ReceivingAddress receivingAddress = new ReceivingAddress();
		receivingAddress.setId(receivingAddressId);
		orders.setReceivingAddress(receivingAddress);
		orders.setLastTime(new Date());
		orders.setState(OrderState.Created);
		
		orderMapper.create(orders);
		
		ShoppingCart shoppingCart = shoppingCartService.findOneByShopuserId(shopuserId);
		for(ShoppingCartItem cartItem : shoppingCart.getItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderId(orders.getId());
			orderItem.setCellphone(cartItem.getCellphone());
			orderItem.setQuantity(cartItem.getQuantity());
			orderMapper.addItem(orderItem);
		}
		shoppingCartService.clearCart(shopuserId);
		
		return orders;
	}


	@Override
	public Orders findOne(Long shopuserId, Long ordersId) {
		// TODO Auto-generated method stub
		return orderMapper.findOne(shopuserId,ordersId);
	}


	@Override
	public List<Orders> findAll(Long shopuserId) {
		// TODO Auto-generated method stub
		return orderMapper.findAll(shopuserId);
	}


	@Override
	public String payFrom(Long shopuserId, Long id) {
		Orders orders = findOne(shopuserId, id);
		
		if (orders.getState() != OrderState.Created) {
			throw new IllegalStateException("只有Created状态的订单才能发起支付");
		}

		BigDecimal totalAmount = BigDecimal.valueOf(orders.totalCost()).divide(BigDecimal.valueOf(100)); // 订单总金额（元）

		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest(); // 即将发送给支付宝的请求（电脑网站支付请求）
		alipayRequest.setReturnUrl(alipayReturnUrl); // 浏览器端完成支付后跳转回商户的地址（同步通知）
		alipayRequest.setNotifyUrl(alipayNotifyUrl); // 支付宝服务端确认支付成功后通知商户的地址（异步通知）
        
		Map<String, Object> bizContent = new HashMap<>(); // biz - business
        // 填充业务参数
        bizContent.put("out_trade_no", "" + id + "-" + new Date().getTime()); // 商户订单号，加时间戳是为了避免测试时订单号重复
        bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY"); // 产品码，固定
        bizContent.put("total_amount", totalAmount); // 订单总金额（元）
        bizContent.put("subject", "shop手机商城订单支付"); // 订单标题
        bizContent.put("body", "TODO 显示订单项概要"); // 订单描述
        

		// 直接将完整的表单html输出到页面
        try {
        	 String bizContentStr = objectMapper.writeValueAsString(bizContent);
             System.out.println("alipay.bizContentStr: " + bizContentStr);
             alipayRequest.setBizContent(bizContentStr);
        	return alipayClient.pageExecute(alipayRequest).getBody(); // 调用SDK生成支付表单
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 
		
		
		
		
	}


	@Override
	public void verifySignature(Map<String, String> paramMap) 
				throws AlipaySignatureException {
		try {
            if (!AlipaySignature.rsaCheckV1(paramMap, alipayPublicKey, "UTF-8", alipaySignType)) {
                throw new AlipaySignatureException();
            }
        } catch (AlipayApiException e) {
            throw new AlipaySignatureException(e); 
        }
		
		
	}


	

}
