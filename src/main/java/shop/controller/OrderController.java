package shop.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;

import shop.controller.form.OrderForm;
import shop.model.OrderState;
import shop.model.Orders;
import shop.model.ReceivingAddress;
import shop.model.ShoppingCart;
import shop.service.OrderService;
import shop.service.ReceivingAddressService;
import shop.service.ShoppingCartService;

@Controller
public class OrderController {
	private ShoppingCartService shoppingCartService;
	
	private ReceivingAddressService receivingAddressService;
	private OrderService orderService;

	
	@Autowired
	public OrderController(ShoppingCartService shoppingCartService, ReceivingAddressService receivingAddressService,
			OrderService orderService) {
		this.shoppingCartService = shoppingCartService;
		this.receivingAddressService = receivingAddressService;
		this.orderService = orderService;
	}


	@RequestMapping(method=RequestMethod.GET, value="/uc/orders/add")
	public String add(@AuthenticationPrincipal(expression = "shopuser.id") Long shopuserId,
					  Model model,
					  @ModelAttribute OrderForm orderForm) {
		prepareInAdd(shopuserId,model);
		return "order-add";
	}

		private void prepareInAdd(Long shopuserId, Model model) {
		ShoppingCart shoppingCart = shoppingCartService.findOneByShopuserId(shopuserId);
		model.addAttribute("shoppingCart", shoppingCart);
		List<ReceivingAddress> receivingAddress = receivingAddressService.findAll(shopuserId);
		model.addAttribute("receivingAddress",receivingAddress);
	}
		
	@RequestMapping(method=RequestMethod.POST, value="/uc/orders/add")
	public String create(@AuthenticationPrincipal(expression = "shopuser.id") Long shopuserId,
						 @ModelAttribute @Valid OrderForm orderForm,
						 BindingResult bindingResult,
						 Model model) {
			if (bindingResult.hasErrors()) {
				prepareInAdd(shopuserId,model);
				return "order-add";
			}
		Orders orders = orderService.create(shopuserId, orderForm.getReceivingAddressId());
		return "redirect:/uc/orders/"+orders.getId();
	}
	
	 
    @RequestMapping(method = RequestMethod.GET, value = "/uc/orders/{id}")
    public String details(@AuthenticationPrincipal(expression = "shopuser.id") Long shopuserId,
                          @PathVariable Long id,
                          Model model) {
        Orders orders = orderService.findOne(shopuserId, id);
        model.addAttribute("orders", orders);
        return "order-details";
    }

    
    @RequestMapping(method = RequestMethod.GET, value = "/uc/orders/")
    public String list(@AuthenticationPrincipal(expression = "shopuser.id") Long shopuserId,
                       Model model) {
        List<Orders> orders = orderService.findAll(shopuserId);
        model.addAttribute("orders", orders);
        return "order-list";
    }
    
    
    
    
	@RequestMapping(method = RequestMethod.POST, value = "/uc/orders/{id}/pay",
					produces = "text/html;charset=UTF-8" // 非常重要，支付宝api响应是html片段（不含编码），必须显式指定
					)
	@ResponseBody
	public String pay(@AuthenticationPrincipal(expression = "shopuser.id") Long shopuserId, @PathVariable Long id)
			throws AlipayApiException {
		return orderService.payFrom(shopuserId, id);

		
	}

	@RequestMapping(method = RequestMethod.GET, value = "/uc/orders/sync-pay-cb")
	public String payOk(@RequestParam("out_trade_no") String orderNumber,
			 			@RequestParam Map<String, String> paramMap, // 将所有请求参数封装到map中
					 	Model model) {
		 // 验签
        orderService.verifySignature(paramMap);
		
		Long orderId = Long.valueOf(orderNumber.split("-")[0]); // 如 3-1533093080374
		model.addAttribute("orderId", orderId);
		return "order-pay-ok";
	}
}
