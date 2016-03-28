package com.ithejas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
//new feature 
@Controller
public class Helloworld {
	@RequestMapping("/welcome") 
	//public ModelAndView helloWorld() {
	public String helloWorld(Model model, @RequestParam(value = "name", defaultValue = "Guest") String name) {
		 model.addAttribute("name", name);
		String message = "<br><div style='text-align:center;'>"
				+ "<h3>********** Hello World, Spring MVC Tutorial</h3>This message is coming from CrunchifyHelloWorld.java **********</div><br><br>";
		//return new ModelAndView("welcome", "message", message);
		if ("admin".equals(name)) {
            model.addAttribute("email", "admin@example.com");
        } else {
            model.addAttribute("email", "Not set");
        }
		model.addAttribute("message",message);
		return "welcome";
	}
}
