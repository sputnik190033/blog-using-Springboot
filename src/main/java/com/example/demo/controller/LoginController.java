package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.UserInfo;
import com.example.demo.repository.UserInfoRepository;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@GetMapping("/login")
	public String login() {
		
		log.info("login method has been executed");
		return "login";
	}
	
	@PostMapping("/blog")
	public ModelAndView login(@RequestParam("username") String userName,
		@RequestParam("password") String password, ModelAndView mv) {
		
		mv.addObject("userName", userName);
		
		UserInfo userInfo = userInfoRepository.findByName(userName);
			
		if(userInfo != null && password.equals(userInfo.getPassword())) {
			//return "Wblog";
			mv.setViewName("Wblog");
		}else {
			//return "redirect:/login";
			mv.setViewName("fail");
		}
		
		return mv;
	}	
}