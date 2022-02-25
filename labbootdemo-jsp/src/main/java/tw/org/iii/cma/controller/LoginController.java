package tw.org.iii.cma.controller;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import tw.org.iii.cma.domain.CustomerBean;
import tw.org.iii.cma.service.CustomerService;

@Controller
public class LoginController {
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private MessageSource messageSource;
	
	@RequestMapping(
			path = {"/secure/login.controller"}
	)
	public String handleMethod(Locale locale, Model model,
			String username, String password, HttpSession session) {
//接收資料
//驗證資料
		Map<String, String> errors = new HashMap<String, String>();
		model.addAttribute("errors", errors);
		
		if(username==null || username.length()==0) {
			errors.put("username", messageSource.getMessage("login.username.required", null, locale));
		}
		if(password==null || password.length()==0) {
			errors.put("password", messageSource.getMessage("login.password.required", null, locale));
		}
		
		if(errors!=null && !errors.isEmpty()) {
			return "/secure/login";
		}
		
//呼叫model
		CustomerBean bean = customerService.login(username, password);
		
//根據model執行結果，導向view
		if(bean==null) {
			errors.put("password", messageSource.getMessage("login.failed", null, locale));
			return "/secure/login";
		} else {
			session.setAttribute("user", bean);
			return "redirect:/";
		}
	}
}
