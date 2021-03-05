package com.hcl.dadimusicwebapp.controller;

import com.hcl.dadimusicwebapp.model.User;
import com.hcl.dadimusicwebapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequestMapping("/register")
@Controller
public class RegisterController {

  @Autowired
  private UserService userService;

  @GetMapping
  public String showForm(Model model) {
    model.addAttribute("accountInput", new User());
    return "register";
  }

  @PostMapping
  public String checkUserInfo(@ModelAttribute("accountInput") User accountInput, Model model, RedirectAttributes attributes) {
    if (userService.usernameExists(accountInput.getUsername())) {
      model.addAttribute("usernameAlert", "Username already taken!");
      return "register";
    }
    accountInput.setRole("USER");
    userService.addUser(accountInput);

    attributes.addFlashAttribute("registerAlert", "Registration Successful!");
    return "redirect:/login";
  }

}
