package br.biblioteca.livros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.biblioteca.livros.exception.BookNotFoundException;
import br.biblioteca.livros.models.Book;
import br.biblioteca.livros.repository.BookRepository;

@Service
public class BooksService {

	@Autowired
	BookRepository bookRepository;

	public List<Book> listAllBooks() {
		return bookRepository.listBooks();
	}

	public void saveBook(Book book) {
		bookRepository.save(book);
	}

	public Book findBook(Long id) {
		return bookRepository.findById(id).orElseThrow(() -> new BookNotFoundException());
	}

	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}

}
