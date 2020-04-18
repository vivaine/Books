package br.biblioteca.livros.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.biblioteca.livros.converter.BookConverter;
import br.biblioteca.livros.dto.BookDTO;
import br.biblioteca.livros.models.Book;
import br.biblioteca.livros.service.BooksService;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	BooksService booksService;

	@GetMapping("/books/list")
	public ResponseEntity<List<BookDTO>> list() {

		List<Book> booksList = booksService.listAllBooks();

		return ResponseEntity.ok(BookConverter.toDTO(booksList));
	}
}
