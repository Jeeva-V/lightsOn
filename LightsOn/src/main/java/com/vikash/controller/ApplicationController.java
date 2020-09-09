package com.vikash.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.connector.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vikash.modal.User;
import com.vikash.services.UserService;

@Controller
public class ApplicationController {

	@Autowired
	UserService userService;

	@RequestMapping("/welcome")
	public String Welcome(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_HOME");
		return "welcomepage";
	}

	@RequestMapping("/register")
	public String registration(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_REGISTER"); 
		return "welcomepage";
	}

	@PostMapping("/save-user")
	public String registerUser(@ModelAttribute User user, BindingResult bindingResult, HttpServletRequest request) {
		userService.saveMyUser(user);
		request.setAttribute("mode", "MODE_HOME");
		return "welcomepage";
	}

	@GetMapping("/show-users")
	public String showAllUsers(HttpServletRequest request) {
		request.setAttribute("users", userService.showAllUsers());
		request.setAttribute("mode", "ALL_USERS");
		return "welcomepage";
	}

	@RequestMapping("/delete-user")
	public String deleteUser(@RequestParam int id, HttpServletRequest request) {
		userService.deleteMyUser(id);
		request.setAttribute("users", userService.showAllUsers());
		request.setAttribute("mode", "ALL_USERS");
		return "welcomepage";
	}
	
	@RequestMapping("/edit-user")
	public String editUser(@RequestParam int id,HttpServletRequest request) {
		request.setAttribute("user", userService.editUser(id));
		request.setAttribute("mode", "MODE_UPDATE");
		return "welcomepage";
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		request.setAttribute("mode", "MODE_LOGIN");
		return "welcomepage";
	}
	
	@RequestMapping ("/login-user")
	public String loginUser(@ModelAttribute User user, HttpServletRequest request) {
		if(userService.findByUsernameAndPassword(user.getUsername(), user.getPassword())!=null) {
			return "homepage";
		}
		else {
			request.setAttribute("error", "Invalid Username or Password");
			request.setAttribute("mode", "MODE_LOGIN");
			return "welcomepage";
			
		}
	}
	
	@RequestMapping("/main-floor")
	public String adminBlock(HttpServletRequest request)
	{
		request.setAttribute("mode", "MAIN_FLOORS");
		return "adminfloor";
	}
	@RequestMapping("/civil-floor")
	public String civilBlock(HttpServletRequest request)
	{
		request.setAttribute("mode", "CIVIL_FLOORS");
		return "adminfloor";
	}
	@RequestMapping("/mech-floor")
	public String mechBlock(HttpServletRequest request)
	{
		request.setAttribute("mode", "MECH_FLOORS");
		return "adminfloor";
	}
	@RequestMapping("/bh-floor")
	public String boysHostelBlock(HttpServletRequest request)
	{
		request.setAttribute("mode", "BH_FLOORS");
		return "adminfloor";
	}
	@RequestMapping("/gh-floor")
	public String girlsHostelBlock(HttpServletRequest request)
	{
		request.setAttribute("mode", "GH_FLOORS");
		return "adminfloor";
	}
	@RequestMapping("/operation")
	public String operation(HttpServletRequest request)
	{
		//request.setAttribute("mode", "GH_FLOORS");
		return "Operation";
	}

}
