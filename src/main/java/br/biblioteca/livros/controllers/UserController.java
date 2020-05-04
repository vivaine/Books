package br.biblioteca.livros.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.livros.models.User;
import br.biblioteca.livros.service.SecurityService;
import br.biblioteca.livros.service.UserService;
import br.biblioteca.livros.validator.LoginValidator;
import br.biblioteca.livros.validator.UserValidator;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private SecurityService securityService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private LoginValidator loginValidator;

	@GetMapping("/login")
	public ModelAndView login() {
		return new ModelAndView("users/form", "userForm", new User());
	}

	@PostMapping("/authentication")
	public ModelAndView authentication(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,
			Model model) {
		loginValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return new ModelAndView("users/form");
		}

		securityService.login(userForm.getUsername(), userForm.getPassword());

		return new ModelAndView("redirect:/users/list");
	}

	@GetMapping("/list")
	public ModelAndView list() {
		return new ModelAndView("/users/list");
	}

	@GetMapping("/listadmin")
	public ModelAndView listadmin(User user) {

		List<User> users = userService.findAll();
		return new ModelAndView("/users/listadmin", "users", users);
	}

	@GetMapping(value = "/registration")
	public ModelAndView registration() {
		return new ModelAndView("users/registration", "userForm", new User());
	}

	@PostMapping(value = "/registration")
	public ModelAndView registrationform(@ModelAttribute("userForm") User userForm, BindingResult bindingResult,
			Model model) {
		userValidator.validate(userForm, bindingResult);

		if (bindingResult.hasErrors()) {
			return new ModelAndView("users/registration");
		}

		String password = userForm.getPassword();

		userService.save(userForm);

		try {
			securityService.login(userForm.getUsername(), password);
			return new ModelAndView("redirect:/users/list");
		} catch (Exception e) {
			return new ModelAndView("redirect:/users/login");
		}
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		SecurityContextHolder.clearContext();
		if (session != null) {
			session.invalidate();
		}

		return "redirect:/users/login";
	}

}
