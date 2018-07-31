package com.param.comman;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommanController {
	//private static final Logger logger = Logger.getLogger(CommanController.class);
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String defaultLink() {
		System.out.println("Started..!!");
	
		return "views/index.html";
	}
	
	/*@RequestMapping(value="bedManagement", method=RequestMethod.GET)
	public String loginView(){
		//logger.error("Message from controller..!!");
		System.out.println("Login");
		//ModelAndView model= new ModelAndView("index");
		return "about";
	}*/

}
