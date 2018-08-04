package shop.controller;

import java.util.List;

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

import shop.model.ReceivingAddress;
import shop.model.Shopuser;
import shop.service.ReceivingAddressService;

@Controller
public class ReceivingAddressController {
	private ReceivingAddressService receivingAddressService;
	
	@Autowired
	public ReceivingAddressController(ReceivingAddressService receivingAddressService) {
		this.receivingAddressService = receivingAddressService;
	}

	//添加收货地址
	@RequestMapping(method=RequestMethod.GET,value="/uc/Receiving-address/add")
	public String add(@ModelAttribute ReceivingAddress receivingAddress) {
		return "ReceivingAddress-edit";
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/uc/Receiving-address/add")
	public String Address(@ModelAttribute @Valid ReceivingAddress receivingAddress,
						  BindingResult bindingResult,
						  @AuthenticationPrincipal(expression = "shopuser.id") Long shopuserId) {
		if (bindingResult.hasErrors()) {
			return "ReceivingAddress-edit";
		}
		receivingAddressService.address(receivingAddress,shopuserId);
		return "redirect:/uc/Receiving-address/";
	}
	//收货地址列表
	@RequestMapping(method=RequestMethod.GET,value="/uc/Receiving-address/")
	public String list(Model model,
			 		   @AuthenticationPrincipal(expression = "shopuser.id") Long shopuserId) {
		List<ReceivingAddress> receivingAddress = receivingAddressService.findAll(shopuserId);
		model.addAttribute("receivingAddress", receivingAddress);
		return "ReceivingAddress-list";
	}
	//修改收货地址
	@RequestMapping(method=RequestMethod.GET,value="/uc/Receiving-address/{id}/edit")
	public String edit(@PathVariable Long id,
					   @AuthenticationPrincipal(expression = "shopuser.id") Long shopuserId,
					   Model model) {
		ReceivingAddress receivingAddress = receivingAddressService.findOne(shopuserId,id);
		model.addAttribute("receivingAddress", receivingAddress);
		return "ReceivingAddress-edit";
	}
	 @RequestMapping(method = RequestMethod.POST, value = "/uc/Receiving-address/{id}/edit")
	    public String update(@AuthenticationPrincipal(expression = "shopuser.id") Long shopuserId,
	                         @ModelAttribute @Valid ReceivingAddress receivingAddress,
	                         BindingResult bindingResult) {
	        if (bindingResult.hasErrors()) {
	            return "ReceivingAddress-edit";
	        }
	        
	        Shopuser shopuser = new Shopuser();
	        shopuser.setId(shopuserId);
	        receivingAddress.setShopuser(shopuser);
	        receivingAddressService.update(receivingAddress);
	        return "redirect:/uc/Receiving-address/";
	    }
}
