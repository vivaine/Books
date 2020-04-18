package br.biblioteca.livros.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.biblioteca.livros.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	@Query(value = " from #{#entityName} I left join fetch I.author a order by I.name")
	List<Book> listBooks();
}
