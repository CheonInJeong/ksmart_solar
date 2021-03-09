package ksmart38.team.ksmart_solar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommonController {
	@GetMapping("/")
	public String main() {
		return "main";
	}
	@GetMapping("/components/todoList")
	public String test() {
		return "components/todoList";
	}

}
