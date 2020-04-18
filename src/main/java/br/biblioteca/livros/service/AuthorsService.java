package br.biblioteca.livros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.biblioteca.livros.models.Author;
import br.biblioteca.livros.repository.AuthorRepository;

@Service
public class AuthorsService {

	@Autowired
	AuthorRepository authorRepository;

	public List<Author> listAllAuthors() {
		return authorRepository.findAll();
	}

	public void saveAuthor(Author author) {
		authorRepository.save(author);
	}

	public Author findAuthor(Long id) {
		return authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Autor não encontrado"));
	}

	public void deleteAuthor(Long id) {
		authorRepository.deleteById(id);
	}

}
