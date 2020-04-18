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
import br.biblioteca.livros.models.Book;
import br.biblioteca.livros.service.AuthorsService;
import br.biblioteca.livros.service.BooksService;

@Controller
@RequestMapping("/books")
public class BookController {

	@Autowired
	BooksService bookService;

	@Autowired
	AuthorsService authorService;

	@GetMapping("/list")
	public ModelAndView list() {
		ModelAndView modelAndView = new ModelAndView("books/index");

		List<Book> booksList = bookService.listAllBooks();
		modelAndView.addObject("books", booksList);
		return modelAndView;
	}

	@GetMapping("/new")
	public ModelAndView add(@ModelAttribute Book book) {
		ModelAndView modelAndView = new ModelAndView("books/form");
		List<Author> authorsList = authorService.listAllAuthors();
		modelAndView.addObject("authorsList", authorsList);
		return modelAndView;
	}

	@PostMapping("/post")
	public ModelAndView post(@Valid Book book, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<Author> authorsList = authorService.listAllAuthors();
			return new ModelAndView("books/form", "authorsList", authorsList);
		}
		bookService.saveBook(book);
		return new ModelAndView("redirect:/books/list");
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {

		Book book = bookService.findBook(id);

		List<Author> authorsList = authorService.listAllAuthors();

		ModelAndView modelAndView = new ModelAndView("books/form");
		modelAndView.addObject("authorsList", authorsList);
		modelAndView.addObject("book", book);

		return modelAndView;
	}

	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {

		bookService.deleteBook(id);

		return new ModelAndView("redirect:/books/list");
	}

}
