package tw.org.iii.cma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PathController {
	@RequestMapping(path = {"/", "/index.page"})
	public String method1() {
		return "/index";
	}
	@RequestMapping(value = {"/secure/login.page"})
	public String method2() {
		return "/secure/login";
	}
	@RequestMapping({"/pages/product.page"})
	public String method3() {
		return "/pages/product";
	}
	@RequestMapping("/pages/display.page")
	public String method4() {
		return "/pages/display";
	}
}
