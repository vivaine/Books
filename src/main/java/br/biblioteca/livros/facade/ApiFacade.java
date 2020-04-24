package br.biblioteca.livros.facade;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.biblioteca.livros.converter.EvaluationConverter;
import br.biblioteca.livros.dto.EvaluationDTO;
import br.biblioteca.livros.models.Book;
import br.biblioteca.livros.models.Evaluation;
import br.biblioteca.livros.service.BooksService;
import br.biblioteca.livros.service.EvaluationsService;

@Service
public class ApiFacade {

	@Autowired
	BooksService booksService;

	@Autowired
	EvaluationsService evaluationsService;

	public List<Book> listAllBooks() {
		return booksService.listAllBooks();
	}

	public List<Book> listAllBooksWithComments() {
		return booksService.listAllBooks();
	}

	public Long saveEvaluation(Long bookId, EvaluationDTO evaluationDTO) {
		Book book = booksService.findBook(bookId);
		Evaluation evaluation = EvaluationConverter.toModel(evaluationDTO, book);
		return evaluationsService.saveEvaluation(evaluation);
	}

}
