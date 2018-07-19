package shop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.controller.form.ShopuserForm;
import shop.service.ShopuserService;

@Controller
public class ShopuserController {
	private ShopuserService shopuserService;

	@Autowired
	public ShopuserController(ShopuserService shopuserService) {
		this.shopuserService = shopuserService;
	}

	@RequestMapping(method=RequestMethod.GET, value="/shopuser/add")
	public String add(@ModelAttribute ShopuserForm shopuserForm){
		return "shopuser-add";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/shopuser/add")
	public String create(@ModelAttribute @Valid ShopuserForm shopuserForm,
						BindingResult bindingResult) {
		if(bindingResult.hasErrors() ){
			return "shopuser-add";
		}	
		shopuserService.create(shopuserForm.getUsername(), shopuserForm.getPassword());
		return "redirect:/login";
	}
		
}
