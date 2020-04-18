package br.biblioteca.livros.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.livros.models.Author;
import br.biblioteca.livros.service.AuthorsService;

@Controller
@RequestMapping("/authors")
public class AuthorController {

	@Autowired
	AuthorsService authorService;

	@GetMapping("/list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("authors/index");

		List<Author> authorList = authorService.listAllAuthors();
		modelAndView.addObject("authors", authorList);

		return modelAndView;

	}

	@GetMapping("/new")
	public ModelAndView add(@ModelAttribute Author author) {
		return new ModelAndView("authors/form");
	}

	@PostMapping("/post")
	public ModelAndView post(@Valid Author author, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ModelAndView("authors/form");
		}
		authorService.saveAuthor(author);

		return new ModelAndView("redirect:/authors/list");
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		Author author = authorService.findAuthor(id);
		ModelAndView modelAndView = new ModelAndView("authors/form");
		modelAndView.addObject("author", author);

		return modelAndView;
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {

		authorService.deleteAuthor(id);

		return new ModelAndView("redirect:/authors/list");
	}

}
