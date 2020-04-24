package br.biblioteca.livros.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.biblioteca.livros.converter.BookConverter;
import br.biblioteca.livros.dto.BookDTO;
import br.biblioteca.livros.dto.EvaluationDTO;
import br.biblioteca.livros.exception.BookNotFoundException;
import br.biblioteca.livros.facade.ApiFacade;
import br.biblioteca.livros.models.Book;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	ApiFacade apiController;

	@GetMapping("/books/list")
	public ResponseEntity<List<BookDTO>> list() {

		List<Book> booksList = apiController.listAllBooks();
		return ResponseEntity.ok(BookConverter.toDTO(booksList));
	}

	@PostMapping("/books/evaluation/{id}")
	public ResponseEntity<Long> comment(@PathVariable("id") Long id, @RequestBody EvaluationDTO evaluationDTO) {

		try {
			return ResponseEntity.ok(apiController.saveEvaluation(id, evaluationDTO));
		} catch (BookNotFoundException e) {
			return ResponseEntity.notFound().build();
		}

	}
}
