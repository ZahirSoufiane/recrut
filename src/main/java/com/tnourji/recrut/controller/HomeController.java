package com.tnourji.recrut.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tnourji.recrut.model.User;
import com.tnourji.recrut.service.UserService;

@Controller
@RequestMapping("/")
public class HomeController extends BaseController {
	
private static final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    
    private static final String MAIL_INFO = "zahir.soufian@gmail.com";
    
    @Autowired
    private UserService userService;
    
    
    /**
     * subscribe or subscribed
     * 
     * @param model
     *            model
     * @return view
     */
    @RequestMapping(value = { "welcome", "/*" },
            method = RequestMethod.GET)
    public ModelAndView subscribe(Model model) {
        return new ModelAndView("entrypoint.html");
    }

}
