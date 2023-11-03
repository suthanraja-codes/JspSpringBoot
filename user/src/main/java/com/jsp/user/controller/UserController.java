package com.jsp.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.user.entity.UserEntity;
import com.jsp.user.service.UserService;

@Controller
public class UserController {
	
	@Autowired
	UserService service;
	
	@RequestMapping("/index")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/create")
	public String create() {
		return "create";
	}
	   
	@PostMapping("/adduser")
	public String saveData(UserEntity user) {
		service.addUser(user);
		return "redirect:/index";
	}
	
	@GetMapping("/getuser")
	public String getData(Model model){
		model.addAttribute("users",service.getUser());
		return "view";
	}
	
	@GetMapping("/getuser/{id}")
	public String getDataById(@PathVariable int id, Model model) {
		model.addAttribute("users",service.getUserById(id));
		return "update";
	}
	
	@PostMapping("/updateuser/{id}")
	public String updateDeatilsById(@PathVariable int id,@ModelAttribute("user") UserEntity user) {
		
		UserEntity user1=service.getUserById(id);
		user1.setId(id);
		user1.setName(user.getName());
		user1.setAge(user.getAge());
		user1.setGender(user.getGender());
		user1.setMobile(user.getMobile());
		user1.setLocation(user.getLocation());
		service.updateDetails(user1);
		return "redirect:/getuser";
	}
	
	
	@GetMapping("/deleteuser/{id}")
	public String removeDataById(@PathVariable int id) {
		 service.deleteUserById(id);
		return "redirect:/getuser";
	}
	
	@PostMapping("/getuserbygender")
	public String getDataByGender(@RequestParam String gender,  Model model) {
		List<UserEntity> userby = service.getUserByGender(gender);
		model.addAttribute("users",userby);
		return "viewgender";
	}
	
}
