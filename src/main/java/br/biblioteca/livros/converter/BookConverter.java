package br.biblioteca.livros.converter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import br.biblioteca.livros.dto.BookDTO;
import br.biblioteca.livros.models.Book;

public class BookConverter {

	public static BookDTO toDTO(Book book) {
		BookDTO dto = new BookDTO();

		dto.setTitle(book.getName());
		dto.setNumberPages(book.getNumberPages());
		dto.setAuthor(book.getAuthor() != null ? book.getAuthor().getName() : null);
		dto.setEvaluations(
				book.getEvaluations().size() > 0 ? EvaluationConverter.toDTO(book.getEvaluations()) : Arrays.asList());
		return dto;
	}

	public static List<BookDTO> toDTO(List<Book> books) {

		return books.stream().map(l -> toDTO(l)).collect(Collectors.toList());
	}

}
