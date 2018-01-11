package com.tnourji.recrut.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tnourji.recrut.model.User;
import com.tnourji.recrut.service.UserService;

@RestController
// @RequestMapping("/")
public class HomeController extends BaseController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

	private static final String MAIL_INFO = "zahir.soufian@gmail.com";

	@Autowired
	private UserService userService;

	@GetMapping(value={"/welcome","/home",""})
	public ModelAndView subscribe() {
		return new ModelAndView("index.html");
		
	}
	
	@GetMapping(value={"/inscrir"})
	public ModelAndView register() {
		return new ModelAndView("inscrir.html");
		
	}
	
	
}
