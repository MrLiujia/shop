package shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shop.model.Cellphone;
import shop.service.IndexService;
@Controller
public class IndexController {
	private IndexService indexService;
	
	@Autowired
	public IndexController(IndexService indexService) {
		this.indexService = indexService;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/")
    public String index(Model model,
    					@ModelAttribute Cellphone cellphone) {
        System.out.println("手机搜索条件: " + cellphone);
        List<Cellphone> cellphones = indexService.search(cellphone);
//		List<Cellphone> cellphones =  indexService.findAll();
		model.addAttribute("cellphones", cellphones);
        return "index";
    }
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public String details(@PathVariable Long id, Model model) {
		Cellphone cellphone = indexService.findOne(id);
	    model.addAttribute("cellphones", cellphone);
	    return "cellphone-details";
	}

}
