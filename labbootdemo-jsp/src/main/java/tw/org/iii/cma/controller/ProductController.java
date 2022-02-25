package tw.org.iii.cma.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.org.iii.cma.domain.ProductBean;
import tw.org.iii.cma.service.ProductRepositoryService;

@Controller
public class ProductController {
	@Autowired
	private ProductRepositoryService productService;
//	private ProductService productService;
	
	
	@Autowired
	private MessageSource messageSource;

	//客製化的資料轉換規則，要宣告在@RequestMapping之前
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.registerCustomEditor(Integer.class,
				new CustomNumberEditor(Integer.class, true));
		webDataBinder.registerCustomEditor(Double.class,
				new CustomNumberEditor(Double.class, true));
		webDataBinder.registerCustomEditor(java.util.Date.class,
				new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	@RequestMapping(
			path= {"/pages/product.controller"}
	)
	public String handlerMethod(Locale locale, Model model, 
			String prodaction,// prodaction沒對應到要自己寫
			ProductBean bean, BindingResult bindingResult) {
//接收資料
//轉換資料(Spring幫忙做了，所以只留錯誤訊息)
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);
		
		if(bindingResult!=null && bindingResult.hasFieldErrors()) {
			if(bindingResult.hasFieldErrors("id")) {
				errors.put("id", messageSource.getMessage("product.id.format", null, locale));
			}
			if(bindingResult.hasFieldErrors("price")) {
				errors.put("price", messageSource.getMessage("product.price.format", null, locale));	
			}
			if(bindingResult.hasFieldErrors("make")) {
				errors.put("make", messageSource.getMessage("product.make.format", null, locale));	
			}
			if(bindingResult.hasFieldErrors("expire")) {
				errors.put("expire", messageSource.getMessage("product.expire.format", null, locale));	
			}
			
		}
//驗證資料
		if("Insert".equals(prodaction) || "Update".equals(prodaction)|| "Delete".equals(prodaction)) {
			if(bean==null || bean.getId()==null) {
				errors.put("id",messageSource.getMessage("product.id.required",new String[] {prodaction},locale));	
			}
		}
		
		if(errors!=null && !errors.isEmpty()) {
			return "/pages/product";
		}
		
		
//呼叫Model
//根據Model執行結果導向View
		if(prodaction!=null && prodaction.equals("Select")) {
			List<ProductBean> result = productService.select(bean);
			model.addAttribute("select", result);
			return "/pages/display";
		} else if(prodaction!=null && prodaction.equals("Insert")) {
			ProductBean result = productService.insert(bean);
			if(result==null) {
				errors.put("action", "Insert fail");
			} else {
				model.addAttribute("insert", result);
			}
			return "/pages/product";
		} else if(prodaction!=null && prodaction.equals("Update")) {
			ProductBean result = productService.update(bean);
			if(result==null) {
				errors.put("action", "Update fail");
			} else {
				model.addAttribute("update", result);
			}
			return "/pages/product";
		} else if(prodaction!=null && prodaction.equals("Delete")) {
			boolean result = productService.delete(bean);
			if(!result) {
				model.addAttribute("delete", 0);
			} else {
				model.addAttribute("delete", 1);
			}
			return "/pages/product";
		} else  {
			errors.put("action", "Unknown Action:"+prodaction);
			return "/pages/product";
		}		
	}
}
