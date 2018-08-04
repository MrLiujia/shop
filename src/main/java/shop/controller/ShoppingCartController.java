package shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import shop.model.ShoppingCart;
import shop.model.ShoppingCartItem;
import shop.service.ShoppingCartService;

@Controller
public class ShoppingCartController {
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	public ShoppingCartController(ShoppingCartService shoppingCartService) {
		this.shoppingCartService = shoppingCartService;
	}

	//购物车项
	@RequestMapping(method=RequestMethod.POST, value="/uc/shopping-cart/add")
	public String add(@RequestParam Long cellphoneId,
					  @AuthenticationPrincipal(expression = "shopuser.id") Long shopuserId) {
		shoppingCartService.addToCart(shopuserId, cellphoneId, 1);
        return "redirect:/" + cellphoneId;
	}
	
	//购物车
    @RequestMapping(method = RequestMethod.GET, value = "/uc/shopping-cart")
    public String details(@AuthenticationPrincipal(expression = "shopuser.id") Long shopuserId,
                          Model model) {
        ShoppingCart shoppingCart = shoppingCartService.findOneByShopuserId(shopuserId);
        model.addAttribute("shoppingCart", shoppingCart);
        return "shopping-cart";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/uc/shopping-cart/delete")
    public String removeItem(@RequestParam Long cellphoneId,
			  @AuthenticationPrincipal(expression = "shopuser.id") Long shopuserId) {
    	shoppingCartService.removeItem(shopuserId,cellphoneId);
		return "redirect:/uc/shopping-cart";
    	
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/uc/shopping-cart/item-dec")
    public String decItem(@RequestParam Long cellphoneId,
			  @AuthenticationPrincipal(expression = "shopuser.id") Long shopuserId) {
    	shoppingCartService.decItem(shopuserId, cellphoneId);		
    	return "redirect:/uc/shopping-cart";
    	}
    
    
    @RequestMapping(method = RequestMethod.POST, value = "/uc/shopping-cart/item-inc")
    public String incItem(@RequestParam Long cellphoneId,
			  @AuthenticationPrincipal(expression = "shopuser.id") Long shopuserId) {
    	shoppingCartService.incItem(shopuserId, cellphoneId);		
    	return "redirect:/uc/shopping-cart";
    	}
    
    
    
    
    
    
}







