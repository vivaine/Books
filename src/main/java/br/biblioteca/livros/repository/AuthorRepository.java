package br.biblioteca.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.biblioteca.livros.models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
